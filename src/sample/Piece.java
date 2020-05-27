package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.pieces.*;


public class Piece {
    public static Inventaire inventaire = new Inventaire();
    private static Stage primary;
    private static MediaPlayer sonbackground = new MediaPlayer(new Media(Piece.class.getResource("son/son-foret.mp3").toExternalForm()));
    private static MediaPlayer sonprendreitem = new MediaPlayer(new Media(Piece.class.getResource("son/prendreitem.mp3").toExternalForm()));
    private static Piece[] piecesto = {new Piece_1(primary),
            new Piece_1_1(primary),
            new Piece_1_2(primary),
            new Piece_2(primary),
            new Piece_3(primary),
            new Piece_4(primary),
            new Piece_5(primary),
            new Piece_6(primary),
            new Piece_7(primary)};
    private Item[] listItem;

    public Piece(Stage primaryStage) {
        primary = primaryStage;
    }

    public Piece(Stage primaryStage, Item[] listIteme) {
        primary = primaryStage;
        listItem = listIteme;
    }

    public Piece() {
    }

    public static Stage getPrimary() {
        return primary;
    }

    public Scene getScene() {
        return new Scene(null);
    }

    public MediaPlayer getMedia() {
        return sonbackground;
    }

    public void setMedia(MediaPlayer son) {
        sonbackground.stop();
        sonbackground = son;
        sonbackground.setVolume(0.7);
        sonbackground.setCycleCount(50);
        sonbackground.play();
    }

    public void setListItem(Item[] list) {
        listItem = list;
    }

    public void itemSelec(Scene scene, Inventaire inventaire) {
        scene.setOnMouseClicked((mouseEvent) -> {
            if (inventaire.getItemSelection() != null) {
                scene.setCursor(new ImageCursor(new Image(Inventaire.class.getResourceAsStream(inventaire.getItemSelection().getNom()))));
            } else if (scene.getCursor() != Cursor.DEFAULT) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        if (inventaire.getItemSelection() != null) {
            scene.setCursor(new ImageCursor(new Image(Inventaire.class.getResourceAsStream(inventaire.getItemSelection().getNom()))));
        } else {
            scene.setCursor(Cursor.DEFAULT);
        }
    }

    public void tonItem(int n, Inventaire inventaire, Group group, int X, int Y, int taille, double camouflage, double rotation) {
        if (listItem[n] != null) {
            listItem[n].afficher(X, Y, taille, camouflage, rotation);
            listItem[n].setOnMousePressed(mouseEvent -> {
                sonprendreitem.stop();
                sonprendreitem.play();
                inventaire.ajouterItemInventaire(listItem[n]);
                group.getChildren().remove(listItem[n]);
                listItem[n] = null;
            });
            group.getChildren().add(listItem[n]);
        }
    }

    public void animationFermeture(Scene scene) {
        Timeline t = new Timeline(new KeyFrame(Duration.millis(0),
                new KeyValue(scene.getRoot().opacityProperty(), 1)),
                new KeyFrame(Duration.millis(500), new KeyValue(scene.getRoot()
                        .opacityProperty(), 0)));
        t.setOnFinished(e -> {
            lanceScene2();

        });
        t.setCycleCount(1);
        t.playFromStart();
    }

    public void animationouverture(Scene scene) {
        Timeline t = new Timeline(new KeyFrame(Duration.millis(0),
                new KeyValue(scene.getRoot().opacityProperty(), 0)),
                new KeyFrame(Duration.millis(500), new KeyValue(scene.getRoot()
                        .opacityProperty(), 1)));
        t.setCycleCount(1);
        t.playFromStart();
    }

    public void lanceScene() {
        if (primary.getScene() == null || primary.getScene().getRoot() == null) {
            lanceScene2();
        } else {
            animationFermeture(primary.getScene());
        }
    }

    public Piece getab(int nombre) {
        return piecesto[nombre];
    }

    public void lanceScene2() {
        primary.setScene(null);
        primary.setScene(getScene());
        primary.show();
    }
}
