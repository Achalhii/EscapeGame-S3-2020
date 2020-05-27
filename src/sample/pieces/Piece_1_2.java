package sample.pieces;


import sample.*;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Piece_1_2 extends Piece {
    private Stage primary;
    private static Group group;
    private static Scene scene;
    private static Fleche fleche0 =  new Fleche(3,80,590,350,-90,false,0);
    private static Fleche fleche =  new Fleche(1,50,100,400,180,true,0);
    private static Fleche fleche1 =  new Fleche(0,80,600,505,90,true,0);
    private static Item[] listItem = {
            new Item("image/lame.png","image/Couteau.png",true)};

    private static boolean ouvert=false;
    private static final MediaPlayer couper = new MediaPlayer(new Media(Piece.class.getResource("son/couper.mp3").toExternalForm()));

    public Piece_1_2(Stage primaryStage) {
        super(primaryStage,listItem);
        primary=primaryStage;
    }

    public Scene getScene(){

        //TU CODE CE QUE TU VEUX D'ICI
        super.setListItem(listItem);
        group= null;
        scene=null;
        group=new Group();
        ImageView img = new ImageView(new Image(Piece.class.getResourceAsStream("image/pieceBiss.png")));
        img.setFitWidth(1280);
        img.setFitHeight(720);
        group.getChildren().add(img);
        Inventaire inventaire = Piece.inventaire;
        if(!ouvert){
            ImageView buisson = new ImageView(new Image(Piece.class.getResourceAsStream("image/buisson.png")));
            buisson.setFitWidth(450);
            buisson.setFitHeight(330);
            buisson.setX(400);
            buisson.setY(200);
            buisson.setOnMouseClicked((mouseEvent) -> {try{
                if(inventaire.getItemSelection().getNom().equals("image/Couteau.png")){
                    ouvert=true;
                    group.getChildren().remove(buisson);
                    couper.stop();
                    couper.play();
                    group.getChildren().add(fleche0);

                }} catch (Exception ignored) {}
            });
            group.getChildren().add(buisson);
        }
        if(ouvert){
            group.getChildren().add(fleche0);
        }
        group.getChildren().add(fleche);
        group.getChildren().add(fleche1);

        //zone où tu rentres les items sur ta scene
        tonItem(0, inventaire, group, 220,500,50,0.8,-45);
        group.getChildren().add(inventaire);

        //A ICI.
        scene = new Scene(group, 1280, 720, Color.BLACK);
        itemSelec(scene, inventaire);
        group.setOpacity(0);
        animationouverture(scene);
        return scene;
    }

}
