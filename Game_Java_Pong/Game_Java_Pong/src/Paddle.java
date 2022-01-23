import java.awt.*;
import java.awt.event.KeyEvent;


public class Paddle  implements Runnable{
    public int x,y,yDirection,player;
    public int paddle_width=10,paddle_height=100;
    Rectangle paddle;//call in main





    public Paddle(int x, int y,int player){/**constructor*/
        this.x=x;
        this.y=y;
        this.player=player;
       paddle= new Rectangle(x,y,paddle_width,paddle_height);//paddle size length

    }
    //check which player clicked the key player1 or player 2 switch case
    /**Key bored pressed W,S(player1)      arrow up,down (player 2)*/
   public void keyPressed(KeyEvent e){
        switch (player) {
            case 1:
              if (e.getKeyCode() == e.VK_UP) {/**upper arrow*/
                  y_paddle(-1);
              }
              if (e.getKeyCode() == e.VK_DOWN) {/**down arrow*/
                  y_paddle(+1);
              }
              break;
            case 2:

             if (e.getKeyCode() == e.VK_W) {/**keyboard W*/
                 y_paddle(-1);
             }
             if (e.getKeyCode() == e.VK_S) {/**keyboard S*/
                 y_paddle(+1);
             }
            break;
            /**do we need default?*/

        }
   }
   /**Key bored release the player from moving after he pressed one time*/
    public void keyReleased(KeyEvent e) {
        switch (player) {
            case 1:
                if (e.getKeyCode() == e.VK_UP) {/**upper arrow*/
                    y_paddle(0);//stop from moving player 1
                }
                if (e.getKeyCode() == e.VK_DOWN) {/**down arrow*/
                    y_paddle(0);//stop from moving player 1
                }
                break;
            case 2:

                if (e.getKeyCode() == e.VK_W) {/**keyboard W*/
                    y_paddle(0);//stop from moving player 2
                }
                if (e.getKeyCode() == e.VK_S) {/**keyboard S*/
                    y_paddle(0);//stop from moving player 2
                }
                break;
            /**do we need default?*/

        }
    }
   //drew players paddle
    /**needs new colors*/
   public void drew(Graphics g){
        switch (player){
            case 1:
                g.setColor(Color.GREEN);//color of the player 1
                g.fillRect(paddle.x,paddle.y,paddle_width,paddle_height);
                break;
            case 2:
                g.setColor(Color.YELLOW);//color player 2
                g.fillRect(paddle.x,paddle.y,paddle_width,paddle_height);
                break;
           /**do we need default? direction*/
        }
   }
   //get new direction
   public void y_paddle(int y_direction){
       yDirection=y_direction;

   }
   /**tests needed for the math paddle
    * Tests was successful need more tests? NO*/
   public void move_paddle(){
        paddle.y+=yDirection;
        if(paddle.y<=30){//Paddle movement player?//upper side
            paddle.y=30;
        }
        if(paddle.y>=495){//paddle movement player?//lower side
            paddle.y=495;
        }
   }

    @Override
    /**ruining method*/
    public void run() {
        while(true){
            move_paddle();
            try {
                Thread.sleep(4);//speed of the paddle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
