import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector2f;


public class Player {
	
	private Vector3f location;
	private Vector2f angles;
	private Vector3f sights;
	
	public Player() {
		this(new Vector3f(0.0f, 1.0f, 0.0f));
	}
	
	public Player(Vector3f location) {
		this(location, new Vector2f(0.0f, (float)(Math.PI/2)));
	}
	
	public Player(Vector3f location, Vector2f angles) {
		this.location = location;
		this.angles = angles;
		this.sights = new Vector3f();
		setSights();
	}
	
	public Vector3f location() {
		return location;
	}
	
	public Vector2f angles() {
		return angles;
	}
	
	public void update() {
		// turn
		if (Mouse.isButtonDown(0)) {
			angles.x -= 0.01f*Mouse.getDX();
			angles.y += 0.01f*Mouse.getDY();
		}
		
		// move
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			move(Move.FORWARD);
		else if (Keyboard.isKeyDown(Keyboard.KEY_S))
			move(Move.BACKWARD);
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			move(Move.LEFT);
		else if (Keyboard.isKeyDown(Keyboard.KEY_D))
			move(Move.RIGHT);
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			move(Move.UP);
		else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			move(Move.DOWN);
		
		// debug
		System.out.println("Location: (" + location.x + ", " + location.y + ", " + location.z + ")");
		System.out.println("Sights  : (" + sights.x + ", " + sights.y + ", " + sights.z + ")");
		System.out.println("Angles  : (" + (angles.x * 180 / Math.PI) % 360 + ", " + (angles.y * 180 / Math.PI) % 360 + ")");
	}
	
	public void look() {
		setSights();
		GLU.gluLookAt(location.x, location.y, location.z,
						sights.x, sights.y, sights.z,
						0f, 1f, 0f);
		
	}
	
	private void setSights() {
		sights.x = location.x + (float)(Math.sin(angles.y)*Math.cos(angles.x));
		sights.y = location.y + (float)(Math.cos(angles.y));
		sights.z = location.z + (float)(Math.sin(angles.y)*Math.sin(angles.x));
	}
	
	private enum Move {
		FORWARD, BACKWARD, LEFT, RIGHT, UP, DOWN;
	}
	
	private void move(Move move) {
		if (move == Move.FORWARD) {
			location.x += (float)(Math.sin(angles.y)*Math.cos(angles.x));
			location.z += (float)(Math.sin(angles.y)*Math.sin(angles.x));
		}
		if (move == Move.BACKWARD) {
			location.x -= (float)(Math.sin(angles.y)*Math.cos(angles.x));
			location.z -= (float)(Math.sin(angles.y)*Math.sin(angles.x));
		}
		if (move == Move.LEFT) {
			location.x += (float)(Math.sin(angles.y)*Math.cos(angles.x - Math.PI/2));
			location.z += (float)(Math.sin(angles.y)*Math.sin(angles.x - Math.PI/2));
		}
		if (move == Move.RIGHT) {
			location.x += (float)(Math.sin(angles.y)*Math.cos(angles.x + Math.PI/2));
			location.z += (float)(Math.sin(angles.y)*Math.sin(angles.x + Math.PI/2));
		}
		if (move == Move.UP) {
			location.y += .65f;
		}
		if (move == Move.DOWN) {
			location.y -= .65f;
		}
	}
}
