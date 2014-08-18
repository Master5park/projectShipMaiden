import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/** @see http://stackoverflow.com/questions/3256941 */
public class Loop extends JPanel implements ActionListener {
	private static final int WIDE = 800;
	private static final int HIGH = 600;
	private static final int FRAMES = 60;
	private final Timer timer = new Timer(20, this);// in millis
	private BufferedImage background;
	private long totalTime;
	private long averageTime;
	private int frameCount;
	private static KeyMonitor keyMonitor;
	private static PipelineManager pipelineManager;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				keyMonitor = new KeyMonitor();
				pipelineManager = new PipelineManager();
				new Loop().create();
			}
		});
	}

	private void create() {
		JFrame f = new JFrame("Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.pack();
		f.setLocationRelativeTo(null);// appWindow in center of screen
		f.setVisible(true);
		timer.start();
	}

	public Loop() {
		super(true);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(WIDE, HIGH));
		this.addMouseListener(new MouseHandler());
		this.addComponentListener(new ComponentHandler());
	}

	@Override
	protected void paintComponent(Graphics g) {
		long start = System.nanoTime();
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
		pipelineManager.addAsset(new Hero(new Point(40,40), false ),3);
		for (int k = 0; k < pipelineManager.bg1.size(); k++) {
			pipelineManager.bg1.get(k).update();
			drawMyJunk(g, pipelineManager.bg1.get(k).getCurrentFrame(),
					pipelineManager.bg1.get(k).getPos().x, pipelineManager.bg1
							.get(k).getPos().y,
					pipelineManager.bg1.get(k).opacity);
		}
		if (frameCount == FRAMES) {
			averageTime = totalTime / FRAMES;
			totalTime = 0;
			frameCount = 0;
		} else {
			totalTime += System.nanoTime() - start;
			frameCount++;
		}
		String s = String.format("%1$5.3f", averageTime / 1000000d);// framerate
		g.drawString(s, 5, 16);
	}

	public void drawMyJunk(Graphics gra, BufferedImage img, int posX, int posY,
			float opacity) {
		((Graphics2D) gra).setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, opacity));
		gra.drawImage(img, posX, posY, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}

	private class MouseHandler extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			int w = getWidth();
			int h = getHeight();
		}
	}

	private class ComponentHandler extends ComponentAdapter {

		private final GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		private final GraphicsConfiguration gc = ge.getDefaultScreenDevice()
				.getDefaultConfiguration();
		private final Random r = new Random();

		@Override
		public void componentResized(ComponentEvent e) {
			super.componentResized(e);

		}
	}
}