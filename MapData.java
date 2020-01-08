import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MapData {
    public static final int TYPE_NONE   = 0;
    public static final int TYPE_WALL   = 1;
    public static final int TYPE_ITEM   = 2;
    public static final int TYPE_GOAL = 3;
    public static final int TYPE_OTHER = 4;
    private static final String mapImageFiles[] = {
        "png/SPACE.png",
        "png/WALL.png",
        "png/item1.png",
        "png/goal.png",
        "png/SPACE.png"
    };

    private Image[] mapImages;
    private ImageView[][] mapImageViews;
    private int[][] maps;
    private int width;
    private int height;
    private int itemcount = 0;
    MapData(int x, int y){
        mapImages     = new Image[5];
        mapImageViews = new ImageView[y][x];
        for (int i=0; i<5; i++) {
            mapImages[i] = new Image(mapImageFiles[i]);
        }

        width  = x;
        height = y;
        maps = new int[y][x];

        fillMap(MapData.TYPE_WALL);
        digMap(1, 3);
        ITEM();
        setMap(19, 13 , 3);
        setImageViews();
        printMap();
    }

    public void setItems(int x , int y, int type){
        mapImageViews[y][x] = new ImageView(mapImages[type]);
    }

    public void take_Item(int x, int y ){
        if (getMap(x, y) == MapData.TYPE_ITEM){

            setMap(x, y, MapData.TYPE_OTHER);
            itemcount++;
            System.out.println("取得数： " + itemcount);

            setImageViews();
            printMap();

            if(itemcount < 3){
                int rest = 3 - itemcount;
                System.out.println("あと" + rest + "個");
            } else {
                System.out.println("ゴール可能になった");
            }
        }
    }

    public int getItemcount(){
        return itemcount;
    }

    public void ITEM(){
        for(int i = 0; i < 3; i++){
            int rx;
            int ry;
            do{
                rx = (int)(Math.random()*(width/2))*2 +1;
                ry = (int)(Math.random()*(height/2))*2 +1;
            }
            while(maps[ry][rx] !=TYPE_NONE);
            maps[ry][rx] = TYPE_ITEM;
        }
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getMap(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y) {
            return -1;
        }
        return maps[y][x];
    }

    public ImageView getImageView(int x, int y) {
        return mapImageViews[y][x];
    }

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

    public void fillMap(int type){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                maps[y][x] = type;
            }
        }
    }

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

    public void printMap(){
        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                if (getMap(x,y) == MapData.TYPE_WALL){
                    System.out.print("++");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.print("\n");
        }
    }
}
