package sweeper;

public class Cord {
    public int x;
    public int y;


    public Cord (int x , int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Cord){
            Cord to = (Cord) obj;
            return to.x==x && to.y==y ;
        }
        return super.equals(obj);

    }
}
