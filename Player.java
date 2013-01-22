import org.lwjgl.util.vector.Vector3f;


public class Player {
	
	private Vector3f location;
	
	public Player() {
		this(new Vector3f(0, 0, 0));
	}
	
	public Player(Vector3f location) {
		this.location = location;
	}
	
	public Vector3f location() {
		return location;
	}
	
	public void adjustLocation(Vector3f delta) {
		this.location.x += delta.x;
		this.location.y += delta.y;
		this.location.z += delta.z;
	}
}
