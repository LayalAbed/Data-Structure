package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main extends Application {
	// the array
	MyList<Martyr> list = new MyList<>(16);
	FileChooser fileChooser = new FileChooser();

	private void readDataFromFileAndAddToList(File fileExel) throws IOException, ParseException {
		int caunt=0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try (Scanner scannerTheFile = new Scanner(fileExel)) {
			while (scannerTheFile.hasNext()) {
				String line = scannerTheFile.nextLine();
				String[] data = line.split(",");
				if (data.length < 5) {
					System.out.println("Skipping incomplete record: " + line);
					
					continue;
				}

				String name = data[0];
				try {
					int age = Integer.parseInt(data[1]);
					String eventLocation = data[2];
					Date dateOfMartyr = dateFormat.parse(data[3]);
					char gender = data[4].charAt(0);
					if (gender != 'M' && gender != 'F') {
						System.out.println("Invalid gender for record: " + line);
						caunt++;
						continue;
					}

					Martyr martyr = new Martyr(name, age, eventLocation, dateOfMartyr, gender);
					list.add(martyr);
				} catch (NumberFormatException | ParseException e) {
					System.out.println("Invalid data for record: " + line);
					caunt++;
				}
			}
			System.out.println(caunt);
		}
	}
//
	// .......................................
	@Override
	public void start(Stage primaryStage) {

		// the menu stage
		VBox v1 = new VBox(20);
		v1.setAlignment(Pos.CENTER);
		Label start = new Label(" Enter your choese: ");
		Button insertANewMartyr = new Button("insert a new martyr:");
		Button deleteAMartyrByName = new Button("delete Martyr By Name:");
		Button searchMartyrByName = new Button("search Martyr By Name:");
		Button displayTheNumberOfMartyrsBy = new Button("display The Number Of MartyrsBy:");
		Button chose = new Button("select file ");
		v1.getChildren().addAll(start, insertANewMartyr, deleteAMartyrByName, searchMartyrByName,
				displayTheNumberOfMartyrsBy, chose);
		Scene s = new Scene(v1, 300, 300);
		primaryStage.setScene(s);
		primaryStage.show();

//....................................................................
		// insert A New Martyr
		FlowPane flow = new FlowPane();
		flow.setHgap(50);
		Label Name = new Label("Name");
		TextField addName = new TextField();
		Label Age = new Label("Age");
		TextField addAge = new TextField();
		Label Eventlocation = new Label("Event location");
		TextField addEventlocation = new TextField();
		Label Date = new Label("Date of martyr");
		TextField addDate = new TextField();
		Label Gender = new Label("Gender");
		TextField addGender = new TextField();
		Button add = new Button(" add");
		Button back1 = new Button("back");
		Label alarm = new Label("");
		Button clear = new Button("clear");

		// the pane in the scene
		flow.getChildren().addAll(Name, addName, Age, addAge, Eventlocation, addEventlocation, Date, addDate, Gender,
				addGender, add, alarm, clear, back1);

		Scene s1 = new Scene(flow, 300, 300);

		insertANewMartyr.setOnAction(e -> { // event of insert A New Martyr
			primaryStage.setScene(s1);
			primaryStage.show();

		});

		// .......
		add.setOnAction(e -> {
			String aName = addName.getText().trim();
			String aAgeString = addAge.getText().trim();
			String aEventlocation = addEventlocation.getText().trim();
			String aGenderString = addGender.getText().trim();

			if (aName.isEmpty() || aAgeString.isEmpty() || aEventlocation.isEmpty()
					|| addDate.getText().trim().isEmpty() || aGenderString.isEmpty()) {
				alarm.setText("Please enter all data!");
			} else {
				try {
					int finalAAge = Integer.parseInt(aAgeString);

					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					Date aDate = dateFormat.parse(addDate.getText().trim());
					char aGender = aGenderString.charAt(0);
					if (aGenderString.equals("M") || aGenderString.equals("F")) {

						Martyr M = new Martyr(aName, finalAAge, aEventlocation, aDate, aGender);
						list.add(M);
					} else {
						System.out.println(" pleas enter M OR F");
						alarm.setText("pleas enter M OR F");
					}
				} catch (NumberFormatException | ParseException ex) {

					alarm.setText("Error in input data!");
				}
			}
		});
		clear.setOnAction(e -> {
			addName.clear();
			addAge.clear();
			addEventlocation.clear();
			addGender.clear();
			addDate.clear();
			alarm.setText(null);
		});

		// ......
		back1.setOnAction(e -> { // event of back button
			primaryStage.setScene(s);
		});
//...........................................................................

		// search Martyr By Name
		HBox h2 = new HBox(20);
		VBox v2 = new VBox(20);
		v2.setAlignment(Pos.CENTER);
		h2.setAlignment(Pos.CENTER);
		TextField nameToSer = new TextField();
		Label lName = new Label(" Name :");
		Button search = new Button("search");
		Label resalt = new Label();
		h2.getChildren().addAll(lName, nameToSer, search);

		Button back2 = new Button("back");
		Button clear1 = new Button("clear");

		// the pane in the scene
		v2.getChildren().addAll(h2, resalt, clear1, back2);

		Scene s2 = new Scene(v2, 300, 300);

		searchMartyrByName.setOnAction(e -> { // event of search Martyr By Name
			primaryStage.setScene(s2);
			primaryStage.show();

			// .....
		});
		search.setOnAction(e -> {
			String serName = nameToSer.getText();
			if (serName.isEmpty()) {
				resalt.setText("pleas enter name !");
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (serName.equals(list.get(i).getName())) {
						resalt.setText("Found Martyr: " + list.get(i).toString());// Display information about the found
																					// martyr
				
				}
			}}

		});
		clear1.setOnAction(e -> {
			nameToSer.clear();
			resalt.setText(null);
		});

		// ....
		back2.setOnAction(e -> { // event of back button
			primaryStage.setScene(s);
		});
//.........................................................................

		// delete A Martyr By Name
		HBox h3 = new HBox(20);
		VBox v3 = new VBox(20);
		v3.setAlignment(Pos.CENTER);
		h3.setAlignment(Pos.CENTER);
		TextField nameToDel = new TextField();
		Label lNametoDel = new Label(" Name :");
//		Label delalarm = new Label();
		Button deleat = new Button("deleat");
		Label de = new Label();
		h3.getChildren().addAll(lNametoDel, nameToDel, deleat);

		Button back3 = new Button("back");
		Button clear2 = new Button("clear");

		// the pane in the scene
		v3.getChildren().addAll(h3, de, clear2, back3);

		Scene s3 = new Scene(v3, 300, 300);

		deleteAMartyrByName.setOnAction(e -> { // event of delete A Martyr By Name
			primaryStage.setScene(s3);
			primaryStage.show();
		});
		// ............
		back3.setOnAction(e -> { // event of back button
			primaryStage.setScene(s);
		});
		// ...........
		deleat.setOnAction(e -> {

			String deln = nameToDel.getText(); // get the name

			if (deln.isEmpty()) {
				de.setText("please enter name !");
			}

			for (int i = 0; i < deln.length(); i++) {
				if (deln.charAt(i) >= '0' && deln.charAt(i) <= '9') {
					de.setText("please enter a valid name");
					return;
				}

			}

			if (deln.isEmpty()) {
				de.setText("the name dose nut esist");

			} else {
				for (int i = 0; i < list.size(); i++) {
					if (deln.equals(list.get(i).getName())) {
						list.delete(list.get(i));
						de.setText("the martyr has been deleted");
					}
				}
			}
		});
		clear2.setOnAction(e ->

		{
			nameToDel.clear();
			de.setText(null);
		});
//........................................................................

		// display The Number Of Martyrs By
		VBox VNum = new VBox(20);
		VNum.setAlignment(Pos.CENTER);

		HBox HNum1 = new HBox(20);
		HNum1.setAlignment(Pos.CENTER);
		Label Agenum = new Label("Age :");
		TextField Agenu = new TextField();
		Button search1 = new Button("search");
		HNum1.getChildren().

				addAll(Agenum, Agenu, search1);

		HBox HNum2 = new HBox(20);
		HNum2.setAlignment(Pos.CENTER);
		Label Gendernum = new Label("Gender:");
		TextField Gendernu = new TextField();
		Button search2 = new Button("search");
		HNum2.getChildren().

				addAll(Gendernum, Gendernu, search2);

		HBox HNum3 = new HBox(20);
		HNum3.setAlignment(Pos.CENTER);
		Label Datenum = new Label("Date :");
		TextField Datenu = new TextField();
		Button search3 = new Button("search");
		HNum3.getChildren().

				addAll(Datenum, Datenu, search3);

		Label resultnumber = new Label("   "); // the reselt will be in the label

		Button back4 = new Button("back");
		Button clear3 = new Button("clear");

		// the pane on the scene
		VNum.getChildren().

				addAll(HNum1, HNum2, HNum3, resultnumber, clear3, back4);

		Scene s4 = new Scene(VNum, 300, 300);
		// ......
		displayTheNumberOfMartyrsBy.setOnAction(e ->

		{ // event of display The Number Of Martyrs By
			primaryStage.setScene(s4);
			primaryStage.show();
			// ....
		});

		search1.setOnAction(e -> {
			if (Agenu.getText().isEmpty()) {
				resultnumber.setText("pleas enter the age ");
			} else {
				String InputSumAge = Agenu.getText();
				int castSumAge = Integer.parseInt(InputSumAge);
				int count = 0;
				for (int i = 0; i < list.size(); i++) {
					if (castSumAge == list.get(i).getAge()) {
						count++;
					}
				}
				resultnumber.setText("Number of martyrs" + count);

			}
		});
		// ......
		search2.setOnAction(e -> {
			if (Gendernu.getText().isEmpty()) {
				resultnumber.setText("pleas enter the Gender");
			} else if (Gendernu.getText().length() != 1) {
				resultnumber.setText("Please enter only one character for gender");
			} else {

				int count = 0;
				for (int j = 0; j < list.size(); j++) {
					if (Gendernu.getText().charAt(0) == list.get(j).getGender()) {
						count++;
					}
				}
				resultnumber.setText("Number of martyrs" + count);

			}

		});

		search3.setOnAction(e ->

		{
			if (Datenu.getText().isEmpty()) {
				resultnumber.setText("Please enter the date");
			} else {
				String inputDateStr = Datenu.getText().trim();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				try {
					Date inputDate = dateFormat.parse(inputDateStr);
					int count = 0;
					for (int i = 0; i < list.size(); i++) {
						if (inputDate.equals(list.get(i).getDateOfMartyr())) {
							count++;
						}
					}
					// Assuming list is an ArrayList<Martyr>
					resultnumber.setText("Number of martyrs on " + count);
				} catch (ParseException ex) {
					resultnumber.setText("Invalid date format");
					ex.printStackTrace();
				}
			}
		});
		// ......
		back4.setOnAction(e ->

		{ // event of back button
			primaryStage.setScene(s);
		});
		clear3.setOnAction(e ->

		{
			Agenu.clear();
			Gendernu.clear();
			Datenu.clear();
			resultnumber.setText(null);
		});

//...............................................
//		 file choser event
		chose.setOnAction(e ->

		{
			// Set title for the FileChooser dialog
			fileChooser.setTitle("Select Excel File");
			fileChooser.setInitialDirectory(new File("C:\\"));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null && selectedFile.exists()) {

				try {
					readDataFromFileAndAddToList(selectedFile);
				} catch (IOException | ParseException ex) {
					System.out.println(" erorrr!!");
				}
			}

		});

//........................................................................
	}

	public static void main(String[] args) {
		launch(args);
	}
}
