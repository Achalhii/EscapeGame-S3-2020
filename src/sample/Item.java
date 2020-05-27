package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


public class Item extends ImageView {
    private String nom;
    private String itemFusion;
    private String complexe;
    private String nomCamouflage;


    public Item(String nom, boolean avec_une_fusion) {
        super(new Image(Item.class.getResourceAsStream(nom)));
        this.nomCamouflage = nom;
        this.nom = nom;
        if (avec_une_fusion) {
            this.itemFusion = "Cheated";
            this.complexe = "Cheated";
        }
    }

    public Item(String nom, String itemFusion, boolean avec_une_fusion) {
        super(new Image(Item.class.getResourceAsStream(nom)));
        this.nom = nom;
        this.nomCamouflage = nom;
        this.itemFusion = itemFusion;
        if (avec_une_fusion) {
            this.complexe = "Cheated";
        }
    }

    public Item(String nom, String itemFusion, String nomCamouflage, boolean avec_une_fusion) {
        super(new Image(Item.class.getResourceAsStream(nomCamouflage)));
        this.nom = nom;
        this.itemFusion = itemFusion;
        this.nomCamouflage = nomCamouflage;
        if (avec_une_fusion) {
            this.itemFusion = "Cheated";
        }
    }

    public void afficher(int X, int Y, int taille, double camouflage, double rotation) {
        setFitWidth(taille);
        setFitHeight(taille);
        setX(X);
        setY(Y);
        setRotate(rotation);
        setStyle("-fx-opacity: " + camouflage + ";");
        setOnMouseEntered((mouseEvent) -> {
            setStyle("-fx-opacity: 1;");
        });
        setOnMouseExited((mouseEvent) -> {
            setStyle("-fx-opacity: " + camouflage + ";");
        });
    }

    public String getNom() {
        return nom;
    }

    public String getComplexe() {
        return this.complexe;
    }

    public String getItemFusion() {
        return this.itemFusion;
    }

    public void setItemFusion(String itemFusion) {
        this.itemFusion = itemFusion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(nom, item.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, itemFusion, complexe, nomCamouflage);
    }
}


