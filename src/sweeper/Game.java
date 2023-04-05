package sweeper;

public class Game {
    public GameState getState() {
        return state;
    }

    private GameState state;
    private  Bomb bomb;
    private Flag flag;


    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Cord(cols, rows));
        bomb =new Bomb(bombs);
        flag = new Flag();

    }
    public void start () {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;

    }
    public Box getBox (Cord cord){
        if (flag.get(cord)==Box.OPENED)
            return bomb.get(cord);
        else
            return flag.get(cord);
    }


    private void checkWinner () {
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }

    private void openBox(Cord cord) {
        switch (flag.get(cord)){
            case OPENED: setOpenedToClosedBoxesAroundNumber(cord); return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(cord)){
                    case ZERO : openBoxAround(cord);return;
                    case BOMB: openBombs(cord); return;
                    default: flag.setOpenedToBox(cord); return;

                }
        }
    }
    private void setOpenedToClosedBoxesAroundNumber(Cord cord){
        if (bomb.get(cord)!= Box.BOMB)
            if(flag.getCountOfFlagedBoxesAround(cord)==bomb.get(cord).getNumber())
                for(Cord around : Ranges.getCordAround(cord))
                    if( flag.get(around)==Box.CLOSED)
                        openBox(around);

    }

    private void openBombs(Cord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Cord cord : Ranges.getAllCords())
            if (bomb.get(cord) == Box.BOMB)
                flag.setOpenedBoxBombed(cord);
        else flag.setNobombToFlagSafeBox(cord);


    }

    private void openBoxAround(Cord cord) {
        flag.setOpenedToBox(cord);
        for (Cord around : Ranges.getCordAround(cord))
            openBox(around);
    }

    public void pressRightButton(Cord cord) {
        if(gameOver()) return;
        flag.toggleFlagedBox (cord);
    }
    public void pressLeftButton(Cord cord) {
        if(gameOver()) return;
        openBox(cord);
        checkWinner();
    }
    private boolean gameOver () {
        if( state == GameState.PLAYED) return false;
        start();
        return true;
    }
}
