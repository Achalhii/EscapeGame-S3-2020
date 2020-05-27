package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Inventaire extends Group {
    private static final MediaPlayer soncarte1 = new MediaPlayer(new Media(Piece.class.getResource("son/carte1.mp3").toExternalForm()));
    private static final MediaPlayer soncarte3 = new MediaPlayer(new Media(Piece.class.getResource("son/carte3.mp3").toExternalForm()));
    private static final MediaPlayer nop = new MediaPlayer(new Media(Piece.class.getResource("son/nopt.mp3").toExternalForm()));
    private static final MediaPlayer sonfusion = new MediaPlayer(new Media(Piece.class.getResource("son/fusion.mp3").toExternalForm()));
    private static final MediaPlayer sonselectionner = new MediaPlayer(new Media(Piece.class.getResource("son/selectionner.mp3").toExternalForm()));
    private static Item itemSelectionné;
    private static Item[] itemsActuels = new Item[12];

    public Inventaire() {
        labarre();
        refreshInventaire();
    }

    public void labarre() {
        getChildren().removeAll(getChildren());
        ImageView barre = new ImageView(new Image(Inventaire.class.getResourceAsStream("image/barre.png")));
        barre.setFitWidth(1280);
        barre.setFitHeight(120);
        barre.setY(600);
        getChildren().add(barre);
        laCarte();
    }

    public void refreshInventaire() {
        for (int i = 0; i < 12; i++) {
            if (itemsActuels[i] == null) {
                break;
            }
            ImageView img = new ImageView(new Image(Inventaire.class.getResourceAsStream(itemsActuels[i].getNom())));
            img.setFitWidth(60);
            img.setUserData(i);
            img.setFitHeight(60);
            img.setY(650);
            img.setX(30 + (i * 80));
            if (itemsActuels[i] == itemSelectionné) {
                img.setStyle("-fx-opacity: 1;");
            } else {
                img.setStyle("-fx-opacity: 0.4;");
            }
            img.setUserData(i);
            img.setOnMouseEntered((mouseEvent) -> {
                img.setStyle("-fx-opacity: 1;");
            });
            img.setOnMouseExited((mouseEvent) -> {
                if (itemSelectionné != itemsActuels[(int) img.getUserData()]) {
                    img.setStyle("-fx-opacity: 0.4;");
                }
            });
            img.setOnMouseClicked((mouseEvent) -> {
                if (itemSelectionné == null) {
                    sonselectionner.stop();
                    sonselectionner.play();
                    itemSelectionné = itemsActuels[(int) img.getUserData()];
                    img.setStyle("-fx-opacity: 1;");
                } else if (itemSelectionné == itemsActuels[(int) img.getUserData()]) {
                    sonselectionner.stop();
                    sonselectionner.play();
                    itemSelectionné = null;
                    img.setStyle("-fx-opacity: 0.4;");
                } else if (itemSelectionné != itemsActuels[(int) img.getUserData()] && itemSelectionné != null) {
                    fusion(img.getUserData(), itemSelectionné);
                }
            });
            getChildren().add(img);
        }
    }

    public void fusion(Object userData, Item item1) {
        Item item2 = itemsActuels[(int) userData];
        test(item2, item1);
        test(item1, item2);
        try {
            if (item2.getItemFusion().equals(item1.getItemFusion())) {
                Item fin = new Item(item1.getItemFusion(), true);
                if ((int) userData < getNumItem(item1)) {
                    itemsActuels[(int) userData] = fin;
                    for (int i = getNumItem(item1) + 1; i < itemsActuels.length; i++) {
                        itemsActuels[i - 1] = itemsActuels[i];
                    }
                    itemsActuels[itemsActuels.length - 1] = null;
                    itemSelectionné = null;
                    labarre();
                    refreshInventaire();
                }
                if ((int) userData > getNumItem(item1)) {
                    itemsActuels[getNumItem(item1)] = fin;
                    for (int i = (int) userData + 1; i < itemsActuels.length; i++) {
                        itemsActuels[i - 1] = itemsActuels[i];
                    }
                    itemSelectionné = null;
                    itemsActuels[itemsActuels.length - 1] = null;
                    labarre();
                    refreshInventaire();
                }
                sonfusion.stop();
                sonfusion.play();
            }
        } catch (Exception e) {
            nop.stop();
            nop.play();
        }
    }

    private void test(Item item1, Item item2) {
        try {
            if (item2.getItemFusion().equals("Cheated") && item1.getComplexe().equals("Cheated")) {
                item2.setItemFusion(item1.getItemFusion());
            }
        } catch (Exception e) {
            nop.stop();
            nop.play();
        }
    }

    public void laCarte() {
        ImageView cadrecarte = new ImageView(new Image(Inventaire.class.getResourceAsStream("image/cadre.png")));
        ImageView map2 = new ImageView(new Image(Inventaire.class.getResourceAsStream("image/map2.png")));
        ImageView map = new ImageView(new Image(Inventaire.class.getResourceAsStream("image/map.png")));
        ImageView carte = new ImageView(new Image(Inventaire.class.getResourceAsStream("image/map2.png")));

        cadrecarte.setFitWidth(120);
        cadrecarte.setFitHeight(120);
        cadrecarte.setX(1280 - 120);
        cadrecarte.setY(720 - 120 - 95);
        getChildren().add(cadrecarte);

        map2.setFitHeight(600);
        map2.setFitWidth(470);
        map2.setX(410);
        map2.setY(20);
        map2.setOnMouseClicked((mouseEvent) -> {
            soncarte1.stop();
            soncarte1.play();
            getChildren().remove(map2);
            getChildren().add(map);
        });
        map.setFitHeight(600);
        map.setFitWidth(470);
        map.setX(410);
        map.setY(20);
        map.setOnMouseClicked((mouseEvent) -> {
            soncarte1.stop();
            soncarte1.play();
            getChildren().remove(map);
            getChildren().add(map2);
        });
        carte.setFitHeight(100);
        carte.setFitWidth(75);
        carte.setX(1280 - 98);
        carte.setY(720 - 205);
        carte.setStyle("-fx-opacity: 0.7;");
        carte.setOnMouseEntered((mouseEvent) -> {
            carte.setStyle("-fx-opacity: 1;");
        });
        carte.setOnMouseExited((mouseEvent) -> {
            carte.setStyle("-fx-opacity: 0.7;");
        });
        carte.setOnMouseReleased((mouseEvent) -> {
            if (!getChildren().contains(map2) && !getChildren().contains(map)) {
                soncarte3.stop();
                soncarte3.play();
                carte.setImage(new Image(Inventaire.class.getResourceAsStream("image/mapclose.png")));
                getChildren().add(map2);
            } else if (!getChildren().contains(map2) && getChildren().contains(map)) {
                soncarte3.stop();
                soncarte3.play();
                carte.setImage(new Image(Inventaire.class.getResourceAsStream("image/map2.png")));
                getChildren().remove(map);
            } else if (!getChildren().contains(map) && getChildren().contains(map2)) {
                soncarte3.stop();
                soncarte3.play();
                carte.setImage(new Image(Inventaire.class.getResourceAsStream("image/map2.png")));
                getChildren().remove(map2);
            }
        });
        getChildren().add(carte);
    }

    public void ajouterItemInventaire(Item item) {
        itemsActuels[placeIventaire()] = item;
        labarre();
        refreshInventaire();
    }

    public int placeIventaire() {
        int i = 0;
        while (itemsActuels[i] != null && i != 12) {
            i++;
        }
        return i;
    }

    public void supprimer_item(Item item) {
        for (int i = getNumItem(item); i < 11; i++) {
            itemsActuels[i] = itemsActuels[i + 1];
        }
        itemsActuels[11] = null;
    }

    public int getNumItem(Item item) {
        int i = 0;
        while (!item.equals(itemsActuels[i]) && i != 11) {
            i++;
        }
        return i;
    }

    public Item getItemSelection() {
        return itemSelectionné;
    }

    public void setItemSelection(Item item) {
        itemSelectionné = item;
    }
}


