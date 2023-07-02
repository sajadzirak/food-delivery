package main.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

public class methods {
    
    public static void addFoodsToTilePane(TilePane pane, Restaurant restaurant, char flag, ObjectOutputStream toServer, ObjectInputStream fromServer) throws IOException, ClassNotFoundException{
        int size;
        String request = "Get Foods";
        toServer.writeObject(request);
        toServer.writeObject(restaurant.getName());
        pane.getChildren().clear();
        size = (Integer)fromServer.readObject();
        if(flag == 'U') {
            for(int i = 0; i < size; i++){
                pane.getChildren().add(new main.userPanel.FoodTile((Food) fromServer.readObject(), (Integer) fromServer.readObject(), restaurant.getName()));
            }
        }
        else if(flag == 'A') {
            for(int i = 0; i < size; i++){
                pane.getChildren().add(new main.adminPanel.others.FoodTile((Food) fromServer.readObject(), (Integer) fromServer.readObject(), restaurant.getName()));
            }
        }
    }

    public static boolean checkForEmptyTextField(TextField...fields) {
        for(TextField tf : fields) {
            if(tf.getText().equals(""))
                return false;
        }
        return true;
    }
}