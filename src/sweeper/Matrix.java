package sweeper;

class Matrix {
    private Box [][] matrix;
    Matrix (Box defaultBox){
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Cord cord : Ranges.getAllCords() ) matrix [cord.x] [cord.y] = defaultBox;
    }

     Box getMatrix(Cord cord) {
        if(Ranges.inRange ( cord))
            return matrix[cord.x][cord.y];
        return null;
    }

     void setMatrix(Cord cord,Box box) {
         if(Ranges.inRange ( cord))
             matrix[cord.x][cord.y]= box;

    }
}
