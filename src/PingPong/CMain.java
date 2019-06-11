
package PingPong;

import PingPong.CGamePanel;
import javax.swing.JFrame;

/**
 *Klasa główna
 * @author Łukasz
 */
public class CMain {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        CGamePanel gamePanel = new CGamePanel();
        window.setBounds(10, 10, 700, 600);
        window.setTitle("PingPong");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(gamePanel);
        window.setVisible(true);
        
    }
    
}
