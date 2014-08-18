import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyMonitor implements KeyListener {
	boolean up, down, left, right;
	public int directionX;
	public int directionY;
	
	@Override
	public void keyPressed(KeyEvent e) {
		directionX = 0;
		directionY = 0;
		up = false;
		down = false;
		left = false;
		right = false;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = true;
			directionY = -1;

		case KeyEvent.VK_DOWN:
			down = true;
			directionY = 1;

		case KeyEvent.VK_LEFT:
			left = true;
			directionX = -1;

		case KeyEvent.VK_RIGHT:
			right = true;
			directionX = 1;

		case KeyEvent.VK_ESCAPE:
			// TODO: expand keys
		}
		if (up && down) {
			directionY = 0;
		}
		if (left && right) {
			directionX = 0;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}