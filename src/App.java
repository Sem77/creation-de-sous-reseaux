import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("com/sem/controller/Ui.fxml"));
		
		stage.setTitle("Découpage Sous Réseaux");
		stage.setResizable(false); 
		
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("style.css").toString());
		
		stage.setScene(scene);
		stage.show();
    }
}
