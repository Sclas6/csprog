import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Group;
import java.applet.*;
import java.net.*;
/**
* ゲームをコントロールするクラスです。
*
*/
public class MapGameController implements Initializable {
  public MapData mapData;
  public MoveChara chara;
  public GridPane mapGrid;
  public ImageView[] mapImageViews;
  int stage=1;
  int makimono=0;
  //    public Group[] mapGroups;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    mapData = new MapData(21,15);
    chara = new MoveChara(1,1,mapData);
    //        mapGroups = new Group[mapData.getHeight()*mapData.getWidth()];
    mapImageViews = new ImageView[mapData.getHeight()*mapData.getWidth()];
    for(int y=0; y<mapData.getHeight(); y++){
      for(int x=0; x<mapData.getWidth(); x++){
        int index = y*mapData.getWidth() + x;
        mapImageViews[index] = mapData.getImageView(x,y);
      }
    }
    mapPrint(chara, mapData);
  }
  /**
  *アイテム画像→更地のマップチップに変える処理を行うメソッドです。
  */
  public void mapchange(){
    mapData.setMap(chara.getPosX(),chara.getPosY(),MapData.TYPE_NONE);
    mapData.setImageViews();
    for(int y=0; y<mapData.getHeight(); y++){
      for(int x=0; x<mapData.getWidth(); x++){
        int index = y*mapData.getWidth() + x;
        mapImageViews[index] = mapData.getImageView(x,y);
      }
    }
  }
  /**
  *新マップを生成するメソッドです。
  */
   public void StageChange(){
     mapData = new MapData(21,15);
     chara = new MoveChara(1,1,mapData);
     //        mapGroups = new Group[mapData.getHeight()*mapData.getWidth()];
     mapImageViews = new ImageView[mapData.getHeight()*mapData.getWidth()];
     for(int y=0; y<mapData.getHeight(); y++){
       for(int x=0; x<mapData.getWidth(); x++){
         int index = y*mapData.getWidth() + x;
         mapImageViews[index] = mapData.getImageView(x,y);
       }
     }
     mapPrint(chara, mapData);
}

/**
*実際の画面に画像を表示させるメソッドです。
*/
  public void mapPrint(MoveChara c, MapData m){
    int cx = c.getPosX();
    int cy = c.getPosY();
    mapGrid.getChildren().clear();
    for(int y=0; y<mapData.getHeight(); y++){
      for(int x=0; x<mapData.getWidth(); x++){
        int index = y*mapData.getWidth() + x;
        if (x==cx && y==cy) {
          mapGrid.add(c.getCharaImageView(), x, y);
        }else {
          mapGrid.add(mapImageViews[index], x, y);
        }
      }
    }
  }

  public void func1ButtonAction(ActionEvent event) { }
  public void func2ButtonAction(ActionEvent event) { }
  public void func3ButtonAction(ActionEvent event) { }
  public void func4ButtonAction(ActionEvent event) { }

  public void keyAction(KeyEvent event){
    KeyCode key = event.getCode();
    if(key == KeyCode.UP){
      upButtonAction();
    }
    else if (key == KeyCode.DOWN){
      downButtonAction();
    }else if (key == KeyCode.RIGHT){
      rightButtonAction();
    }else if (key == KeyCode.LEFT){
      leftButtonAction();
    }
  }
  /**
  *端末に出力するメソッドです。
  */
  public void outputAction(String actionString) {
    System.out.println("Select Action: " + actionString+" x"+chara.getPosX()+" y"+chara.getPosY()+" stage:"+stage);
  }
  @FXML
  	private Label label1;
    /**
    *取得アイテム数を数えるメソッドです。
    */
  public void itemcount(){

    label1.setText(String.valueOf(makimono));
  }
  /**
  *上アクションのメソッドです。
  */
  public void upButtonAction(){
    outputAction("UP");
    chara.setCharaDir(MoveChara.TYPE_UP);
    chara.move( 0, -1);
    if(mapData.getMap(chara.getPosX(),chara.getPosY())==MapData.TYPE_OTHERS){
      PlayAudio test = new PlayAudio("free_sound17.wav");
      test.play();
      mapchange();
      makimono++;
      itemcount();
    }
    mapPrint(chara, mapData);
    if(chara.getPosX()==19&chara.getPosY()==13&makimono==3*stage){stage++;
       StageChange();
       }

  }
  public void upButtonAction(ActionEvent event) {
    upButtonAction();
  }
  /**
  *下アクションのメソッドです。
  */
  public void downButtonAction(){
    outputAction("DOWN");
    chara.setCharaDir(MoveChara.TYPE_DOWN);
    chara.move(0, 1);
    if(mapData.getMap(chara.getPosX(),chara.getPosY())==MapData.TYPE_OTHERS){
      PlayAudio test = new PlayAudio("free_sound17.wav");
      test.play();
     mapchange();
      makimono++;
      itemcount();
    }
    mapPrint(chara, mapData);
    if(chara.getPosX()==19&chara.getPosY()==13&makimono==3*stage){stage++;
       StageChange();
       }
 }
  public void downButtonAction(ActionEvent event) {
    downButtonAction();
  }
  /**
  *右アクションのメソッドです。
  */
  public void rightButtonAction(){
    outputAction("RIGHT");
    chara.setCharaDir(MoveChara.TYPE_RIGHT);
    chara.move( 1, 0);
    if(mapData.getMap(chara.getPosX(),chara.getPosY())==MapData.TYPE_OTHERS){
      PlayAudio test = new PlayAudio("free_sound17.wav");
      test.play();
      mapchange();
      makimono++;
      itemcount();
    }
    mapPrint(chara, mapData);
    if(chara.getPosX()==19&chara.getPosY()==13&makimono==3*stage){stage++;
       StageChange();
       }

  }
  public void rightButtonAction(ActionEvent event) {
    rightButtonAction();}
    /**
    *左アクションのメソッドです。
    */
  public void leftButtonAction(){
    outputAction("LEFT");
    chara.setCharaDir(MoveChara.TYPE_LEFT);
    chara.move( -1, 0);
    if(mapData.getMap(chara.getPosX(),chara.getPosY())==MapData.TYPE_OTHERS){
      PlayAudio test = new PlayAudio("free_sound17.wav");
      test.play();
      mapchange();
      makimono++;
      itemcount();
    }
    mapPrint(chara, mapData);
    if(chara.getPosX()==19&chara.getPosY()==13&makimono==3*stage){stage++;
       StageChange();
       }

  }
  public void leftButtonAction(ActionEvent event) {
    leftButtonAction();
  }
}
