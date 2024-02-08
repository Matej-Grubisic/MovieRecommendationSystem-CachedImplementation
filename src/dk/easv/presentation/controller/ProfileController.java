package dk.easv.presentation.controller;

import dk.easv.entities.UserSimilarity;
import dk.easv.presentation.model.AppModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public TextField MRText;
    public Button closeBTN;

    public ImageView pfp1;

    public Label name;
    public Label movrating;
    public ListView similarUserListView;

    private AppController appController;

    private ProfileController profileController;

    private DiscoverController discoverController;
    private AppModel model;

    public void setAppController(AppController appController){
        this.appController=appController;
    }

    public void setDiscoverController(DiscoverController discController){
        this.discoverController=discController;
    }


    public void setData(AppModel model) {
        this.model = model;

        name.setText(model.getObsLoggedInUser().getName());
        movrating.setText(String.valueOf(model.getObsLoggedInUser().getRatingsSize()));
        ObservableList<String> similarUserNames = FXCollections.observableArrayList();

        for (UserSimilarity similarUser : model.getObsSimilarUsers()) {
            similarUserNames.add(similarUser.getName());
        }

        similarUserListView.setItems(similarUserNames);
        similarUserListView.setOnMouseClicked(event ->{
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/presentation/view/Friends.fxml"));
                Parent root1;
                try {
                    root1 = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                FriendsController profileController = loader.getController();
                profileController.setProfileController(this);
                profileController.setData(similarUserListView.getSelectionModel().getSelectedItems().getFirst().toString());
                Stage stage = new Stage();
                stage.setTitle("ime usera");
                stage.setScene(new Scene(root1));
                stage.show();

            }
        });
        
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
