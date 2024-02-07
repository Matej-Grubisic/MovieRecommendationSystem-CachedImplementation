package dk.easv.presentation.controller;

import dk.easv.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public TextField MRText;
    public TextField nameText;
    public Button closeBTN;

    public ImageView pfp1;

    private User user;


    private void setData(User user) {

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
