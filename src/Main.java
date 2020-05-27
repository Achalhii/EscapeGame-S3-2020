import sample.Piece;
import sample.pieces.Piece_1;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Crusoé");
        Piece demarre = new Piece_1(primaryStage);
        demarre.lanceScene();
        demarre.getMedia().setCycleCount(50);
        demarre.getMedia().play();
    }
}

