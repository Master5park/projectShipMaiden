import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Asset {
	String imagePath = ".../tschib.game.projectShipMaiden/Assets/";
	String imagePathAdd;
	String imageName = "nope";
	Boolean isVisible = true;
	float opacity = 1.0f;
	boolean isStaticAnim = false;
	BufferedImage currentFrame;
	BufferedImage nextFrame;
	boolean animFreeze = false;
	Point pos = new Point(0, 0);
	double currentAngle;
	boolean drawNextFrame = false;
	boolean dispose = false;
	boolean fadeIn = false;
	ArrayList<BufferedImage> animList;

	public void init() {// loads animations
		animList = new ArrayList<BufferedImage>();
		for (int i = 1; i < 17; i++) {
			animList.add(loadAnim((imageName + i)));
		}
		if (fadeIn == true) {
			opacity = 0;
		}
	}

	public void update() {
	}

	public void move(double newAngle) {
		Point nextPos = null;
		// TODO: newAngle
		rotate(nextPos);
	}

	public void rotate(Point nextPos) {
		double diffX;
		double diffY;
		double direction = 5;
		diffX = (int) (pos.getX() - nextPos.getX());
		diffY = (int) (pos.getY() - nextPos.getY());
		direction = Math.round(Math.atan2(-diffX, diffY) * 4.0 / Math.PI) + 5;// 1-8
		switch ((int) direction) {
		case 1:
			currentFrame = animList.get(1);
			nextFrame = animList.get(2);
			break;
		case 2:
			currentFrame = animList.get(3);
			nextFrame = animList.get(4);
			break;
		case 3:
			currentFrame = animList.get(5);
			nextFrame = animList.get(6);
			break;
		case 4:
			currentFrame = animList.get(7);
			nextFrame = animList.get(8);
			break;
		case 5:
			currentFrame = animList.get(9);
			nextFrame = animList.get(10);
			break;
		case 6:
			currentFrame = animList.get(11);
			nextFrame = animList.get(12);
			break;
		case 7:
			currentFrame = animList.get(13);
			nextFrame = animList.get(14);
			break;
		case 8:
			currentFrame = animList.get(15);
			nextFrame = animList.get(16);
			break;
		}
	}

	public BufferedImage loadAnim(String newAnimName) {
		BufferedImage tempAnim = null;
		try {
			tempAnim = ImageIO.read(new File(imagePath + imagePathAdd
					+ newAnimName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempAnim;
	}

	public void fadeEffect() {// true to fade in
		if (fadeIn == true && opacity != 1) {// TODO: finish&implement
			opacity += 0.01;
		}
	}

	public BufferedImage getCurrentFrame() {
		if (drawNextFrame == false) {// switches between 2 animations
			drawNextFrame = true;
			return currentFrame;
		} else {
			drawNextFrame = false;
			return nextFrame;
		}
	}

	public Point getPos() {
		return pos;
	}
}