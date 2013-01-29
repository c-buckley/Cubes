// Both the window and the main. To do: better organize this.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.*;

import static org.lwjgl.opengl.GL11.*;

public class Window {

	public static void init() {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Esc to escape!");
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
		
		glShadeModel(GL_SMOOTH);
		glClearColor(1f, 1f, 1f, 0f);
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
	}
	
	public static void draw(Player player, EnvironmentMap environment) {
		// clear the screen and depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		player.look();
		environment.draw();
	}
	
	public static void update(Player player) {
		player.update();
		if (Mouse.isButtonDown(0))
			Mouse.setGrabbed(true);
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			Mouse.setGrabbed(false);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		init();
		Player player = new Player();
		EnvironmentMap environment = new EnvironmentMap();
		Model m = OBJLoader.loadModel(new File("assets/person.obj"));
		
		while (!Display.isCloseRequested()) {
			update(player);
			draw(player, environment);
			m.draw();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
}
