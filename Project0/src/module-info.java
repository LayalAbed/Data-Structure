module Project0 {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
}
