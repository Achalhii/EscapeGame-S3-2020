package sample.pieces;

import sample.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Piece_5 extends Piece {
    private Stage primary;
    private static Group group ;
    private static Scene scene ;
    private static Fleche fleche =  new Fleche(5,70,420,310,-90,true,0);
    private static  Fleche fleche0 =  new Fleche(5,70,540,530,70,true,1);
    private static Item[] listItem = {
            new Item ("image/Sphere/sphere-00.png",false),
            new Item ("image/Sphere/sphere-21.png",false)};
    public static boolean ouvertureporte=false;

    public Piece_5(Stage primaryStage) {
        super(primaryStage,listItem);
        primary=primaryStage;
    }

    public Scene getScene(){

        //TU CODE CE QUE TU VEUX D'ICI
        super.setListItem(listItem);
        group=null;
        scene=null;
        group=new Group();
        ImageView img = new ImageView(new Image(Piece.class.getResourceAsStream("image/Piece_5.png")));
        img.setFitWidth(1280);
        img.setFitHeight(800);
        group.getChildren().add(img);
        if(!ouvertureporte) {
            ImageView grotteporte = new ImageView(new Image(Piece.class.getResourceAsStream("image/door-close.png")));
            grotteporte.setFitWidth(220);
            grotteporte.setFitHeight(230);
            grotteporte.setX(360);
            grotteporte.setY(190);
            grotteporte.setStyle("-fx-opacity: 0.95");
            grotteporte.setOnMouseEntered(mouseEvent -> {
                grotteporte.setStyle("-fx-opacity: 1");
            });
            grotteporte.setOnMouseExited(mouseEvent -> {
                grotteporte.setStyle("-fx-opacity: 0.95");
            });
            grotteporte.setOnMouseClicked(mouseEvent -> {
                Fleche.piece.getab(7).lanceScene();
            });
            group.getChildren().add(grotteporte);
            for (int numero = 0; numero < 6; numero++) {
                if (Piece_6.place_boule[numero]== null) {
                    ImageView troue = new ImageView(new Image(Piece.class.getResourceAsStream("image/Sphere/sphere-18.png")));
                    troue.setFitWidth(10);
                    troue.setFitHeight(10);
                    troue.setStyle("-fx-opacity: 0.95");
                    troue.setY(263 + (numero + 1) % 2 * (numero / 2) * 41 + (numero % 2) * ((numero - 1) / 2) * 41);
                    troue.setX(454 + (numero % 2) * 22);
                    troue.setOnMouseClicked(mouseEvent -> {
                        Fleche.piece.getab(7).lanceScene();
                    });
                    group.getChildren().add(troue);

                } else {
                    ImageView troue = new ImageView(new Image(Piece.class.getResourceAsStream(Piece_6.place_boule[numero])));
                    troue.setFitWidth(10);
                    troue.setFitHeight(10);
                    troue.setStyle("-fx-opacity: 0.95");
                    troue.setY(263 + (numero + 1) % 2 * (numero / 2) * 41 + (numero % 2) * ((numero - 1) / 2) * 41);
                    troue.setX(454 + (numero % 2) * 22);
                    troue.setOnMouseClicked(mouseEvent -> {
                        Fleche.piece.getab(7).lanceScene();
                    });
                    group.getChildren().add(troue);
                }
            }
        }
        else {ImageView grotteporte = new ImageView(new Image(Piece.class.getResourceAsStream("image/door-open.png")));
            grotteporte.setFitWidth(350);
            grotteporte.setFitHeight(230);
            grotteporte.setX(280);
            grotteporte.setY(190);
            group.getChildren().add(grotteporte);
            group.getChildren().add(fleche);
        }
        group.getChildren().add(fleche0);

        Inventaire inventaire = Piece.inventaire;

        //zone où tu rentres les items sur ta scene
        tonItem(0, inventaire, group, 750,472,40,0.8,50);
        tonItem(1, inventaire, group, 300,472,20,0.8,50);
        group.getChildren().add(inventaire);

        //A ICI.
        scene = new Scene(group, 1280, 720, Color.BLACK);
        itemSelec(scene, inventaire);
        group.setOpacity(0);
        animationouverture(scene);
        return scene;
    }
}
