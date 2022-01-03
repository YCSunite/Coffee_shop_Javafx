import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class OrderModel {
    // mainContainer is each item in listView. It contains leftContainer and rightContainer
    HBox mainContainer = new HBox();
    VBox leftContainer = new VBox();    // the area that contains the images and prices
    VBox rightContainer = new VBox();   // the area that contains the delete button
    ImageView deleteBtn = new ImageView(new Image("del-img.png", 30, 30, true, true));

    public OrderModel(int newPos) {
        // initialize containers and delete button
        mainContainer.setSpacing(160);
        mainContainer.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
        deleteBtn.setId("del_"+String.valueOf(newPos));
        rightContainer.getChildren().add(deleteBtn);
        rightContainer.setPadding(new Insets(25,0,0,0));
        mainContainer.getChildren().addAll(leftContainer, rightContainer);

        addItem("coffee", 3.99);
    }

    public void addItem(String item, double price){
        /* add item to the list list view*/
        HBox hBox = new HBox();     // hBox for each image and price (horizontally aligned)
        hBox.setSpacing(30);

        if (item.toLowerCase().contains("coffee")){
            Image image = new Image("coffee-img.png", 50, 50, true, true);
            ImageView imageView = new ImageView(image);
            hBox.getChildren().add(imageView);
        }
        else if (item.toLowerCase().contains("caramel")){
            Image image = new Image("caramel-img.png", 50, 50, true, true);
            ImageView imageView = new ImageView(image);
            hBox.getChildren().add(imageView);

        }
        else if (item.toLowerCase().contains("cream")){
            System.out.println("contains cream");
            Image image = new Image("cream-img.png", 100, 100, true, true);
            ImageView imageView = new ImageView(image);
            hBox.getChildren().add(imageView);

        }
        else if (item.toLowerCase().contains("shot")){
            Image image = new Image("shot-image.png", 100, 100, true, true);
            ImageView imageView = new ImageView(image);
            hBox.getChildren().add(imageView);

        }
        else if (item.toLowerCase().contains("pumpkin")){
            Image image = new Image("pump-img.png", 100, 100, true, true);
            ImageView imageView = new ImageView(image);
            hBox.getChildren().add(imageView);

        }
        else if (item.toLowerCase().contains("sugar")){
            Image image = new Image("sugar-img.png", 100, 100, true, true);
            ImageView imageView = new ImageView(image);
            hBox.getChildren().add(imageView);

        }
        else if (item.toLowerCase().equals("total")){
            Label totalLabel =  new Label("Total: ");
            hBox.getChildren().add(totalLabel);

        }

        Label priceLabel = new Label(String.valueOf(price));
        priceLabel.setFont(Font.font("Courier New", 15));
        hBox.getChildren().add(priceLabel);   // add the price to the HBox
        leftContainer.getChildren().add(hBox);
    }
    public HBox getVisual(){        // the HBox that will be displayed in listView
        return mainContainer;
    }
    public ImageView getDeleteButton(){     // this enables us set the deleteButton's EventListener elsewhere
        return deleteBtn;
    }
}
