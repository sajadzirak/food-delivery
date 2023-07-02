package main.adminPanel.controllers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import main.adminPanel.AdminClient;
import main.classes.Food;
import main.classes.Restaurant;
import main.classes.methods;
import main.userPanel.UserClient;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RestaurantFoodManagementPageController implements Initializable {

    public static Restaurant restaurant;
    private Stage addBox;
    public static Stage addBoxCopy;

    @FXML
    private Button addFoodButton;

    @FXML
    private TilePane centerTilePane;
    public static TilePane centerTilePaneCopy;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label titleLabel;

    @FXML
    private HBox topMenu;

    @FXML
    void addFoodButtonClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        addBox = new Stage();
        addBoxCopy = addBox;
        Parent root = FXMLLoader.load(getClass().getResource("addFoodBox.fxml"));
        addBox.setScene(new Scene(root));
        addBox.setTitle("Add Food");
        addBox.initModality(Modality.APPLICATION_MODAL);
        addBox.showAndWait();
        methods.addFoodsToTilePane(centerTilePane, restaurant, 'A', AdminClient.toServer, AdminClient.fromServer);
        // addFoodsToTilePane(centerTilePane, adminClient.toServer, adminClient.fromServer);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            centerTilePaneCopy = centerTilePane;
            restaurant = (Restaurant)AdminClient.fromServer.readObject();
            // addFoodsToTilePane(centerTilePane, adminClient.toServer, adminClient.fromServer);
            methods.addFoodsToTilePane(centerTilePane, restaurant, 'A', AdminClient.toServer, AdminClient.fromServer);
        }catch(Exception e){
            e.printStackTrace();
        }
        titleLabel.setText(restaurant.getName());
    }

    // private void addFoodsToTilePane(TilePane pane, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
    //     int size;
    //     String request = "Get Foods";
    //     toServer.writeObject(request);
    //     toServer.writeObject(restaurant.getName());
    //     pane.getChildren().clear();
    //     size = (Integer)fromServer.readObject();
    //     for(int i = 0; i < size; i++){
    //         pane.getChildren().add(new FoodTile((Food) adminClient.fromServer.readObject()));
    //     }
    // }
    
    public static void refresh() throws ClassNotFoundException, IOException {
        methods.addFoodsToTilePane(centerTilePaneCopy, restaurant, 'A', AdminClient.toServer, AdminClient.fromServer);
    }
}