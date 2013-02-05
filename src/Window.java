// Both the window and the main. To do: better organize this.

import java.io.*;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.*;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Window {
	private Player player;
	private EnvironmentMap environment;
	private ArrayList<Model> models;
	
	public Window() throws FileNotFoundException, IOException {
		this.init();
		player = new Player();
		environment = new EnvironmentMap();
		models = new ArrayList<Model>();
		models.add(OBJLoader.loadModel(new File("assets/mayantemple.obj")));
		models.add(OBJLoader.loadModel(new File("assets/sphere2.obj")));
		models.get(1).setLight(true);
		models.get(1).setLocation(new Vector3f(0f, 8.2f, 0f));
		models.add(OBJLoader.loadModel(new File("assets/homer.obj")));
		models.get(2).setLocation(new Vector3f(-30f, 0f, 0f));
	}

	public void init() {
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
		
    	glEnable(GL_CULL_FACE);
    	glCullFace(GL_BACK);
		
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, Window.asFlippedFloatBuffer(new float[]{0.2f, 0.2f, 0.2f, 1f}));
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
	}
	
	public void draw() {
		// clear the screen and depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		player.look();
		environment.draw();
		for (Model m : models)
			m.draw();
	}
	
	public void update() {
		player.update();
		
		if (Mouse.isButtonDown(0))
			Mouse.setGrabbed(true);
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			Mouse.setGrabbed(false);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Window w = new Window();
		
		while (!Display.isCloseRequested()) {
			w.update();
			w.draw();
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
		System.exit(0);
	}
	
    /**
     * @param values the float values that are to be turned into a FloatBuffer
     * @return a FloatBuffer readable to OpenGL (not to you!) containing values
     */
    public static FloatBuffer asFlippedFloatBuffer(float... values) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}
