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

public class Piece_4 extends Piece {
    private Stage primary;
    private static Group group ;
    private static Scene scene ;
    private static Fleche fleche0 =  new Fleche(4,50,600,460,90,true,0);
    private static MediaPlayer songrotte= new MediaPlayer(new Media(Piece.class.getResource("son/son-grotte.mp3").toExternalForm()));
    private static Item[] listItem={new Item ("image/Sphere/sphere-07.png",false)};

    public Piece_4(Stage primaryStage) {
        super(primaryStage,listItem);
        primary=primaryStage;
    }

    public Scene getScene(){

        //TU CODE CE QUE TU VEUX D'ICI
        super.setListItem(listItem);
        group=null;
        scene=null;
        group=new Group();
        ImageView img = new ImageView(new Image(Piece.class.getResourceAsStream("image/Piece_4.png")));
        img.setFitWidth(1280);
        img.setFitHeight(623);
        group.getChildren().add(img);

        ImageView grotte = new ImageView(new Image(Piece.class.getResourceAsStream("image/grotteentree.png")));
        grotte.setFitWidth(430);
        grotte.setFitHeight(300);
        grotte.setX(1280-430);
        grotte.setY(623-300);
        group.getChildren().add(grotte);

        ImageView grotte2 = new ImageView(new Image(Piece.class.getResourceAsStream("image/grotteentree2.png")));
        grotte2.setFitWidth(430);
        grotte2.setFitHeight(300);
        grotte2.setX(1280-430);
        grotte2.setY(623-300);
        grotte2.setStyle("-fx-opacity: 0");
        grotte2.setOnMouseEntered(mouseEvent -> {
            grotte2.setStyle("-fx-opacity: 1");
        });
        grotte2.setOnMouseExited(mouseEvent -> {
            grotte2.setStyle("-fx-opacity: 0");
        });
        grotte2.setOnMouseClicked(mouseEvent -> {
            songrotte.stop();
            songrotte.play();
            super.setMedia(songrotte);
           Fleche.piece.getab(6).lanceScene();
        });

        group.getChildren().add(grotte2);
        group.getChildren().add(fleche0);

        Inventaire inventaire = Piece.inventaire;

        //zone où tu rentres les items sur ta scene
        tonItem(0, inventaire, group, 80,590,20,0.7,50);
        group.getChildren().add(inventaire);

        //A ICI.
        scene = new Scene(group, 1280, 720, Color.BLACK);
        itemSelec(scene, inventaire);
        group.setOpacity(0);
        animationouverture(scene);
        return scene;
    }

}
