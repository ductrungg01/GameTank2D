package pkg2dgamesframework;

import game.tank2d.KeyPlayer2;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public abstract class GameScreen extends JFrame implements KeyListener {

    public static int KEY_PRESSED = 0;
    public static int KEY_RELEASED = 1;
    
    public int CUSTOM_WIDTH  = 500;
    public int CUSTOM_HEIGHT = 500;
    
    private GameThread G_Thread;
    private KeyPlayer2 keyPlayer2;

    public static int MASTER_WIDTH = 500, MASTER_HEIGHT = 500;
    
    public GameScreen(){
        InitThread();
        InitScreen();
    }
    
    public void RegisterImage(int id, BufferedImage image){
        
    }
    
    public BufferedImage getImageWithID(int id){
        return null;
    }
    
    public GameScreen(int w, int h){
        this.CUSTOM_WIDTH = w;
        this.CUSTOM_HEIGHT = h;
        MASTER_WIDTH = CUSTOM_WIDTH;
        MASTER_HEIGHT = CUSTOM_HEIGHT;
        InitThread();
        InitScreen();
    }
    
    private void InitScreen(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        setVisible(true);
        
    }
    
    public void BeginGame(){
        G_Thread.StartThread();
    }
    
    private void InitThread(){
        G_Thread = new GameThread(this);
        //keyPlayer2 = new KeyPlayer2(this);
        add(G_Thread);
    }
    protected List<String> listKey = new ArrayList<String>();
    public void AddKey(int e)
    {
        boolean kt=false;
        for (int i=0;i<listKey.size();i++)
        {
            int a = Integer.parseInt(listKey.get(i));
            if (e==KeyEvent.VK_UP||e==KeyEvent.VK_DOWN||e==KeyEvent.VK_LEFT||e==KeyEvent.VK_RIGHT)
            {
                if (a==KeyEvent.VK_UP||a==KeyEvent.VK_DOWN||a==KeyEvent.VK_LEFT||a==KeyEvent.VK_RIGHT)
                    return;
            }
            if (e==KeyEvent.VK_W||e==KeyEvent.VK_S||e==KeyEvent.VK_A||e==KeyEvent.VK_D)
            {
                if (a==KeyEvent.VK_W||a==KeyEvent.VK_S|a==KeyEvent.VK_A|a==KeyEvent.VK_D)
                    return;
            }
        }
        listKey.add(String.valueOf(e));
        System.out.println("Keypressed: "+e);
    }
    public void RemoveKey(int e)
    {
        if (listKey.contains(String.valueOf(e))) {
            listKey.remove(String.valueOf(e));
            System.out.println("KeyRelease: "+e);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        AddKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        RemoveKey(e.getKeyCode());
    }
    
    public abstract void GAME_UPDATE(long deltaTime);
    public abstract void GAME_PAINT(Graphics2D g2);
    public abstract void KEY_ACTION();
}
