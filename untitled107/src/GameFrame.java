import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame(){
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Snake game");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        //this.setLocationRelativeTo(null);

    }






}
