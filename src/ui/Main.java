package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Attendee;
import model.Event;

public class Main extends Application {

	public static void main(String[] args) throws IOException {
		Event e = new Event("data/event.csv");
		ArrayList<Attendee> pre = e.preorder();
		for(Attendee c: pre) {
			System.out.println(c);
		}
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("volleyball.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Pan-American male volleyball cup under-21");
		stage.show();
	}

}
