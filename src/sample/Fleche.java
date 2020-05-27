package sample;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Fleche extends ImageView {
    private static MediaPlayer sondroite = new MediaPlayer(new Media(Piece.class.getResource("son/marcheforetdroite.mp3").toExternalForm()));
    private static MediaPlayer songauche = new MediaPlayer(new Media(Piece.class.getResource("son/marcheforetgauche.mp3").toExternalForm()));
    private static MediaPlayer soncentre = new MediaPlayer(new Media(Piece.class.getResource("son/marcheforetcentre.mp3").toExternalForm()));
    private static MediaPlayer sonforet = new MediaPlayer(new Media(Piece.class.getResource("son/son-foret.mp3").toExternalForm()));
    private static MediaPlayer songrotte = new MediaPlayer(new Media(Piece.class.getResource("son/son-grotte.mp3").toExternalForm()));
    private static MediaPlayer marchergrotte = new MediaPlayer(new Media(Piece.class.getResource("son/marchergrotte.mp3").toExternalForm()));
    public static Piece piece = new Piece();

    public Fleche(int lapiece, int taille, int X, int Y, int rotate, boolean autoRotate, int idson) {
        super();
        setImage(getDefaultImage());
        setFitWidth(taille);
        setFitHeight(taille);
        setX(X);
        setY(Y);
        setStyle("-fx-opacity: 0.6;");
        setRotate(rotate);
        setOnMouseEntered((mouseEvent) -> {
            setStyle("-fx-opacity: 1;");
        });
        setOnMouseExited((mouseEvent) -> {
            setStyle("-fx-opacity: 0.6;");
        });
        setOnMouseClicked(mouseEvent -> {
            if (idson == 1) {
                sonforet.stop();
                piece.setMedia(sonforet);
            }
            if (idson == 2) {
                songrotte.stop();
                piece.setMedia(songrotte);
            }
            if (idson != -1) {
                if (rotate > -90 && rotate < 90) {
                    sondroite.stop();
                    sondroite.play();
                } else if (rotate == 90 || rotate == -90) {
                    soncentre.stop();
                    soncentre.play();
                } else {
                    songauche.stop();
                    songauche.play();
                }
            } else {
                marchergrotte.stop();
                marchergrotte.play();
            }
                piece.getab(lapiece).lanceScene();
        });
        if (autoRotate) {
            startAutoRotate();
        }
    }

    private void startAutoRotate() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), ev -> {
            rotate3DByX(0.5);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        visibleProperty().addListener(observable -> {
            if (isVisible()) timeline.play();
            else timeline.stop();
        });
    }

    protected final Image getDefaultImage() {
        return new Image(Fleche.class.getResourceAsStream("image/arrow.png"));
    }

    public void rotate3DByX(double angle) {
        Rotate transformX = new Rotate(angle, Rotate.X_AXIS);
        transformX.setPivotX(getX() + getFitWidth() / 2);
        transformX.setPivotY(getY() + getFitHeight() / 2);
        getTransforms().add(transformX);
    }
}
