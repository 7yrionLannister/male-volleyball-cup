package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws IOException {
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
