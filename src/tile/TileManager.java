package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * 
 * Gestionnaire des tiles du jeu
 *
 */
public class TileManager {
	GamePanel m_gp;			//panel du jeu principal
	Tile[] m_tile;			//tableau de toutes les tiles possibles dans le jeu
	int m_maxTiles = 10;	//nombre maximum de tiles chargeable dans le jeu
	int m_mapTileNum[][];	//r�partition des tiles dans la carte du jeu
	
	/**
	 * Constructeur
	 * @param gp
	 */
	public TileManager(GamePanel gp) {
		this.m_gp =  gp;
		m_tile = new Tile[m_maxTiles];
		m_mapTileNum = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
		this.getTileImage();
		this.newRandomMap();
	}
	
	/**
	 * Chargement de toutes les tuiles du jeu
	 */
	public void getTileImage() {
		try {
			m_tile[0] = new Tile();
			m_tile[0].m_image = ImageIO.read(getClass().getResource("/tiles/fond_noir.png"));
			
			m_tile[1] = new Tile();
			m_tile[1].m_image = ImageIO.read(getClass().getResource("/tiles/etoiletest1.png"));
			
			m_tile[2] = new Tile();
			m_tile[2].m_image = ImageIO.read(getClass().getResource("/tiles/etoiletest2.png"));
			
			m_tile[3] = new Tile();
			m_tile[3].m_image = ImageIO.read(getClass().getResource("/tiles/etoiletest3.png"));
			
			m_tile[4] = new Tile();
			m_tile[4].m_image = ImageIO.read(getClass().getResource("/tiles/etoiletest4.png"));
			
			m_tile[5] = new Tile();
			m_tile[5].m_image = ImageIO.read(getClass().getResource("/tiles/etoiletest5.png"));
			
			m_tile[6] = new Tile();
			m_tile[6].m_image = ImageIO.read(getClass().getResource("/tiles/etoiletest6.png"));
			
			m_tile[7] = new Tile();
			m_tile[7].m_image = ImageIO.read(getClass().getResource("/tiles/fond_blanc.png"));
			
			m_tile[8] = new Tile();
			m_tile[8].m_image = ImageIO.read(getClass().getResource("/tiles/fond_noir.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getTileNum(int col, int row) {
    if (col >= 0 && col < m_gp.MAX_WORLD_COL && row >= 0 && row < m_gp.MAX_WORLD_ROW) {
        return m_mapTileNum[col][row];
    }
    return 0;
}
	
	/**
	 * Lecture du fichier txt contenant la map et chargement des tuiles correspondantes.
	 */
	public void newRandomMap() {
		Random rand = new Random();
		double fondProba = 0.97;
		for (int row = 0; row < m_gp.MAX_WORLD_ROW; row++) {
	        for (int col = 0; col < m_gp.MAX_WORLD_COL; col++) {
	        	if (row == 50 || row == 51 || row == 52) {
	        		if (row % 2 == 0) {
		        		if (col % 2 == 0) {
		        			m_mapTileNum[col][row] = 7;
		        		} else {
		        			m_mapTileNum[col][row] = 8;
		        		}
	        		} else {
	        			if (col % 2 == 0) {
		        			m_mapTileNum[col][row] = 8;
		        		} else {
		        			m_mapTileNum[col][row] = 7;
		        		}
	        		}
	        	} else {
		            double roll = rand.nextDouble();
		            if (roll < fondProba) {
		                m_mapTileNum[col][row] = 0;
		            } else {
		                m_mapTileNum[col][row] = rand.nextInt(6) + 1;
		            }
	        	}
	        }
	    }
	}
	
	/**
	 * Affichage de la carte avec les diff�rentes tuiles
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y =m_gp.m_position;
		
		while (col < m_gp.MAX_WORLD_COL && row < m_gp.MAX_WORLD_ROW) {
			int tileNum = m_mapTileNum[col][row];
			
			g2.drawImage(m_tile[tileNum].m_image, x, y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
			col ++;
			x += m_gp.TILE_SIZE;
			if (col == m_gp.MAX_WORLD_COL) {
				col = 0;
				row ++;
				x = 0;
				y += m_gp.TILE_SIZE;
			}
		}
		
	}
}
