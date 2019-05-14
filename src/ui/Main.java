package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Event;

public class Main extends Application {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Event event = new Event("data/event.csv");
		ArrayList<model.Attendee> cdcd = event.preorder();
		
		System.out.println("\nAñadidos desde el archivo de texto");
		for(int i = 0; i < cdcd.size(); i++) {
			System.out.println(cdcd.get(i).getId());
		}
		event.cortarArbol();
		for(int i = 0; i < cdcd.size(); i++) {
			cdcd.get(i).setLeft(null);
			cdcd.get(i).setRight(null);
			event.addAttendee(cdcd.get(i).getId(), cdcd.get(i).getFirstName(), cdcd.get(i).getLastName(), cdcd.get(i).getEmail(), cdcd.get(i).getGender(), cdcd.get(i).getCountry(), cdcd.get(i).getPhoto(), cdcd.get(i).getBirthday());
		}
		cdcd = event.preorder();
		System.out.println("\n\nAñadidos desde un arreglo");
		for(int i = 0; i < cdcd.size(); i++) {
			System.out.println(cdcd.get(i).getId());
		}
		System.exit(0);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
