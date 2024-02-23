package dk.easv.presentation.controller;

import dk.easv.dataaccess.Test1;
import dk.easv.entities.Movie;
import dk.easv.entities.TopMovie;
import dk.easv.entities.UserSimilarity;
import dk.easv.presentation.model.AppModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DiscoverController {
    @FXML
    private ImageView imagelogo;
    @FXML
    private ImageView imagepfp;
    @FXML
    private ListView lvTopSimilarUsers;
    @FXML
    private ListView lvTopForUser;
    @FXML
    private ListView lvTopAvgNotSeen;
    @FXML
    private ListView lvUsers;
    @FXML
    private ListView lvTopFromSimilar;
    @FXML
    private ImageView movimage1;
    @FXML
    private Label lbl1;
    @FXML
    private ImageView movimage2;
    @FXML
    private Label lbl3;
    @FXML
    private ImageView movimage3;
    @FXML
    private Label lbl2;
    @FXML
    private ImageView movimage4;
    @FXML
    private Label lbl4;
    @FXML
    private ImageView movimage5;
    @FXML
    private Label lbl5;
    @FXML
    private ImageView movimage6;
    @FXML
    private Label lbl6;
    @FXML
    private ImageView movimage7;
    @FXML
    private Label lbl7;
    @FXML
    private ImageView movimage8;
    @FXML
    private Label lbl8;

    int posMov1;
    int posMov2;

    AppModel model;

    ArrayList<Label> labels1 = new ArrayList<Label>();
    ArrayList<Label> labels2 = new ArrayList<Label>();
    ArrayList<Movie> movies1 = new ArrayList<>();
    ArrayList<String> movies2 = new ArrayList<>();

    ArrayList<ImageView> imageViews = new ArrayList<>();

    String[] titles = {"godfather.png", "incredibles.png", "cat1.jpg", "aladdin.png", "mash4.png", "seven.png", "ferrisday.png", "rainman.png"};

    private AppController appControllerD;

    public void setAppControllerD(AppController appcontroller){
        this.appControllerD=appcontroller;
    }

    public void ClickPFP(MouseEvent mouseEvent) throws IOException {
        //similar users / model.getObsSimilarUsers();
        //name of the user / model.getObsLoggedInUser().getName();
        //rating of the user / model.getObsLoggedInUser().getRatings();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/Profile.fxml"));
        Parent root = loader.load();

        ProfileController profileController = loader.getController();
        profileController.setDiscoverController(this);
        profileController.setData(model);

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public void setData(AppModel model) throws IOException {
        this.model = model;
        movies1.addAll(model.getObsTopMovieNotSeen());
        ObservableList<TopMovie> topSimilar = model.getObsTopMoviesSimilarUsers();
        for (int i = 0; i < topSimilar.size();i++){
            movies2.add(topSimilar.get(i).getTitle());
        }
        labels1.addAll(Arrays.asList(lbl1,lbl3,lbl2,lbl4));
        labels2.addAll(Arrays.asList(lbl5,lbl6,lbl7,lbl8));
        imageViews.addAll(Arrays.asList(movimage1, movimage2, movimage3, movimage4,movimage5,movimage6,movimage7,movimage8));

        File file = new File("src/dk/easv/presentation/view/images/maxcat.png");
        Image image = new Image(file.toURI().toString(), 55, 55, false ,true);
        imagepfp.setImage(image);
        File file2 = new File("src/dk/easv/presentation/view/images/netflixbluemax.png");
        Image image2 = new Image(file2.toURI().toString(), 300, 100, false ,true);
        imagelogo.setImage(image2);
        for(int i = 0; i<4;i++){
            imageShowI1(i);
        }
        for(int i = 4; i<8;i++){
            imageShowI2(i);
        }

        for(int i = 0; i < labels1.size(); i++){
            labels1.get(i).setText(String.valueOf(movies1.get(i).getTitle()));
            posMov1 = i;
        }
        for(int i = 0; i < labels2.size(); i++){
            labels2.get(i).setText(movies2.get(i));
            posMov2 = i;
        }
    }

    public void prevMov1(ActionEvent actionEvent) {
        int i;
        if(posMov1 > 4){
            posMov1 -= 3;
            for(i = 3;i >= 0; i--){
                imageShowP11(i, posMov1);
                labels1.get(i).setText(String.valueOf(movies1.get(posMov1 -= 1).getTitle()));
                System.out.println(i + "i" + " " + (posMov1) +" posMov");
            }
            posMov1 += 3;
            System.out.println(posMov1 + "Final");
        }
        else{
            posMov1 = 104;
            for(i = 3;i >= 0; i--){
                posMov1 -= 1;
                imageShowP12(i, posMov1);
                labels1.get(i).setText(String.valueOf(movies1.get(posMov1-i).getTitle()));
            }

            System.out.println(posMov1 + "goes to 104");
        }
    }

    public void prevMov2(ActionEvent actionEvent) {
        int i;
        if(posMov2 > 4){
            posMov2 -= 3;
            for(i = 3;i >= 0; i--){
                imageShowP21(i, posMov2);
                labels2.get(i).setText(String.valueOf(movies2.get(posMov2 -= 1)));
                System.out.println(i + "i" + " " + (posMov2) +" posMov");
            }
            posMov2 += 3;
            System.out.println(posMov2 + "Final");
        }
        else{
            posMov2 = 104;
            for(i = 3;i >= 0; i--){
                posMov2 -= 1;
                imageShowP22(i, posMov2);
                labels2.get(i).setText(String.valueOf(movies2.get(posMov2-i)));
            }

            System.out.println(posMov2 + "goes to 104");
        }
    }

    public void nextMov1(ActionEvent actionEvent) {
        int i;
        for(i = 0;i < labels1.size(); i++){
            imageShowN1(i, posMov1);
            labels1.get(i).setText(String.valueOf(model.getObsTopMovieNotSeen().get(posMov1+i).getTitle()));
        }
        posMov1 += i ;
        System.out.println(posMov1);
    }

    public void nextMov2(ActionEvent actionEvent) {
        int i;
        for(i = 0;i < labels2.size(); i++){
            imageShowN2(i, posMov2);
            labels2.get(i).setText(String.valueOf(model.getObsTopMoviesSimilarUsers().get(posMov2+i).getTitle()));
        }
        posMov2 += i ;
        System.out.println(posMov2);
    }

    private void imageShowI1(int i) throws IOException {
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMovieNotSeen().get(i).getTitle()));
            poster = poster.substring(1,poster.length()-1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!poster.contains("https://")){
            //fix this bitch
            File file11 = new File("src/dk/easv/presentation/view/images/noimage.png");
            Image image11 = new Image(file11.toURI().toString(), 200, 200, false ,true);
            imageViews.get(i).setImage(image11);
        }
        else{
            System.out.println(poster);
            URL url = new URL(poster);
            BufferedImage img = ImageIO.read(url);
            File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMovieNotSeen().get(i).getTitle().replace(":", " ")+".jpg");
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
    }

    private void imageShowI2(int i) throws IOException {
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMoviesSimilarUsers().get(i).getTitle()));
            poster = poster.substring(1,poster.length()-1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!poster.contains("https://")){
            //fix this bitch
            File file11 = new File("src/dk/easv/presentation/view/images/noimage.png");
            Image image11 = new Image(file11.toURI().toString(), 200, 200, false ,true);
            imageViews.get(i).setImage(image11);
        }
        else{
            System.out.println(poster);
            URL url = new URL(poster);
            BufferedImage img = ImageIO.read(url);
            File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMoviesSimilarUsers().get(i).getTitle().replace(":", " ")+".jpg");
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
    }

    private void imageShowP11(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMovieNotSeen().get(posMov-1).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster);
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
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMovieNotSeen().get(posMov-1).getTitle().replace(":", " ")+".jpg");
                if(f.exists() && !f.isDirectory()) {
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i).setImage(image1);
                }
                else{
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i+4).setImage(image1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void imageShowP12(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMoviesSimilarUsers().get(posMov-1).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster);
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
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMoviesSimilarUsers().get(posMov-1).getTitle().replace(":", " ")+".jpg");
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

    private void imageShowP21(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMoviesSimilarUsers().get(posMov-1).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster);
            if(!poster.contains("https://")){
                //fix this bitch
                File file = new File("src/dk/easv/presentation/view/images/noimage.png");
                Image image = new Image(file.toURI().toString(), 200, 200, false ,true);
                imageViews.get(i+4).setImage(image);
            }
            else{
                System.out.println(poster);
                URL url = new URL(poster);
                BufferedImage img = ImageIO.read(url);
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMoviesSimilarUsers().get(posMov-1).getTitle().replace(":", " ")+".jpg");
                if(f.exists() && !f.isDirectory()) {
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i+4).setImage(image1);
                }
                else{
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i+4).setImage(image1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void imageShowP22(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMoviesSimilarUsers().get(posMov-i).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster);
            if(!poster.contains("https://")){
                //fix this bitch
                File file = new File("src/dk/easv/presentation/view/images/noimage.png");
                Image image = new Image(file.toURI().toString(), 200, 200, false ,true);
                imageViews.get(i+4).setImage(image);
            }
            else{
                System.out.println(poster);
                URL url = new URL(poster);
                BufferedImage img = ImageIO.read(url);
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMoviesSimilarUsers().get(posMov-i).getTitle().replace(":", " ")+".jpg");
                if(f.exists() && !f.isDirectory()) {
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i+4).setImage(image1);
                }
                else{
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i+4).setImage(image1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void imageShowN1(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMovieNotSeen().get(posMov+i).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster);
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
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMovieNotSeen().get(posMov+i).getTitle().replace(":", " ")+".jpg");
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

    private void imageShowN2(int i, int posMov){
        Test1 test1 = new Test1();
        String poster;
        try {
            poster = test1.getImage(String.valueOf(model.getObsTopMoviesSimilarUsers().get(posMov+i).getTitle()));
            poster = poster.substring(1,poster.length()-1);
            System.out.println(poster);
            if(!poster.contains("https://")){
                //fix this bitch
                File file = new File("src/dk/easv/presentation/view/images/noimage.png");
                Image image = new Image(file.toURI().toString(), 200, 200, false ,true);
                imageViews.get(i+4).setImage(image);
            }
            else{
                System.out.println(poster);
                URL url = new URL(poster);
                BufferedImage img = ImageIO.read(url);
                File f = new File("src/dk/easv/presentation/view/images/"+model.getObsTopMoviesSimilarUsers().get(posMov+i).getTitle().replace(":", " ")+".jpg");
                if(f.exists() && !f.isDirectory()) {
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i+4).setImage(image1);
                }
                else{
                    ImageIO.write(img, "jpg", f);
                    Image image1 = new Image(f.toURI().toString(), 200, 200, false, false);
                    imageViews.get(i+4).setImage(image1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
