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
		ArrayList<String> cdcd = new ArrayList<String>();
		event.printBST(cdcd);
		System.out.println("\n");
		for(int i = 0; i < cdcd.size(); i++) {
			//boolean b = cdcd.get(i-1).compareTo(cdcd.get(i)) <= 0;
			//System.out.println(b);
			//if(!b) {
			//	System.out.println(cdcd.get(i-1) + "::"+cdcd.get(i));
			//}
			System.out.println(cdcd.get(i));
		}
		System.out.println("*** "+("00-2252884".compareTo("00-5972239")<=0));
		System.exit(0);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
