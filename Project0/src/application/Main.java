package application;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	// the array
	MyList<Martyr> list = new MyList<Martyr>(16);
	// FFile choser
	FileChooser fileChooser = new FileChooser();
	// method to Read data from the file and add it to the array

	private void readDataFromFileAndAddToList(File fileExel) throws IOException {
		try (Scanner scannerTheFile = new Scanner(fileExel)) {
			// Skip the first line if it contains headers
			if (scannerTheFile.hasNextLine()) {
				scannerTheFile.nextLine();
			}
			// Now start processing the actual data
			while (scannerTheFile.hasNextLine()) {
				String D = scannerTheFile.nextLine();
				String[] AddtoArray = D.split(",");
				if (AddtoArray.length >= 5) {

					String name = AddtoArray[0];
					int age;
					try {
						age = Integer.parseInt(AddtoArray[1]);
						if (age < 0) {
							System.out.println("Invalid age: ");
							continue; // Skip this line and move to the next one
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid age format:");
						continue; // Skip this line and move to the next one
					}
					String eventLocation = AddtoArray[2].trim(); // Remove leading/trailing whitespace
					if (eventLocation.isEmpty()) {
						System.out.println("Event location is empty");
						continue; // Skip this line and move to the next one
					}
					Date dateOfMartyr = null;
					try {
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						dateOfMartyr = dateFormat.parse(AddtoArray[3]);
					} catch (ParseException e) {
						System.out.println("Invalid date format: " + AddtoArray[3]);
						continue; // Skip this line and move to the next one
					}
					char gender = AddtoArray[4].charAt(0);

					// Create a new Martyr object and add it to the list
					Martyr martyr = new Martyr(name, age, eventLocation, dateOfMartyr, gender);
					list.Add(martyr);
				} else {
					System.out.println("Invalid data format:");
				}
			}
		}
	}

//.......................................
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

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
					Date aDate = dateFormat.parse(addDate.getText().trim());
					char aGender = aGenderString.charAt(0);
					if (aGenderString.equals("M") || aGenderString.equals("F")) {

						Martyr M = new Martyr(aName, finalAAge, aEventlocation, aDate, aGender);
						list.Add(M);
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
		Label seralarm = new Label();
		h2.getChildren().addAll(lName, nameToSer, search);

		Button back2 = new Button("back");
		Button clear1 = new Button("clear");

		// the pane in the scene
		v2.getChildren().addAll(h2, resalt, seralarm, clear1, back2);

		Scene s2 = new Scene(v2, 300, 300);

		searchMartyrByName.setOnAction(e -> { // event of search Martyr By Name
			primaryStage.setScene(s2);
			primaryStage.show();

			// .....
		});
		search.setOnAction(e -> {
			String serName = nameToSer.getText();
			if (serName.isEmpty()) {
				seralarm.setText("pleas enter name !");
			} else {

				Martyr Mname = new Martyr(serName);
				Martyr foundMartyr = list.gettheobj(list.find(Mname));// Retrieve the martyr object from the list

				// Display information about the found martyr
				resalt.setText("Found Martyr: " + foundMartyr.toString());

			}

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

			Martyr Mname = new Martyr(deln);

			if (list.delete(Mname) == false) {
				de.setText("the name dose nut esist");

			} else {
				de.setText("The martyr has been deleted");
			}
		});
		clear2.setOnAction(e -> {
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
		HNum1.getChildren().addAll(Agenum, Agenu, search1);

		HBox HNum2 = new HBox(20);
		HNum2.setAlignment(Pos.CENTER);
		Label Gendernum = new Label("Gender:");
		TextField Gendernu = new TextField();
		Button search2 = new Button("search");
		HNum2.getChildren().addAll(Gendernum, Gendernu, search2);

		HBox HNum3 = new HBox(20);
		HNum3.setAlignment(Pos.CENTER);
		Label Datenum = new Label("Date :");
		TextField Datenu = new TextField();
		Button search3 = new Button("search");
		HNum3.getChildren().addAll(Datenum, Datenu, search3);

		Label resultnumber = new Label("   "); // the reselt will be in the label

		Button back4 = new Button("back");
		Button clear3 = new Button("clear");

		// the pane on the scene
		VNum.getChildren().addAll(HNum1, HNum2, HNum3, resultnumber, clear3, back4);

		Scene s4 = new Scene(VNum, 300, 300);
		// ......
		displayTheNumberOfMartyrsBy.setOnAction(e -> { // event of display The Number Of Martyrs By
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
				Martyr MarAge = new Martyr(castSumAge);
				int outputsumAge = (list.NumOf(MarAge));
				resultnumber.setText("Number of martyrs" + outputsumAge);

			}
		});
		// ......
		search2.setOnAction(e -> {
			if (Gendernu.getText().isEmpty()) {
				resultnumber.setText("pleas enter the Gender");
			}

			else if (Gendernu.getText().length() != 1) {
				resultnumber.setText("Please enter only one character for gender");
			} else {
				String SumGender = Gendernu.getText().trim();
				char castSumGender = SumGender.charAt(0);
				if (SumGender.equals("M") || SumGender.equals("F")) {
					Martyr MarGender = new Martyr(castSumGender);
					int outputStringGender = (list.NumOf(MarGender));
					resultnumber.setText("Number of martyrs" + outputStringGender);

				} else {
					System.out.println(" pleas enter M or F");
				}
			}
		});
		// ......

		search3.setOnAction(e -> {
			if (Datenu.getText().isEmpty()) {
				resultnumber.setText("Please enter the date");
			} else {
				String inputDateStr = Datenu.getText().trim();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
				try {
					Date inputDate = dateFormat.parse(inputDateStr);
					Martyr m = new Martyr(inputDate);
					int numOfMartyrs = list.NumOf(m); // Assuming list is an ArrayList<Martyr>
					resultnumber.setText("Number of martyrs on " + numOfMartyrs);
				} catch (ParseException ex) {
					resultnumber.setText("Invalid date format");
					ex.printStackTrace();
				}
			}
		});
		// ......
		back4.setOnAction(e -> { // event of back button
			primaryStage.setScene(s);
		});
		clear3.setOnAction(e -> {
			Agenu.clear();
			Gendernu.clear();
			Datenu.clear();
			resultnumber.setText(null);
		});

//...............................................
//		 file choser event
		chose.setOnAction(e -> {
			// Set title for the FileChooser dialog
			fileChooser.setTitle("Select Excel File");
			fileChooser.setInitialDirectory(new File("c:\\"));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null && selectedFile.exists()) {

				try {
					readDataFromFileAndAddToList(selectedFile);
				} catch (IOException ex) {
					System.out.println(" erorrr!!");
				}
			}

		});

		System.out.println(list.toString());
//........................................................................	
	}

	public static void main(String[] args) {
		launch(args);
	}
}