package dk.easv.dataaccess;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.*;


public class Test1 {
        public String getImage(String title) throws IOException {
            title = title.replace(" ", "+");
            if(title.contains(":")){
                title = title.substring(0, title.indexOf(":"));
            }
            if(title.contains("/")){
                title = title.substring(0, title.indexOf("/"));
            }

            System.out.println(title);
            URL api1 = new URL("https://www.omdbapi.com/?t="+title+"&apikey=4c9001f2");
            stream(api1, title);
            JsonParser parser = new JsonParser();
            try{
                Object obj = parser.parse(new FileReader("src\\dk\\easv\\data\\" + title +".json"));

                JsonObject jsonObject = (JsonObject) obj;

                String poster = String.valueOf(jsonObject.get("Poster"));

                return poster;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public static String stream(URL url, String title) throws IOException {
            try (InputStream input = url.openStream()) {
                InputStreamReader isr = new InputStreamReader(input);
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder json = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    json.append((char) c);
                }
                //System.out.println(json);
                CreateFile.create(title);
                CreateFile.write(title, json.toString());
                return json.toString();
            } catch (IOException e) {
                throw new IOException(e);
            }
        }


}


