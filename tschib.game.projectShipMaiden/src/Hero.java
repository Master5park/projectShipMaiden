import java.awt.Point;
import java.awt.image.BufferedImage;

public class Hero extends Asset {

	Hero(Point pos, boolean fadeIn) {
		this.imagePathAdd = "hero/";
		this.imageName = "hero";
		this.init();
		this.pos = pos;
		this.fadeIn = fadeIn;
	}

	public void update() {// (1-8) N==1 S==5
		move(0);// TODO
		// TODO:Asset Logic
	}

	public void animFire() {
	}

	public void animDie() {
		dispose = true;
	}

	public void animMove() {
	}
}