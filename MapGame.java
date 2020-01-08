import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

//BGM
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MapGame extends Application {
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
	stage = primaryStage;
	primaryStage.setTitle("MAP GAME");
	Pane myPane_top = (Pane)FXMLLoader.load(getClass().getResource("MapGame.fxml"));
	Scene myScene = new Scene(myPane_top);
	primaryStage.setScene(myScene);
	primaryStage.show();

    //BGM
    primaryStage.show();
    Media m = new Media(new File("loop1.wav").toURI().toString());
    MediaPlayer mp = new MediaPlayer(m);
    mp.play();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
