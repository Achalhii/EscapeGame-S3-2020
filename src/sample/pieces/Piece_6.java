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



public class Piece_6 extends Piece {
    private Stage primary;
    private static Group group;
    private static Scene scene;
    private static Fleche fleche0 = new Fleche(6,70, 100, 530, 90, true,-1);
    private MediaPlayer bougerpierre = new MediaPlayer(new Media(Piece.class.getResource("son/bougerpierre.mp3").toExternalForm()));
    private MediaPlayer prendrepierre = new MediaPlayer(new Media(Piece.class.getResource("son/prendreitem.mp3").toExternalForm()));
    public static String[] place_boule = new String[6];

    public Piece_6(Stage primaryStage) {
        super(primaryStage);
        primary = primaryStage;
    }

    public Scene getScene() {
        //TU CODE CE QUE TU VEUX D'ICI
        group=null;
        scene=null;
        group=new Group();
        ImageView img = new ImageView(new Image(Piece.class.getResourceAsStream("image/door-close.png")));
        img.setFitWidth(1000);
        img.setFitHeight(1000);
        img.setY(-200);
        img.setX(130);
        group.getChildren().add(img);
        group.getChildren().add(fleche0);

        ImageView bouton = new ImageView(new Image(Piece.class.getResourceAsStream("image/test-bouton.png")));
        bouton.setFitWidth(100);
        bouton.setFitHeight(100);
        bouton.setY(400);
        bouton.setX(1100);
        bouton.setOpacity(0.8);
        bouton.setOnMousePressed(mouseEvent -> {
            bouton.setOpacity(1);
            ouverture_porte();
        });
        bouton.setOnMouseReleased(mouseEvent -> {
            bouton.setOpacity(0.8);
        });
        group.getChildren().add(bouton);

        Inventaire inventaire =troue(group);

        //zone où tu rentres les items sur ta scene

        group.getChildren().add(inventaire);
        //A ICI.
        scene = new Scene(group, 1280, 720, Color.BLACK);
        itemSelec(scene, inventaire);
        group.setOpacity(0);
        animationouverture(scene);
        return scene;
    }

    public void ouverture_porte(){
        try{if(place_boule[0].equals("image/Sphere/sphere-11.png")){
            if(place_boule[2].equals("image/Sphere/sphere-12.png")){
                if(place_boule[4].equals("image/Sphere/sphere-22.png")){
                    if (place_boule[1].equals("image/Sphere/sphere-07.png")) {
                        if(place_boule[3].equals("image/Sphere/sphere-03.png")){
                            if(place_boule[5].equals("image/Sphere/sphere-05.png")){
                                Piece_5.ouvertureporte=true;
                                new MediaPlayer(new Media(Piece.class.getResource("son/ouverture.mp3").toExternalForm())).play();
                                Fleche.piece.getab(6).lanceScene();
                            }
                        }
                    }
                }
            }
        }}catch (Exception ez){
            prendrepierre.stop();
            prendrepierre.play();
        }
    }

    public Inventaire troue(Group group) {
        Inventaire inventaire = Piece.inventaire;
        for (int numero = 0; numero < 6; numero++) {
            if (place_boule[numero] == null) {
                ImageView troue = new ImageView(new Image(Piece.class.getResourceAsStream("image/Sphere/sphere-18.png")));
                troue.setFitWidth(36);
                troue.setFitHeight(36);
                troue.setY(123 + (numero + 1) % 2 * (numero / 2) * 176 + (numero % 2) * ((numero - 1) / 2) * 176);
                troue.setX(562 + (numero % 2) * 100);
                int finalNumero = numero;
                troue.setOnMouseClicked(mouseEvent -> {
                    try{
                        if (inventaire.getItemSelection().getComplexe() == null) {
                            place_boule[finalNumero] = inventaire.getItemSelection().getNom();
                            inventaire.supprimer_item(inventaire.getItemSelection());
                            inventaire.setItemSelection(null);
                            troue(group);
                            group.getChildren().remove(inventaire);
                            group.getChildren().add(inventaire);
                            bougerpierre.stop();
                            bougerpierre.play();
                            inventaire.labarre();
                            inventaire.refreshInventaire();
                        }}catch(Exception ignored){}
                });
                group.getChildren().add(troue);
            } else {
                ImageView troue = new ImageView(new Image(Piece.class.getResourceAsStream(place_boule[numero])));
                troue.setFitWidth(36);
                troue.setFitHeight(36);
                troue.setY(123 + (numero + 1) % 2 * (numero / 2) * 176 + (numero % 2) * ((numero - 1) / 2) * 176);
                troue.setX(562 + (numero % 2) * 100);
                int finalNumero1 = numero;
                troue.setOnMouseClicked(mouseEvent -> {
                    try{
                        if(inventaire.getItemSelection()==null && place_boule[finalNumero1]!="image/Sphere/sphere-18.png"){
                            inventaire.ajouterItemInventaire(new Item(place_boule[finalNumero1], false));
                            place_boule[finalNumero1]=null;
                            troue(group);
                            group.getChildren().remove(inventaire);
                            group.getChildren().add(inventaire);
                            inventaire.labarre();
                            inventaire.refreshInventaire();
                            bougerpierre.stop();
                            bougerpierre.play();
                        }
                        if (inventaire.getItemSelection().getComplexe() == null) {
                            inventaire.supprimer_item(inventaire.getItemSelection());
                            inventaire.ajouterItemInventaire(new Item(place_boule[finalNumero1], false));
                            place_boule[finalNumero1] = inventaire.getItemSelection().getNom();
                            inventaire.setItemSelection(null);
                            troue(group);
                            group.getChildren().remove(inventaire);
                            group.getChildren().add(inventaire);
                            bougerpierre.stop();
                            bougerpierre.play();
                        }
                    }catch (Exception ignored){}
                });
                group.getChildren().add(troue);
            }
        }
        return inventaire;
    }
}
