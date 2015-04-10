import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<ItemsHP> itemsHP = new ArrayList<ItemsHP>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	private int hp = 4;

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
				processItems();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	private void generateItemsHP(){
		ItemsHP i = new ItemsHP((int)(Math.random()*390), 30);
		gp.sprites.add(i);
		itemsHP.add(i);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 10;
			}
		}

		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				hp--;
				e.enermyCrash();
				if(hp < 1){
					die();
				}
				gp.updateGameUI(this);
				return;
			}
		}

	}
	
	private void processItems(){
		if(Math.random() < difficulty/20){
			generateItemsHP();
		}


		Iterator<ItemsHP> i_iter = itemsHP.iterator();
		while(i_iter.hasNext()){
			ItemsHP i = i_iter.next();
			i.proceed();
			
			if(!i.isAlive()){
				i_iter.remove();
				gp.sprites.remove(i);
			}
		}

		gp.updateGameUI(this);

		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(ItemsHP i : itemsHP){
			er = i.getRectangle();
			if(er.intersects(vr)){
				hp++;
				i.itemsCrash();
				gp.updateGameUI(this);
				return;
			}
		}
	}

	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1,0);
			break;
		case KeyEvent.VK_A:
			v.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1,0);
			break;
		case KeyEvent.VK_D:
			v.move(1,0);
			break;
		case KeyEvent.VK_UP:
			v.move(-1,1);
			break;
		case KeyEvent.VK_W:
			v.move(-1,1);
			break;
		case KeyEvent.VK_DOWN:
			v.move(1,1);
			break;
		case KeyEvent.VK_S:
			v.move(1,1);
			break;
		case KeyEvent.VK_F:
			difficulty += 0.1;
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	public int getHpScore(){
		return hp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
