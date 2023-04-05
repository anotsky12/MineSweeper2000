import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Cord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new JavaSweeper();

    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        Ranges.setSize(new Cord(COLS, ROWS));
        setImages();
        initLable();
        initPanel();
        initFrame();


    }
    private void initLable(){
        label = new JLabel("WellCome");
        add (label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Cord cord : Ranges.getAllCords())
                    for (Box box : Box.values()) {

                        g.drawImage((Image) game.getBox(cord).image,
                                cord.x * IMAGE_SIZE,
                                cord.y * IMAGE_SIZE,
                                this);
                    }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Cord cord = new Cord(x,y);
                 if (e.getButton()==MouseEvent.BUTTON1)
                    game.pressLeftButton(cord);
                if (e.getButton()==MouseEvent.BUTTON3)
                    game.pressRightButton(cord);
                if (e.getButton()==MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }



    private void initFrame() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");

        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    public String getMessage() {
       switch (game.getState()){
           case PLAYED : return "Think twice!";
           case BOMBED : return "YOU unlucky";
           case WINNER: return "You win!";
           default: return "welcome";
        }
    }
}
