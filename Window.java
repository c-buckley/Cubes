import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.*;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Window {

	public static void init() {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Safety scissors.");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		// initialize the camera
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(45.0f, (Display.getDisplayMode().getWidth() / Display.getDisplayMode().getHeight()), 0.1f, 3000.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		// initialize the shit
		glShadeModel(GL_SMOOTH);
		glClearColor(1f, 1f, 1f, 0f);
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		
	}
	
	public static void draw(Player p, Block bl, Block bl2, Block bl3) {
		// clear the screen and depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		GLU.gluLookAt(p.location().x, p.location().y, p.location().z,
					  11f, 11f, -11f,
					  0f, 1f, 0f);
		
//		bl.render();
//		bl2.render();
		bl3.render();
	}
	
	public static void input(Player player) {
		Vector3f delta = new Vector3f();
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			delta.z = 0.5f;
		else if (Keyboard.isKeyDown(Keyboard.KEY_S))
			delta.z = -0.5f;
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			delta.x = -0.5f;
		else if (Keyboard.isKeyDown(Keyboard.KEY_D))
			delta.x = 0.5f;
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			delta.y = 0.5f;
		else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			delta.y = -0.5f;
		
		player.adjustLocation(delta);
	}
	
	public static void main(String[] args) {
		init();
		Player player = new Player(new Vector3f(-60, 30, -60));
		Block bl = new Block(new Vector3f(100f, 100f, 0f), new Vector3f(300f, 300f, 200f));
		Block bl2 = new Block(new Vector3f(20f, 20f, 0f), new Vector3f(80f, 80f, 40f));
		Block bl3 = new Block(new Vector3f(4f, 4f, -4f), new Vector3f(18f, 18f, -18f));
		
		while (!Display.isCloseRequested()) {
			input(player);
			draw(player, bl, bl2, bl3);
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}
