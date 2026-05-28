package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestionnaire d'�v�nements (touche clavier)
 *
 */
public class KeyHandler implements KeyListener{
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
	public GamePanel m_gp;
	
	KeyHandler(GamePanel gp){
		m_gp=gp;
	}

	@Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (m_gp.m_gameState==m_gp.gameOverState) {
        	if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN){
            	if (m_gp.m_gameover.commandNum==1) {
            		m_gp.m_gameover.commandNum=0;
            	}else {
            		m_gp.m_gameover.commandNum=1;
            	}
            }
        	if (code==KeyEvent.VK_ENTER) {
        		if (m_gp.m_gameover.commandNum==0) {
        			m_gp.m_gameState=m_gp.playState;
        		}else {
        			System.exit(0);
        		}
        	}
        }
        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) upPressed = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = true;
        if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) leftPressed = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = true;
        if (code == KeyEvent.VK_SPACE) spacePressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) upPressed = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = false;
        if (code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) leftPressed = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = false;
        if (code == KeyEvent.VK_SPACE) spacePressed = false;
    }

}
