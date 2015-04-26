import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 600;
	public static final int Y_TO_DIE = 0;
	
	private int step = 12;
	private boolean alive = true;
	
	public Bullet(int x, int y) {
		super(x, y, 3, 10);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

	public void proceed(){
		y -= step;
		if(y < Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	void bulletCrash(){
		alive = false;
	}
}