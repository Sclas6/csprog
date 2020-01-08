/**
*AudioClipを用いた音声再生
*@author MrRadiology(ブログ、学生向けプログラミング入門作者)様
*/
import java.applet.*;
import java.net.*;

public class PlayAudio {

	private URL url;
	private AudioClip audioClip;

	public PlayAudio(String fileName) {
		fileLoad(fileName);
	}

	/**指定ファイルでAudioClipを生成*/
	public void fileLoad(String fileName) {

		try {
			url = new URL("file:" +
			System.getProperty("user.dir") + "/" + fileName);
		}

		catch (MalformedURLException ex) {
			System.err.println(ex);
		}

		//AudioClipの生成
		audioClip = Applet.newAudioClip(url);
	}

	/**再生*/
	public void play() {
		audioClip.play();
	}

	/**ループ*/
	public void loop() {
		audioClip.loop();
	}

	/**停止*/
	public void stop() {
		audioClip.stop();
	}

	public static void main(String[] args) {
		PlayAudio test = new PlayAudio("game_maoudamashii_4_field10.mid");
		test.loop();
	}
}
