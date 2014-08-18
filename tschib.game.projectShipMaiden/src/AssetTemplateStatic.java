import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class AssetTemplateStatic {
	String defaultImageName = "default.png";// default if not overwritten
	String ImagePath = ".../tschib.game.projectShipMaiden/Assets/";// default if
																	// not
																	// overwritten
	String name = "defaultname";//TODO: alles neu anpassens
	int id = 000000;// 6 digits
	Boolean isVisible = true;
	double opacity = 1.0;
	final boolean isStaticAnim = true;
	int currentFrame = 0;
	boolean animFreeze = false;
	Point pos = new Point(0, 0);

	ArrayList<BufferedImage> animListStat;
	ArrayList<String> animListNamesStat;

	public void init() {
		animListStat = new ArrayList<BufferedImage>();
		animListNamesStat = new ArrayList<String>();
		loadAnimList();
	}

	public AssetTemplateStatic() {
	}

	public void update() {
	}

	public void loadAnimList() {
		if (animListNamesStat.isEmpty()) {// in case no images provided
			try {
				animListStat.add(ImageIO.read(new File(ImagePath
						+ defaultImageName)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
