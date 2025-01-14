package dk.easv.presentation.controller;

import dk.easv.dataaccess.Test1;
import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;

public class AppController implements Initializable {
    @FXML
private ImageView imagepfp;
    @FXML
private ImageView imagelogo;
    @FXML
private ImageView imagelogo1;
    @FXML
private ImageView imagepfp1;
    @FXML
private ImageView movimage11;
    @FXML
private ImageView movimage22;
    @FXML
private ImageView movimage33;
    @FXML
private ImageView movimage44;
    @FXML
private ImageView movimage55;
    @FXML
private ImageView movimage66;
    @FXML
private ImageView movimage77;
    @FXML
private ImageView movimage88;
    @FXML
private Label lbl11;
    @FXML
private Label lbl22;
    @FXML
private Label lbl33;
    @FXML
private Label lbl44;
    @FXML
private Label lbl55;
    @FXML
private Label lbl66;
    @FXML
private Label lbl77;
    @FXML
private Label lbl88;
    @FXML
    private ImageView movimage1;
    @FXML
    private ImageView movimage2;
    @FXML
    private ImageView movimage3;
    @FXML
    private ImageView movimage4;
    @FXML
    private ImageView movimage5;
    @FXML
    private ImageView movimage6;
    @FXML
    private ImageView movimage7;
    @FXML
    private ImageView movimage8;
    String[] titles = {"godfather", "incredibles", "cat", "incredibles", "incredibles", "incredibles", "incredibles", "incredibles"};
    ArrayList<ImageView> imageViews = new ArrayList<>();
    int posMov;

    ArrayList<Movie> movies = new ArrayList<>();
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl6;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl8;
    ArrayList<Label> labels = new ArrayList<Label>();
    @FXML
    private ListView<User> lvUsers;
    @FXML
    private ListView<Movie> lvTopForUser;
    @FXML
    private ListView<Movie> lvTopAvgNotSeen;
    @FXML
    private ListView<UserSimilarity> lvTopSimilarUsers;
    @FXML
    private ListView<TopMovie> lvTopFromSimilar;


    private AppModel model;
    private long timerStartMillis = 0;
    private String timerMsg = "";

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setModel(AppModel model) {
        this.model = model;
        lvUsers.setItems(model.getObsUsers());
        lvTopForUser.setItems(model.getObsTopMovieSeen());
        lvTopAvgNotSeen.setItems(model.getObsTopMovieNotSeen());
        lvTopSimilarUsers.setItems(model.getObsSimilarUsers());
        lvTopFromSimilar.setItems(model.getObsTopMoviesSimilarUsers());

        startTimer("Load users");
        model.loadUsers();
        stopTimer();

        lvUsers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldUser, selectedUser) -> {
                    startTimer("Loading all data for user: " + selectedUser);
                    model.loadData(selectedUser);
                    movies.addAll(model.getObsTopMovieSeen());

                    labels.addAll(Arrays.asList(lbl1,lbl3,lbl2,lbl4, lbl5, lbl6, lbl7, lbl8));
                    imageViews.addAll(Arrays.asList(movimage1, movimage2, movimage3, movimage4,movimage5,movimage6,movimage7,movimage8));

                    File file = new File("src/dk/easv/presentation/view/images/maxcat.png");
                    Image image = new Image(file.toURI().toString(), 55, 55, false ,true);
                    imagepfp.setImage(image);
                    File file2 = new File("src/dk/easv/presentation/view/images/netflixbluemax.png");
                    System.out.println(file2.toURI().toString());
                    Image image2 = new Image(file2.toURI().toString(), 300, 100, false ,true);
                    imagelogo.setImage(image2);

                    for(int i = 0; i<8;i++) {
                        Test1 test1 = new Test1();
                        String poster;

                        try {
                            poster = test1.getImage(String.valueOf(model.getObsTopMovieSeen().get(i).getTitle()));
                            poster = poster.substring(1,poster.length()-1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            URL url = new URL(poster);
                            BufferedImage img = ImageIO.read(url);
                            //System.out.println(url.getProtocol() + "Protocol");
                            File file1 = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMovieSeen().get(i).getTitle()+".jpg");
                            ImageIO.write(img, "jpg", file1);
                            Image image1 = new Image(file1.toURI().toString(), 200, 200, false, false);
                            imageViews.get(i).setImage(image1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    //System.out.println(lbl1.getId());
                    for(int i = 0; i < labels.size(); i++){
                        labels.get(i).setText(String.valueOf(model.getObsTopMovieSeen().get(i).getTitle()));
                        posMov = i;
                    }
                   // System.out.println(movies);
                });

        // Select the logged-in user in the listview, automagically trigger the listener above
        lvUsers.getSelectionModel().select(model.getObsLoggedInUser());
    }



    public void nextMov(ActionEvent actionEvent) {
        int i;
        for(i = 0;i < labels.size(); i++){
            imageShowN(i, posMov);
            labels.get(i).setText(String.valueOf(model.getObsTopMovieSeen().get(posMov+i).getTitle()));
        }
        posMov += i ;
        //System.out.println(posMov);
    }

    public void prevMov(ActionEvent actionEvent) {
        int i;
        if(posMov > 8){
            posMov -= 7;
            for(i = 7;i >= 0; i--){
                imageShowP1(i, posMov);
                labels.get(i).setText(String.valueOf(movies.get(posMov -= 1).getTitle()));
                System.out.println(i + "i" + " " + (posMov) +" posMov");
            }
            posMov += 7;
            System.out.println(posMov + "Final");
        }
        else{
            posMov = 104;
            for(i = 7;i >= 0; i--){
                posMov -= 1;
                imageShowP2(i, posMov);
                labels.get(i).setText(String.valueOf(movies.get(posMov-i).getTitle()));
            }

            System.out.println(posMov + "goes to 104");
        }
    }

    public void ClickPFP(MouseEvent mouseEvent) throws IOException {
        //similar users / model.getObsSimilarUsers();
        //name of the user / model.getObsLoggedInUser().getName();
        //rating of the user / model.getObsLoggedInUser().getRatings();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/Profile.fxml"));
        Parent root = loader.load();

        ProfileController profileController = loader.getController();
        profileController.setAppController(this);
        profileController.setData(model);

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void openDiscover(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/Discover.fxml"));
        Parent root = loader.load();

        DiscoverController discoverController = loader.getController();
        discoverController.setAppControllerD(this);
        discoverController.setData(model);

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void imageShowN(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMovieSeen().get(posMov+i).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster.equals("N/A"));
            if(!poster.contains("https://")){
                //fix this bitch
                File file = new File("src/dk/easv/presentation/view/images/noimage.png");
                Image image = new Image(file.toURI().toString(), 200, 200, false ,true);
                imageViews.get(i).setImage(image);
            }
            else{
                System.out.println(poster);
                URL url = new URL(poster);
                BufferedImage img = ImageIO.read(url);
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMovieSeen().get(posMov+i).getTitle()+".jpg");
                if(f.exists() && !f.isDirectory()) {
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i).setImage(image1);
                }
                else{
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i).setImage(image1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void imageShowP2(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMovieSeen().get(posMov-i).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster.equals("N/A"));
            if(!poster.contains("https://")){
                //fix this bitch
                File file = new File("src/dk/easv/presentation/view/images/noimage.png");
                Image image = new Image(file.toURI().toString(), 200, 200, false ,true);
                imageViews.get(i).setImage(image);
            }
            else{
                System.out.println(poster);
                URL url = new URL(poster);
                BufferedImage img = ImageIO.read(url);
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMovieSeen().get(posMov-i).getTitle()+".jpg");
                if(f.exists() && !f.isDirectory()) {
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i).setImage(image1);
                }
                else{
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i).setImage(image1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void imageShowP1(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMovieSeen().get(posMov-1).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster.equals("N/A"));
            if(!poster.contains("https://")){
                //fix this bitch
                File file = new File("src/dk/easv/presentation/view/images/noimage.png");
                Image image = new Image(file.toURI().toString(), 200, 200, false ,true);
                imageViews.get(i).setImage(image);
            }
            else{
                System.out.println(poster);
                URL url = new URL(poster);
                BufferedImage img = ImageIO.read(url);
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMovieSeen().get(posMov-1).getTitle()+".jpg");
                if(f.exists() && !f.isDirectory()) {
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i).setImage(image1);
                }
                else{
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i).setImage(image1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

