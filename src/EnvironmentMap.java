/**
 * Keeps track of the environment.
 */


import static org.lwjgl.opengl.GL11.*;
import java.util.*;
import org.lwjgl.util.vector.Vector3f;

public class EnvironmentMap {
	
	private int mapSize;
	private ArrayList<Block> blocks;
	private Block cage;
	
	/**
	 * Creates a new map, default size 30.
	 */
	public EnvironmentMap() {
		this(30);
	}
	
	/**
	 * Creates a new map with {@code mapSize * 2} blocks.
	 * 
	 * @param mapSize Size of the map. Should be greater than zero.
	 */
	public EnvironmentMap(int mapSize) {
		this.mapSize = mapSize;
		this.blocks = new ArrayList<Block>();
		
		// initializes ground
		cage = new Block(new Vector3f(mapSize, mapSize*2, mapSize), new Vector3f(-mapSize, 0, -mapSize));
		
		// initializes random blocks
		for (int i = 0; i < mapSize * 2; i++) {
			createBlock();
		}
	}
	
	/**
	 * Renders the environment.
	 */
	public void draw() {
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);
		
		// draw ground
		cage.draw();
		
		// draw blocks
		for (Block bl : blocks)
			bl.draw();
		
		glDisable(GL_COLOR_MATERIAL);
	}
	
	/**
	 * Creates a new block with side lengths 0-5 at a random location.
	 */
	public void createBlock() {
		float x = (float)(Math.random()-0.5f) * mapSize * 2;
		float y = (float)(Math.random()) * mapSize * 2;
		float z = (float)(Math.random()-0.5f) * mapSize * 2;
		float dx = (float)Math.random() * 5;
		float dy = (float)Math.random() * 5;
		float dz = (float)Math.random() * 5;
		blocks.add(new Block(new Vector3f(x, y, z), new Vector3f(x+dx, y+dy, z+dz)));
	}
}
