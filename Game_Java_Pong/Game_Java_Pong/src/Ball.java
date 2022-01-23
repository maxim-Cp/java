import java.awt.*;
import java.util.Random;

public class Ball implements Runnable{
    public int x,y,xDierction,yDirection;//start of ball,direction of the ball movement
    Rectangle ball;//call in main
    int Score_Player1;
    int Score_Player2;
    protected boolean running=true;


     Paddle player_1=new Paddle(20,260,1);//position of paddle player 1
     Paddle player_2=new Paddle(970,260,2);//position of paddle player 2

    public Ball(int x,int y){/**constructor*/
        this.x=x;//reference too x
        this.y=y;//reference too x
        Score_Player1=Score_Player2=0;
        /**tests needed for random*/
        Random random_ball=new Random();
        int x_random= random_ball.nextInt(4);//set too 1
        if(x_random==0){//random direction moves only +1 or
            x_random--;//if x-random is 0
            //thin x_random=-1 the ball going down
        }
        ball_xDirection(x_random);//gets ball_x direction
       int y_random=random_ball.nextInt(4);//random y direction
        if(y_random==0){//if y_random=0
            y_random--;//thin y_random=-1
        }
        ball_yDirection(y_random);//gets ball_y
        ball =new Rectangle(this.x,this.y,15,15);//Ball size length
    }
    //drawing the ball
    public void drew(Graphics g){
        int fontSize = 15;
        g.setColor(Color.RED);/**Change color?maybe*/
        g.fillOval(ball.x,ball.y,ball.width,ball.height);//shape it rounded like ball,fill it with color
        /**Game over who WOn?
         * in order too win you need too get 11 points*/
        if(Score_Player1==11) {//player 1 won
            g.setColor(Color.WHITE);
            g.setFont(new Font("",Font.BOLD,fontSize));
            g.drawString("PLAYER 1 WON", 200, 200);
            g.drawString("GAME OVER", 200, 400);
            running=false;

        }
        if(Score_Player2==11) {//player 2 won
            g.setColor(Color.WHITE);
            g.setFont(new Font("",Font.BOLD,fontSize));
            g.drawString("PLAYER 2 WON",580, 200);
            g.drawString("GAME OVER ", 580, 400);
            running=false;
        }
    }

    //direction of the ball movement
    public void move_Direction(){
        ball.x+=xDierction;//get set
        ball.y+=yDirection;//get set
        test_for_collision();
        /**test need calculations
         * more test needed?NO all good
         * Deepened on the size of the screen might need too change x and y*/
        if(ball.x<=0) {//hit X right side Y
            ball_xDirection(+1);
            Score_Player2+=1;
            System.out.println("Player 2 Hit-->"+Score_Player2);
        }

        if(ball.x>=980){//x side upper (top)
            ball_xDirection(-1);
            Score_Player1+=1;
            System.out.println("Player 1 HIT-->"+Score_Player1);
        }

        if(ball.y<=30){//hit Y side left X
            ball_yDirection(+1);
            //System.out.println("Fold");
        }

        if(ball.y>=572){//hit Y left bottom
            ball_yDirection(-1);
            //System.out.println("Fold");
            //System.out.println("iam here");
        }

    }


    /**directions of the ball get set*/
    public void ball_xDirection(int x_dierction_ball){
        this.xDierction=x_dierction_ball;//place of X Direction
    }
    public void ball_yDirection(int y_dierction_ball){
        this.yDirection=y_dierction_ball;//place of y Direction
    }
    /**test_for_collision looking for hits from the ball into the paddle*/
    public void test_for_collision(){
        Random random_collision=new Random();
        int x_collision= random_collision.nextInt(2);
        if(ball.intersects(player_1.paddle))
        {
            ball_xDirection(+x_collision);//hit ball player 1
        }
        if(ball.intersects(player_2.paddle))
        {
            ball_xDirection(-x_collision);//hit ball player 2
        }
    }
    //running method
    @Override
    public void run() {

        while(running){//true false stop
            move_Direction();
            try {

                Thread.sleep(3);//speed of the ball
                /**4 is it too slow?*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
