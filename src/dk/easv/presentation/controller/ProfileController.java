package dk.easv.presentation.controller;

import dk.easv.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public TextField MRText;
    public ImageView pfppicture;
    public TextField nameText;
    public Button closeBTN;

    private User user;


    private void setData(User user) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeBTN.getScene().getWindow();
        stage.close();
    }
}
