package dk.easv.presentation.controller;

import dk.easv.entities.UserSimilarity;
import dk.easv.presentation.model.AppModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;

public class FriendsController {
    public Label friendName;
    public Button addbutton;
    private ProfileController ProfileController;

    public void setProfileController(ProfileController profilecontroller){
        this.ProfileController=profilecontroller;
    }

    public void setData(String name) {
        friendName.setText(name);
    }

    public void addFriend(ActionEvent actionEvent) {
        addbutton.setText("Friend added");
        addbutton.setDisable(true);
    }
}
