/**
 * The player.
 */

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector2f;

public class Player {
	
	private Vector3f location;
	private Vector2f angles;
	private Vector3f sights;
	
	/**
	 * Creates a new player at (0, 1, 0). Note: THERE SHOULD ONLY BE ONE PLAYER.
	 */
	public Player() {
		this(new Vector3f(0.0f, 1.0f, 0.0f));
	}
	
	/**
	 * Creates a new player at {@code location}. Note: THERE SHOULD ONLY BE ONE
	 * PLAYER.
	 * 
	 * @param location Original location of the player. Should be within the map.
	 */
	public Player(Vector3f location) {
		this(location, new Vector2f(0.0f, (float)(Math.PI/2)));
	}
	
	/**
	 * Creates a new player at {@code location} looking in the direction
	 * specified by {@code angles}. Note: THERE SHOULD ONLY BE ONE PLAYER.
	 * 
	 * @param location Original location of the player. Should be within the
	 *        map.
	 * @param angles Original direction of the player's camera. Angles (phi,
	 *        theta) in radians. Phi is the angle from the x-direction. Theta
	 *        is the angle from the zenith (the y-direction).
	 */
	public Player(Vector3f location, Vector2f angles) {
		this.location = location;
		this.angles = angles;
		this.sights = new Vector3f();
		setSights();
		Mouse.setGrabbed(true);
	}
	
	/**
	 * Returns the player's location.
	 * @return Returns the player's location.
	 */
	public Vector3f location() {
		return location;
	}
	
	/**
	 * Returns the player's camera angle.
	 * @return Returns the player's camera angle (phi, theta).
	 */
	public Vector2f angles() {
		return angles;
	}
	
	/**
	 * Updates player's game logic.
	 */
	public void update() {
		// turn
		if (Mouse.isGrabbed()) {
			angles.x += 0.01f*Mouse.getDX();
			// conditionals stop camera from turning upside down
			float dy = -0.01f*Mouse.getDY();
			if (!(angles.y < 0.2f && dy < 0.0f) &&
				!(angles.y > Math.PI - 0.2f && dy > 0.0f))
				angles.y += dy;
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
//		System.out.println("Location: (" + location.x + ", " + location.y + ", " + location.z + ")");
//		System.out.println("Sights  : (" + sights.x + ", " + sights.y + ", " + sights.z + ")");
//		System.out.println("Angles  : (" + (angles.x * 180 / Math.PI) % 360 + ", " + (angles.y * 180 / Math.PI) % 360 + ")");
	}
	
	/**
	 * Updates the player's camera.
	 */
	public void look() {
		setSights();
		GLU.gluLookAt(location.x, location.y, location.z,
						sights.x, sights.y, sights.z,
						0f, 1f, 0f);	
	}
	
	/**
	 * Updates the player's camera focus.
	 */
	private void setSights() {
		sights.x = location.x + (float)(Math.sin(angles.y)*Math.cos(angles.x));
		sights.y = location.y + (float)(Math.cos(angles.y));
		sights.z = location.z + (float)(Math.sin(angles.y)*Math.sin(angles.x));
	}
	
	/**
	 * Enums for movement.
	 */
	private enum Move {
		FORWARD, BACKWARD, LEFT, RIGHT, UP, DOWN;
	}
	
	/**
	 * Moves the player.
	 * 
	 * @param move Move must be a valid move.
	 */
	private void move(Move move) {
		// horizontal movement is currently separate from vertical movement
		// because floating will eventually be replaced with jumping
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
