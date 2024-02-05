package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.sql.Array;
import java.util.*;

public class AppController implements Initializable {
    public ImageView imagepfp;
    public ImageView imagelogo;
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
    String[] titles = {"godfather.png", "incredibles.png", "cat1.jpg", "aladdin.png", "mash4.png", "seven.png", "ferrisday.png", "rainman.png"};
    ArrayList<ImageView> imageViews = new ArrayList<>();
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
                    labels.addAll(Arrays.asList(lbl1,lbl3,lbl2,lbl4, lbl5, lbl6, lbl7, lbl8));
                    imageViews.addAll(Arrays.asList(movimage1, movimage2, movimage3, movimage4,movimage5,movimage6,movimage7,movimage8));

                    File file = new File("src/dk/easv/presentation/view/images/maxcat.png");
                    Image image = new Image(file.toURI().toString(), 55, 55, false ,true);
                    imagepfp.setImage(image);
                    File file2 = new File("src/dk/easv/presentation/view/images/netflixbluemax.png");
                    Image image2 = new Image(file2.toURI().toString(), 300, 100, false ,true);
                    imagelogo.setImage(image2);
                    for(int i = 0; i<titles.length;i++){
                        File file1 = new File("src/dk/easv/presentation/view/images/" + titles[i]);
                        Image image1 = new Image(file1.toURI().toString(), 200, 200, false, true);
                        imageViews.get(i).setImage(image1);
                    }

                    System.out.println(lbl1.getId());
                    for(int i = 0; i < labels.size(); i++){
                        labels.get(i).setText(String.valueOf(model.getObsTopMovieSeen().get(i).getTitle()));
                    }
                });

        // Select the logged-in user in the listview, automagically trigger the listener above
        lvUsers.getSelectionModel().select(model.getObsLoggedInUser());
    }
}
