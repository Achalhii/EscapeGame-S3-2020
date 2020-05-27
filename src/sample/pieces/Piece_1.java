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



public class Piece_1 extends Piece {
    private Stage primary;
    private static Group group ;
    private static Scene scene ;

    private static Fleche fleche0 =  new Fleche(1,50,100,300,200,true,0);
    private static  Fleche fleche1 =  new Fleche(2,50,1200,300,-20,true,0);
    private static Item[] listItem = {
            new Item("image/2corde.png","image/manche2.png","image/cordecache.png",false),};

    public Piece_1(Stage primaryStage) {
        super(primaryStage,listItem);
        primary=primaryStage;
    }

    public Scene getScene(){

        //TU CODE CE QUE TU VEUX D'ICI
        super.setListItem(listItem);
        group= null;
        scene=null;
        group=new Group();
        ImageView img = new ImageView(new Image(Piece.class.getResourceAsStream("image/Piece1.jpg")));
        img.setFitWidth(1280);
        img.setFitHeight(720);
        group.getChildren().add(img);
        group.getChildren().add(fleche0);
        group.getChildren().add(fleche1);

        Inventaire inventaire = Piece.inventaire;

        //zone où tu rentres les items sur ta scene
        tonItem(0, inventaire, group, 1150,420,50,0.7,50);
        group.getChildren().add(inventaire);

        //A ICI.
        scene = new Scene(group, 1280, 720, Color.BLACK);
        itemSelec(scene, inventaire);
        group.setOpacity(0);
        animationouverture(scene);
        return scene;
    }

}
