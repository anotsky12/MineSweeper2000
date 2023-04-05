package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Cord size;
    private static ArrayList<Cord> allCords;
    private static Random random = new Random();

    public static void setSize(Cord _size) {

        size = _size;
        allCords = new ArrayList<Cord>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                allCords.add(new Cord(x, y));

            }

        }
    }

    public static Cord getSize() {
        return size;
    }

    public static ArrayList<Cord> getAllCords() {
        return allCords;
    }

    static boolean inRange(Cord cord) {
        return cord.x >= 0 && cord.x < size.x &&
                cord.y >= 0 && cord.y < size.y;
    }

    static Cord getRandomCord() {
        return new Cord(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Cord> getCordAround(Cord cord){
        Cord around;
        ArrayList<Cord> list = new ArrayList<Cord>();
        for ( int x = cord.x-1; x<=cord.x+1; x++){
            for(int y = cord.y-1; y<= cord.y+1; y++ ){
                if (inRange(around = new Cord(x,y)))
                    if (!around.equals(cord))
                        list.add(around);

            }
        }
        return list;

    }
}
