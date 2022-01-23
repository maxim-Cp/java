import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Main extends JFrame  {
    public final int WIDTH=1000;
    public final int HIGHT=600;
    /**fixing the ball need more calculation*/
    //using static too call it in drawing
    static Ball panit=new Ball(500,320);//position of the ball when it starts


    Image image;
    Graphics graphics;



    public Main(){/**Constructor*/
        this.setTitle("PING PONG GAME JAVA");
        this.setSize(WIDTH,HIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new keyboard());

    }//end of constructor
    public static void main(String[] args){
        Main window=new Main();
        Thread ball=new Thread(panit);
        Thread player_1=new Thread(panit.player_1);
        Thread player_2=new Thread(panit.player_2);
        player_1.start();
        player_2.start();
        ball.start();

    }//end of Main public static
    @Override
    public void paint(Graphics g){
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        drew(graphics);
        g.drawImage(image,0,0,this);

    }//end of paint

    private void drew(Graphics g) {
              /**player 1 score*/
        panit.player_1.drew(g);//drew player 1
        g.setColor(Color.green);
        g.drawString("Score player 1 ",40,80);
        g.setColor(Color.WHITE);
        g.drawString(""+panit.Score_Player1,60,100);

         /**player 2 score */
        panit.player_2.drew(g);//drew player 2
        g.setColor(Color.YELLOW);
        g.drawString("Score player 2 ",890,80);
        g.setColor(Color.WHITE);
        g.drawString(""+panit.Score_Player2,910,100);
        g.setColor(Color.WHITE);
        g.drawLine(WIDTH/2,HIGHT,WIDTH/2,-HIGHT/2);//LIne center


        /**ball*/
        panit.drew(g);

        /**repaint images*/
        repaint();
    }//end of drew
          /**Keypad*/
    private class keyboard extends KeyAdapter {
        /**when clicked buttons*/
        @Override
        public void keyPressed(KeyEvent e) {
            panit.player_1.keyPressed(e);
            panit.player_2.keyPressed(e);

        }
        /**when Released buttons*/
        @Override
        public void keyReleased(KeyEvent e) {
            panit.player_1.keyReleased(e);
            panit.player_2.keyReleased(e);

        }
    }//end of keyboard
}//end of class Main
