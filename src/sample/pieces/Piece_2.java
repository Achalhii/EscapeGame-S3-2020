package sample.pieces;

import sample.Fleche;
import sample.Inventaire;
import sample.Item;
import sample.Piece;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Piece_2 extends Piece {
    private Stage primary;
    private static Group group ;
    private static Scene scene ;
    private static Fleche fleche0 =  new Fleche(2,90,50,530,150,true,0);
    private static  Fleche fleche1 =  new Fleche(4,40,820,410,-12,true,0);

    private static Item[] listItem={new Item ("image/Sphere/sphere-03.png",false),
            new Item ("image/Sphere/sphere-12.png",false)};

    public Piece_2 (Stage primaryStage) {
        super(primaryStage,listItem);
        primary=primaryStage;
    }

    public Scene getScene(){

        //TU CODE CE QUE TU VEUX D'ICI
        super.setListItem(listItem);
        group=null;
        scene=null;
        group=new Group();
        ImageView img = new ImageView(new Image(Piece.class.getResourceAsStream("image/Piece_2.png")));
        img.setFitWidth(1280);
        img.setFitHeight(623);
        group.getChildren().add(img);
        group.getChildren().add(fleche0);
        group.getChildren().add(fleche1);

        Inventaire inventaire = Piece.inventaire;

        //zone où tu rentres les items sur ta scene
        tonItem(0, inventaire, group, 1143,535,30,0.5,50);
        tonItem(1, inventaire, group, 736,476,8,0.5,50);
        group.getChildren().add(inventaire);

        //A ICI.

        scene = new Scene(group, 1280, 720, Color.BLACK);
        itemSelec(scene, inventaire);
        group.setOpacity(0);
        animationouverture(scene);
        return scene;
    }

}

