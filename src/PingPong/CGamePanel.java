package PingPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *Klasa odpowiadajaca za logike gry
 * @author Łukasz
 */
public class CGamePanel extends JPanel implements KeyListener, ActionListener{

    private boolean play = false;
    private String winner;
    
    private Timer time;
    private int speed = 5;
    
    //pozycje poczatkowe
    private int player1Y = 300;
    private int player2Y = 300;
    private int ballX = 300;
    private int ballY = 350;
    //kierunek poczatkowy kulki
    private int ballDirectionX = -1;
    private int ballDirectionY = -2;
    
    public CGamePanel(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(speed, this);
        time.start();     
    } 
    /**
     * rysowanie planszy paletek i kulki
     */
    public void paint(Graphics g){
        //tlo
        g.setColor(Color.blue);
        g.fillRect(1, 1, 692, 592);
        
        //granice planszy
        g.setColor(Color.red);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 562, 692, 3);
        
        //paletki
        g.setColor(Color.green);
        g.fillRect(20 , player1Y, 8, 100);
        g.fillRect(670 , player2Y, 8, 100);
        
        //kulka
        g.setColor(Color.red);
        g.fillOval(ballX , ballY, 20, 20);
        
        //komunikat zakonczenia gry
        if(ballX<-20 || ballX>700 ){
            if(ballX<-20){
                winner="Wygrał gracz 2!";
            }else{
                winner="Wygrał gracz 1!";
            }
            play=false;
            ballDirectionX=0;
            ballDirectionY=0;
            g.setColor(Color.yellow);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString(winner, 300, 300);
            
             g.setFont(new Font("serif",Font.BOLD,20));
             g.drawString("Restart (Enter)", 310, 330);     
        }
        g.dispose();
    }
    
    //wymagane w keyEvent, inaczej wystapi blad
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    
    /**
     * sterowanie
     */
    public void keyPressed(KeyEvent e) {
    //gracz 1
        if(e.getKeyCode()== KeyEvent.VK_W){
            if(player1Y<=5){
                player1Y=5;
            }else{
                play=true;
                player1Y-=20;
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_S){
           if(player1Y>=460){
                player1Y=460;
            }else{
                 play=true;
                player1Y+=20;
            } 
        }
    //gracz 2
        if(e.getKeyCode()== KeyEvent.VK_UP){
            if(player2Y<=5){
                player2Y=5;
            }else{
                 play=true;
                player2Y-=20;
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_DOWN){
           if(player2Y>=460){
                player2Y=460;
            }else{
                play=true;
                player2Y+=20;
            } 
        }
        //ponowne rozpoczecie gry
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            if(!play){
                player1Y = 300;
                player2Y = 300;
                ballX = 300;
                ballY = 350;
                ballDirectionX = -1;
                ballDirectionY = -2;
                repaint();
            }
        }  
    }
    
    /**
     * wykrywanie zderzen po rozpoczeciu gry
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if(play){
            if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(20, player1Y, 8, 100))
              || new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(670, player2Y, 8, 100))){
                ballDirectionX= -ballDirectionX;
            }
            ballX+=ballDirectionX;
            ballY+=ballDirectionY;
        }
        if(ballY<5){
            ballDirectionY= -ballDirectionY;
        }
        if(ballY>542){
            ballDirectionY= -ballDirectionY;
        }
        repaint();
    }
}
