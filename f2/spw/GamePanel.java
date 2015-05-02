import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.JPanel;

import java.awt.Font;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private Image img;

	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		//big.setBackground(Color.BLACK);
		img = Toolkit.getDefaultToolkit().getImage("space.jpg");
       // big.drawImage(img,0, 0, 400, 600, null);
	}

	public void startUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.drawImage(img,0, 0, 400, 600, null);
		big.setColor(Color.RED);
		//big.setFont(big.getFont().deriveFont(16.0f));	
		Font f = new Font ("DialogInput", Font.BOLD, 16);
      	big.setFont (f);
      	big.drawString(String.format("Press \"Enter\" to start game"), 70, 300);
		repaint();
	}

	public void endUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.drawImage(img,0, 0, 400, 600, null);
		big.setColor(Color.ORANGE);
		//big.setFont(big.getFont().deriveFont(12.0f));
		Font f2 = new Font ("DialogInput", Font.PLAIN, 12);
      	big.setFont (f2);
		big.drawString(String.format("Score %08d", reporter.getScore()), 280, 20);
		big.drawString(String.format("HP %02d", reporter.getHpScore()), 5, 20);
		big.setColor(Color.RED);
		//big.setFont(big.getFont().deriveFont(16.0f));
		Font f3 = new Font ("DialogInput", Font.BOLD, 16);
      	big.setFont (f3);
		big.drawString(String.format("GAME OVER"), 150, 280);		
		big.drawString(String.format("Press \"R\" to restart game"), 70, 300);
		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.drawImage(img,0, 0, 400, 600, null);
		big.setColor(Color.ORANGE);
		//big.setFont(big.getFont().deriveFont(12.0f));
		Font f4 = new Font ("DialogInput", Font.PLAIN, 12);
      	big.setFont (f4);		
		big.drawString(String.format("Score %08d", reporter.getScore()), 280, 20);
		big.drawString(String.format("HP %02d", reporter.getHpScore()), 5, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
