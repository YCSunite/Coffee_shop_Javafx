import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CoffeeShop extends Application implements EventHandler {
	// the buttons on the left pane of the scene
	Button newOrder;
	Button addSugarBtn;
	Button addCreamBtn;
	Button addExtraShotBtn;
	Button addPumpkinSpiceBtn;
	Button addCaramelBtn;
	Button doneBtn;
	Label errorLabel;
	VBox controlsArea;		// the container of the buttons on left pane of scene

	ListView mainArea;		// the whole right pane of the screen

	Coffee customerOrder;
	OrderModel orderModel;
	ArrayList<OrderModel> orderModels =  new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Who want's coffee!!!");

		Scene scene = new Scene(new HBox(),600,600);
		primaryStage.setScene(scene);
		primaryStage.show();

		HBox parent = (HBox) scene.getRoot();
		parent.setStyle("-fx-font: normal bold 20px 'serif'");

		// create controls area
		controlsArea = new VBox();
		controlsArea.setSpacing(10);
		controlsArea.setPadding(new Insets(16,8,8,8));


		includeButtons(parent);		// include buttons in control area

		// create main area
		mainArea = new ListView();
		mainArea.setMinHeight(600);
		mainArea.setMinWidth(460);

		// add to parent node (HBox)
		parent.getChildren().addAll(controlsArea, mainArea);

		Coffee order = new Sugar(new Cream( new ExtraShot(new BasicCoffee())));
		double cost = order.makeCoffee();

		System.out.println("Total: "+cost);
	}

	public void includeButtons(HBox parent){
		// get parent node from scene
		parent.setBackground(new Background(new BackgroundFill(Color.ROSYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		parent.setStyle("-fx-font: normal bold 20px 'serif'");

		// create and add buttons to controls area
		newOrder = new Button("New Order");
		newOrder.setOnAction(this);
		Box emptyBox = new Box(0, 40, 0);		// empty box for spacing
		addSugarBtn = new Button("Add Sugar");
		addSugarBtn.setOnAction(this);
		addCaramelBtn = new Button("Add Caramel");
		addCaramelBtn.setOnAction(this);
		addCreamBtn = new Button("Add Cream");
		addCreamBtn.setOnAction(this);
		addCaramelBtn.setOnAction(this);
		addExtraShotBtn = new Button("Add Extra shot");
		addExtraShotBtn.setOnAction(this);
		addPumpkinSpiceBtn = new Button("Add Pumpkin spice");
		addPumpkinSpiceBtn.setOnAction(this);
		Box emptyBox2 = new Box(0, 40, 0);		// empty box for spacing
		doneBtn = new Button("Done");
		doneBtn.setOnAction(this);
		errorLabel = new Label("No Order Created");
		// set up error text
		errorLabel.setTextFill(Color.DARKRED);
		errorLabel.setVisible(false);

		controlsArea.getChildren().addAll(newOrder, emptyBox, addSugarBtn, addCaramelBtn, addCreamBtn, addExtraShotBtn, addPumpkinSpiceBtn, emptyBox2, doneBtn, errorLabel);
	}

	@Override
	public void handle(Event event) {
		// show error message if there is no existing order to add to
		if (customerOrder == null && event.getSource() != newOrder){
			errorLabel.setVisible(true);
			return;
		}
		if (event.getSource() == newOrder){		// initialize variables for a new order
			customerOrder = new BasicCoffee();
			orderModel = new OrderModel(orderModels.size());
			System.out.println("created new coffee");
		}
		else if (event.getSource() == addSugarBtn){
			customerOrder = new Sugar(customerOrder);
			orderModel.addItem("sugar", customerOrder.getInitialCost());
			System.out.println("added sugar");

		}
		else if (event.getSource() == addCaramelBtn){
			customerOrder = new Caramel(customerOrder);
			orderModel.addItem("caramel", customerOrder.getInitialCost());
			System.out.println("added caramel");

		}
		else if (event.getSource() == addCreamBtn){
			customerOrder = new Cream(customerOrder);
			orderModel.addItem("cream", customerOrder.getInitialCost());
			System.out.println("added cream");

		}
		else if (event.getSource() == addExtraShotBtn){
			customerOrder = new ExtraShot(customerOrder);
			orderModel.addItem("extrashot", customerOrder.getInitialCost());
		}
		else if (event.getSource() == addPumpkinSpiceBtn){
			customerOrder = new PumpkinSpice(customerOrder);
			orderModel.addItem("pumpkinspice", customerOrder.getInitialCost());

		}
		else if (event.getSource() == doneBtn){
			orderModel.addItem("total", customerOrder.makeCoffee());		// add total label
			orderModel.getDeleteButton().addEventHandler(MouseEvent.MOUSE_CLICKED, this);	// set delete button listener
			orderModels.add(orderModel);
			customerOrder = new BasicCoffee();
			orderModel = new OrderModel(orderModels.size());
		}
		else if( (((ImageView) event.getSource()).getId() ).contains("del_")){	// if delete button is pressed
			int posToDelete = Integer.valueOf((((ImageView) event.getSource()).getId()).substring(4));
			System.out.println("pos is "+ posToDelete);
			orderModels.remove(posToDelete);
		}

		errorLabel.setVisible(false);
		loadListViewItems();

	}


	public void loadListViewItems(){
		mainArea.getItems().clear();
		int count = 0;
		for (OrderModel orderModel : orderModels){
			// re-assign ids to delete buttons
			orderModel.getDeleteButton().setId("del_"+String.valueOf(count));
			mainArea.getItems().add(orderModel.getVisual());
			count++;
		}
		System.out.println("renewed list");
	}
}
