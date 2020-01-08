import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.applet.*;
import java.net.*;


/**
 * このクラスを実行するとゲームが起動します。
 */
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
    }
    /**
         * mainメソッドです。
         *PlayAudioでBGMを読み込んでいます。
         * @param args 使用しません。
         */
    public static void main(String[] args) {
      PlayAudio test = new PlayAudio("game_maoudamashii_4_field10.mid");
      test.loop();
        launch(args);
    }
}
