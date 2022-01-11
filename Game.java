import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

public class Game{
    JFrame gameFrame;
    GamePanel gamePanel;   
    MyKeyListener keyListener; 
    MyMouseListener mouseListener;
    MyMouseMotionListener mouseMotionListener;

    Set<Integer> keysPressed;
    boolean mouseDown;
    int mouseX;
    int mouseY;
    
    StateMachine stateMachine;




    Game() throws IOException, FontFormatException {
        gameFrame = new JFrame("Mini Golf");   
        gamePanel = new GamePanel();          
        keyListener = new MyKeyListener();
        mouseListener = new MyMouseListener();
        mouseMotionListener = new MyMouseMotionListener();

        keysPressed = new HashSet<Integer>();
        mouseDown = false;
        mouseX = 0;
        mouseY = 0;

        stateMachine = new StateMachine();        
    }
    // set up the game window
    public void setUp() {
        gameFrame.setSize(Const.WIDTH, Const.HEIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gamePanel.addKeyListener(keyListener);
        gamePanel.addMouseListener(mouseListener);
        gamePanel.addMouseMotionListener(mouseMotionListener);        
        gameFrame.add(gamePanel); 
        gameFrame.setVisible(true);
    }
    // main game loop
    public void runGameLoop() {
        while (true) {
            gameFrame.repaint();
            try  {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}

            stateMachine.update(keysPressed, mouseDown, mouseX, mouseY);
        }
    }    
    // act upon key and mouse events
    public class MyKeyListener implements KeyListener {   
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            keysPressed.add(key);
        }
        public void keyReleased(KeyEvent e) { 
            int key = e.getKeyCode();
            keysPressed.remove(key);
        }   
        public void keyTyped(KeyEvent e) {
            char keyChar = e.getKeyChar();
        }           
    }    

    public class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();    
        }
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1) {
                mouseDown = true;
            }
        }
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 1) {
                mouseDown = false;
            }
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    }    

    public class MyMouseMotionListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }         
    }    
    //draw everything
    public class GamePanel extends JPanel {
        GamePanel() {
            setFocusable(true);
            requestFocusInWindow();
        }
        
        @Override
        public void paintComponent(Graphics g) { 
            super.paintComponent(g); //required
            
            stateMachine.draw(g);
        }    
    }    
}