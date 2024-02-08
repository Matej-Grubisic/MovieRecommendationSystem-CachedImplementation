package dk.easv.presentation.controller;

import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
    AppModel model;
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


    public void setData(AppModel model) {
        this.model = model;

    }

    public void prevMov1(ActionEvent actionEvent) {

    }
    public void nextMov1(ActionEvent actionEvent) {

    }

    public void prevMov2(ActionEvent actionEvent) {

    }
    public void nextMov2(ActionEvent actionEvent) {

    }
}
