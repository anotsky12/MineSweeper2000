package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;
    Bomb (int totalBombs){
        this.totalBombs = totalBombs;
        fixBombCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }

    }
    Box get (Cord cord) {
        return bombMap.getMatrix(cord);
    }
    private void fixBombCount(){
        int maxBomb = Ranges.getSize().x * Ranges.getSize().y/2;
        if (totalBombs > maxBomb)
            totalBombs = maxBomb;
    }

    private void placeBomb(){
        while (true){
            Cord cord = Ranges.getRandomCord();
            if (Box.BOMB == bombMap.getMatrix(cord) )
                continue;
            bombMap.setMatrix (cord,Box.BOMB);
            incNumbersAround(cord);
            break;
        }



    }
    private void incNumbersAround (Cord cord) {
        for (Cord around: Ranges.getCordAround(cord))
            if ( Box.BOMB != bombMap.getMatrix(around))
                bombMap.setMatrix(around,bombMap.getMatrix(around).getNextNumberBox());

    }

   int getTotalBombs() {
        return totalBombs;
    }
}
