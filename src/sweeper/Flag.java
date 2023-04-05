package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start (){
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;

    }
    Box get (Cord cord){
        return flagMap.getMatrix(cord);
    }


    void setOpenedToBox(Cord cord) {
        flagMap.setMatrix(cord, Box.OPENED);
        countOfClosedBoxes--;
    }

    private void setFlagedToBox(Cord cord) {
        flagMap.setMatrix(cord,Box.FLAGED);
    }

    void toggleFlagedBox(Cord cord) {
        switch (flagMap.getMatrix(cord)){
            case FLAGED : setClosedToBox(cord);break;
            case CLOSED: setFlagedToBox(cord); break;

        }
    }

    private void setClosedToBox(Cord cord) {
        flagMap.setMatrix(cord, Box.CLOSED);
    }
    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Cord cord) {
        flagMap.setMatrix(cord, Box.BOMBED);
    }

    void setOpenedBoxBombed(Cord cord) {
        if (flagMap.getMatrix(cord)== Box.CLOSED)
            flagMap.setMatrix(cord, Box.OPENED);
    }

    void setNobombToFlagSafeBox(Cord cord) {
        if( flagMap.getMatrix(cord)== Box.FLAGED)
            flagMap.setMatrix(cord, Box.NOBOMB );
    }


    int getCountOfFlagedBoxesAround(Cord cord) {
        int count = 0;
        for (Cord around : Ranges.getCordAround(cord))
            if(flagMap.getMatrix(around)== Box.FLAGED)
                count++;
        return count;
    }
}
