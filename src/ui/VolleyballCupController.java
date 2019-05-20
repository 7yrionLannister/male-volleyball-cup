package ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Attendee;
import model.Event;

//TODO consider using System.nanoTime instead System.currentTimeMillis
public class VolleyballCupController {

	public final static String DEFAULT_MESSAGE = "...";

	private Event cup;

	@FXML
	private ImageView foundPhoto;

	@FXML
	private Label idLabel;

	@FXML
	private Label firstNameLabel;

	@FXML
	private Label lastNameLabel;

	@FXML
	private Label emailLabel;

	@FXML
	private Label genderLabel;

	@FXML
	private Label countryLabel;

	@FXML
	private Label birthdayLabel;

	@FXML
	private TextField pathTextField;

	@FXML
	private Label loadDataMessageLabel;

	@FXML
	private TextField attendeeSearchTextField;

	@FXML
	private Label searchAttendeeTimeLabel;

	@FXML
	private Label searchAttendeeMessageLabel;

	@FXML
	private TextField participantSearchTextField;

	@FXML
	private Label searchParticipantTimeLabel;

	@FXML
	private Label searchParticipantMessageLabel;

	@FXML
	public void initialize() {
		try {
			cup = new Event("data/event.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void chooseButtonPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("CSV Files", "*.csv"));
		File choice = fc.showOpenDialog(loadDataMessageLabel.getScene().getWindow());
		if(choice != null) {
			pathTextField.setText(choice.toString());
		}
	}

	@FXML
	public void loadButtonPressed(ActionEvent event) {
		File chose = new File(pathTextField.getText());
		if(chose.exists()) {
			try {
				cup.refreshAttendees(pathTextField.getText());
				loadDataMessageLabel.setText("Attendees and participants have been\nsuccessfully loaded");
			} catch (IOException|StackOverflowError e) {
				loadDataMessageLabel.setText("Attendees and participants could'nt be\nloaded");
			}
		} else {
			loadDataMessageLabel.setText("Attendees and participants could'nt be\nloaded");
		}
	}

	@FXML
	public void searchAttendeePressed(ActionEvent event) {
		long start = System.currentTimeMillis();
		Attendee found = cup.searchAttendee(attendeeSearchTextField.getText());
		long time = System.currentTimeMillis() -start;
		searchAttendeeTimeLabel.setText("Time: " + time + " ms");
		if(found == null) {
			idLabel.setText(DEFAULT_MESSAGE);
			firstNameLabel.setText(DEFAULT_MESSAGE);
			lastNameLabel.setText(DEFAULT_MESSAGE);
			emailLabel.setText(DEFAULT_MESSAGE);
			countryLabel.setText(DEFAULT_MESSAGE);
			genderLabel.setText(DEFAULT_MESSAGE);
			birthdayLabel.setText(DEFAULT_MESSAGE);
			foundPhoto.setImage(null);
			searchAttendeeMessageLabel.setText("The attendee with id "+ attendeeSearchTextField.getText() +" was not found");
		} else {
			idLabel.setText(found.getId());
			firstNameLabel.setText(found.getFirstName());
			lastNameLabel.setText(found.getLastName());
			emailLabel.setText(found.getEmail());
			countryLabel.setText(found.getCountry());
			genderLabel.setText(found.getGender());
			birthdayLabel.setText(found.getBirthday().toString());
			foundPhoto.setImage(new Image(found.getPhoto()));
			searchAttendeeMessageLabel.setText("The attendee with id "+ attendeeSearchTextField.getText() +" was found");
		}
	}

	@FXML
	public void searchParticipantPressed(ActionEvent event) {
		long start = System.currentTimeMillis();
		Attendee found = cup.searchParticipant(participantSearchTextField.getText());
		long time = System.currentTimeMillis() -start;
		searchParticipantTimeLabel.setText("Time: " + time + " ms");
		if(found == null) {
			idLabel.setText(DEFAULT_MESSAGE);
			firstNameLabel.setText(DEFAULT_MESSAGE);
			lastNameLabel.setText(DEFAULT_MESSAGE);
			emailLabel.setText(DEFAULT_MESSAGE);
			countryLabel.setText(DEFAULT_MESSAGE);
			genderLabel.setText(DEFAULT_MESSAGE);
			birthdayLabel.setText(DEFAULT_MESSAGE);
			foundPhoto.setImage(null);
			searchParticipantMessageLabel.setText("The participant with id "+ participantSearchTextField.getText() +" was not found");
		} else {
			idLabel.setText(found.getId());
			firstNameLabel.setText(found.getFirstName());
			lastNameLabel.setText(found.getLastName());
			emailLabel.setText(found.getEmail());
			countryLabel.setText(found.getCountry());
			genderLabel.setText(found.getGender());
			birthdayLabel.setText(found.getBirthday().toString());
			foundPhoto.setImage(new Image(found.getPhoto()));
			searchParticipantMessageLabel.setText("The participant with id "+ participantSearchTextField.getText() +" was found");
		}
	}
}

