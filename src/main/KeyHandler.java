package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Ennemy;

import tile.TileManager;

public class KeyHandler implements KeyListener {
    
    public GamePanel m_gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;


    
	
	public KeyHandler(GamePanel gp){
		this.m_gp=gp;
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
        			m_gp.m_position=-(m_gp.MAX_WORLD_ROW * m_gp.TILE_SIZE) + m_gp.SCREEN_HEIGHT;
        			m_gp.m_tileM = new TileManager(m_gp);
        			m_gp.vague1=false;
        			m_gp.vague2=false;
        			m_gp.vague3=false;
        			m_gp.vague4=false;
        			m_gp.vague5=false;
        			m_gp.vague6=false;
        			m_gp.vague7=false;

                    entity.Ennemy.listEnnemy.clear();

                    
                    m_gp.m_player.setDefaultValues();
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
