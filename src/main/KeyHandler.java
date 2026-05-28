package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    
    GamePanel m_gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp) {
        this.m_gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (m_gp.m_gameState == m_gp.startState) {
            if (code == KeyEvent.VK_UP) {
                m_gp.m_menu.m_commandNum--;
                if (m_gp.m_menu.m_commandNum < 0) {
                    m_gp.m_menu.m_commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                m_gp.m_menu.m_commandNum++;
                if (m_gp.m_menu.m_commandNum > 1) {
                    m_gp.m_menu.m_commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (m_gp.m_menu.m_commandNum == 0) {
                    m_gp.m_gameState = m_gp.playState;
                }
                if (m_gp.m_menu.m_commandNum == 1) {
                    System.exit(0);
                }
            }
        }
        else if (m_gp.m_gameState == m_gp.playState) {
            if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) upPressed = true;
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = true;
            if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) leftPressed = true;
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = true;
            if (code == KeyEvent.VK_SPACE) {
                System.out.println("Lance le projectile");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (m_gp.m_gameState == m_gp.playState) {
            if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) upPressed = false;
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = false;
            if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) leftPressed = false;
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = false;
        }
    }
}