package sample.pieces;

import sample.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Piece_7 extends Piece {
    private Stage primary;
    private static Item[] listItem = {
            new Item ("image/Sphere/sphere-22.png",null,"image/Sphere/orbSun.png",false),
            new Item ("image/Sphere/sphere-11.png",null,"image/Sphere/sphere-rouge.png",false)};
    private static Group group ;
    private static Scene scene ;
    private static Fleche fleche =  new Fleche(4,70,50,510,170,true,0);



    public Piece_7(Stage primaryStage) {
        super(primaryStage,listItem);
        primary=primaryStage;
    }

    public Scene getScene(){

        //TU CODE CE QUE TU VEUX D'ICI

        group=null;
        scene=null;
        group=new Group();

        super.setListItem(listItem);
        ImageView img = new ImageView(new Image(Piece.class.getResourceAsStream("image/Piece_7.png")));
        img.setFitWidth(1280);
        img.setFitHeight(908);
        img.setY(-200);
        group.getChildren().add(img);

        group.getChildren().add(fleche);

        Inventaire inventaire = Piece.inventaire;

        //zone où tu rentres les items sur ta scene
        tonItem(0, inventaire, group, 734,328,120,0.3,0);
        tonItem(1, inventaire, group, 304,467,30,0.5,17);

        group.getChildren().add(inventaire);

        //A ICI.

        scene = new Scene(group, 1280, 720, Color.BLACK);
        itemSelec(scene, inventaire);
        group.setOpacity(0);
        animationouverture(scene);
        return scene;
    }

}
