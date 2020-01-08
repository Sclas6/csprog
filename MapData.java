import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
* Mapのデータに関するクラスです。
*/
public class MapData {
    public static final int TYPE_NONE   = 0;
    public static final int TYPE_WALL   = 1;
    public static final int TYPE_OTHERS = 2;
    public static final int TYPE_GOAL = 3;
    private static final String mapImageFiles[] = {
        "png/SPACE.png",
        "png/WALL.png",
        "png/ITEM.png",
        "png/GOAL.png"
    };

    private Image[] mapImages;
    private ImageView[][] mapImageViews;
    public int[][] maps;
    private int width;
    private int height;

    MapData(int x, int y){
        mapImages     = new Image[4];
        mapImageViews = new ImageView[y][x];
        for (int i=0; i<4; i++) {

        mapImages[i] = new Image(mapImageFiles[i]);}

        width  = x;
        height = y;
        maps = new int[y][x];

        fillMap(MapData.TYPE_WALL);
        digMap(1, 3);
        itemMap();
        setMap(19,13,MapData.TYPE_GOAL);
        setImageViews();
    }
    /**
      * 高さ（Y）座標を返すメソッドです。
      * @return 高さ
      */
    public int getHeight(){
        return height;
    }
    /**
      * 幅（X）座標を返すメソッドです。
      * @return 幅
      */
    public int getWidth(){
        return width;
    }
    /**
      *ある座標に関してのgetterです。
      * @return maps[y][x]
      */
    public int getMap(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y) {
            return -1;
        }
        return maps[y][x];
    }

    public ImageView getImageView(int x, int y) {
        return mapImageViews[y][x];
    }
    /**
      *ある座標に関してのsetterです。
      */
    public void setMap(int x, int y, int type){
        if (x < 1 || width <= x-1 || y < 1 || height <= y-1) {
            return;
        }
        maps[y][x] = type;
    }

    public void setImageViews() {
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                mapImageViews[y][x] = new ImageView(mapImages[maps[y][x]]);
            }
        }
    }

    /**
      *壁（行き止まり）の画像でマップを埋めるメソッドです。
      */
    public void fillMap(int type){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                maps[y][x] = type;
            }
        }
    }
    /**
      *道を掘るメソッドです。
      */
    public void digMap(int x, int y){
        setMap(x, y, MapData.TYPE_NONE);
        int[][] dl = {{0,1},{0,-1},{-1,0},{1,0}};
        int[] tmp;

        for (int i=0; i<dl.length; i++) {
            int r = (int)(Math.random()*dl.length);
            tmp = dl[i];
            dl[i] = dl[r];
            dl[r] = tmp;
        }

        for (int i=0; i<dl.length; i++){
            int dx = dl[i][0];
            int dy = dl[i][1];
            if (getMap(x+dx*2, y+dy*2) == MapData.TYPE_WALL){
                setMap(x+dx, y+dy, MapData.TYPE_NONE);
                digMap(x+dx*2, y+dy*2);

            }
        }
    }
    /**
      *アイテムをランダムに道に配置するメソッドです。
      */
  public void itemMap(){
    int i=0;
    int count=3;
    while(i<count){
      int x = (int)(Math.random()*19);
      int y = (int)(Math.random()*13);//x座標、y座標を乱数で指定します

       if(x!=2&y!=1){  //スタート位置にアイテムを設置したくなかったです
        if (getMap(x, y) == MapData.TYPE_NONE){
            setMap(x, y, MapData.TYPE_OTHERS);
            i++;

        }

      }
    }
 }
    /*public void printMap(){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                if (getMap(x,y) == MapData.TYPE_WALL){
                    System.out.print("++");
                }else if(getMap(x,y) == MapData.TYPE_NONE){
                    System.out.print("  ");
                }else{
                    System.out.print("item");
                }
            }
            System.out.print("\n");
        }
    }*/
}
