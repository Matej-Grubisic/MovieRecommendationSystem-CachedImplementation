package dk.easv.presentation.controller;

import dk.easv.entities.User;
import dk.easv.entities.UserSimilarity;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public TextField MRText;
    public Button closeBTN;

    public ImageView pfp1;

    public Label name;
    public Label movrating;
    public TextArea similarUserTextArea;

    private AppController appController;

    private AppModel model;

    public void setAppController(AppController appController){
        this.appController=appController;
    }


    public void setData(AppModel model) {
        this.model=model;

       name.setText(model.getObsLoggedInUser().getName());
       movrating.setText(String.valueOf(model.getObsLoggedInUser().getRatingsSize()));
       similarUserTextArea.setText(String.valueOf(model.getObsSimilarUsers()));




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/dk/easv/presentation/view/images/maxcat.png");
        Image image = new Image(file.toURI().toString(), 55, 55, false ,true);
        pfp1.setImage(image);

    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeBTN.getScene().getWindow();
        stage.close();
    }
}
