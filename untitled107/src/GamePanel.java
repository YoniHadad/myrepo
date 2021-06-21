import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    final int x[]= new int[Def.GAME_UNIT];
    final int y[]= new int[Def.GAME_UNIT];
    int applesEaten;
    int appleX;
    int appleY;
    String direction = "Right";
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){
        random= new Random();
        this.setPreferredSize(new Dimension(Def.GAME_WIDTH,Def.GAME_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        startGame();

    }
    public void startGame(){
        newApple();
        running=true;
        timer = new Timer(Def.DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g){
        if (running){
            for (int i=0;i<Def.GAME_HEIGHT/Def.UNIT_SIZE;i++){
                g.drawLine(i*Def.UNIT_SIZE,0,i*Def.UNIT_SIZE,Def.GAME_HEIGHT);
                g.drawLine(0,i*Def.UNIT_SIZE,Def.GAME_WIDTH,i*Def.UNIT_SIZE);
            }
            g.setColor(Color.GREEN);
            g.fillOval(appleX,appleY,Def.UNIT_SIZE,Def.UNIT_SIZE);

            for (int i=0;i<Def.BODY_PARTS;i++){
                if (i==0){
                    g.setColor(Color.orange);
                    g.fillRect(x[i],y[i],Def.UNIT_SIZE,Def.UNIT_SIZE);
                }else{
                    g.setColor(Color.yellow);
                    g.fillRect(x[i],y[i],Def.UNIT_SIZE,Def.UNIT_SIZE);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font("u",Font.HANGING_BASELINE,30));
            FontMetrics m1=getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten,(Def.GAME_WIDTH-m1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());

        }
        else {
            gameOver(g);
        }



    }
    public void newApple(){
        appleX=random.nextInt((int)(Def.GAME_WIDTH/Def.UNIT_SIZE))*Def.UNIT_SIZE;
        appleY=random.nextInt((int)(Def.GAME_HEIGHT/Def.UNIT_SIZE))*Def.UNIT_SIZE;

    }
    public void move(){
        for (int i = Def.BODY_PARTS;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction){
            case "Up":
                y[0]=y[0]-Def.UNIT_SIZE;
                break;
            case "Down":
                y[0]=y[0]+Def.UNIT_SIZE;
                break;
            case "Left":
                x[0]=x[0]-Def.UNIT_SIZE;
                break;
            case "Right":
                x[0]=x[0]+Def.UNIT_SIZE;
                break;
        }

    }
    public void apple(){
        if ((x[0]==appleX)&&(y[0]==appleY)){
            Def.BODY_PARTS++;
            applesEaten++;
            newApple();
        }

    }
    public void snakeCrash(){
        //check if head touching body
        for (int i = Def.BODY_PARTS;i>0;i--){
            if ((x[0]==x[i])&&(y[0]==y[i])){
                running=false;
            }
        }
        if (x[0]<0){
            running=false;
        }
        if (x[0]>Def.GAME_WIDTH){
            running=false;
        }
        if (y[0]<0){
            running=false;
        }
        if (y[0]>Def.GAME_HEIGHT){
            running=false;
        }
        if (!running){
            timer.stop();

        }
    }
    public void gameOver (Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("u",Font.HANGING_BASELINE,30));
        FontMetrics m1=getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten,(Def.GAME_WIDTH-m1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());

        g.setColor(Color.RED);
        g.setFont(new Font("u",Font.HANGING_BASELINE,50));
        FontMetrics m2=getFontMetrics(g.getFont());
        g.drawString("Game over!",(Def.GAME_WIDTH-m2.stringWidth("Game over!"))/2,Def.GAME_HEIGHT/2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
            apple();
            snakeCrash();
        }
        repaint();

    }
    public class myKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction!="Right"){
                        direction="Left";
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction!="Left"){
                        direction="Right";
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction!="Down"){
                        direction="Up";
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction!="Up"){
                        direction="Down";
                    }
                    break;
            }

        }
    }
}
