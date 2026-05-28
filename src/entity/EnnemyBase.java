package entity;



import main.GamePanel;


import java.util.Random;

public class EnnemyBase extends Ennemy {

	int m_target_x;
	int m_target_y;
	int m_cooldownTir; 
	int m_cadenceTir;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public EnnemyBase(GamePanel a_gp, int y_depart, int y_target) {
		super(a_gp);
		Random r = new Random();
		this.setDefaultValues();
		m_y=y_depart;
		this.getEnnemyImage();
		m_target_x = r.nextInt(m_gp.SCREEN_WIDTH+1);
		m_target_y = y_target;
		m_cooldownTir = 0;   
		m_cadenceTir = 25;
	}
	
	private void tirer() {
		ProjectileEnnemy missile = new ProjectileEnnemy1(m_gp, this,25,8);
		m_projectiles.add(missile);}
	
	public void update() {
		double dx = m_target_x-m_init_x;
		double dy = m_target_y-m_init_y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		m_y += m_speed * (dy/distance);			
		m_x += m_speed * (dx/distance);
		if (m_cooldownTir > 0) {
				m_cooldownTir--; 
			}
			
		if (m_cooldownTir == 0) {
				tirer();
				m_cooldownTir = m_cadenceTir; 
			}
		for (int i = m_projectiles.size() - 1; i >= 0; i--) {
			ProjectileEnnemy p = m_projectiles.get(i);
			p.update();
			

			if (p.getY() < -m_gp.TILE_SIZE) {
				m_projectiles.remove(i);
			}
		}
		
	}
}
