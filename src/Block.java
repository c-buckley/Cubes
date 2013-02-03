/**
 * Cuboid.
 */

import static org.lwjgl.opengl.GL11.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.util.vector.Vector3f;


public class Block {
	private Vector3f topLeft;
	private Vector3f bottomRight;
	private Vector3f[] colors;
	
	/**
	 * Creates a new block at specified location with side lengths equal to the
	 * distance between Vector3f components. Side colors are random.
	 * 
	 * @param topLeft Top-left corner of the cuboid.
	 * @param bottomRight Top-right corner of the cuboid. Distance between each
	 *        top-left component and each top-right component should be zero.
	 */
	public Block(Vector3f topLeft, Vector3f bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		// choose colors
		colors = new Vector3f[6];
		Random randy = new Random();
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Vector3f(randy.nextFloat(), randy.nextFloat(), randy.nextFloat());
		}
	}
	
	/**
	 * Renders the block.
	 */
	public void draw() {
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		glBegin(GL_QUADS);
			
			// front
			glColor3f(colors[0].x, colors[0].y, colors[0].z);
			glVertex3f(topLeft.x, topLeft.y, topLeft.z);
			glVertex3f(topLeft.x, bottomRight.y, topLeft.z);
			glVertex3f(bottomRight.x, bottomRight.y, topLeft.z);
			glVertex3f(bottomRight.x, topLeft.y, topLeft.z);
			
			// left
			glColor3f(colors[1].x, colors[1].y, colors[1].z);
			glVertex3f(topLeft.x, topLeft.y, bottomRight.z);
			glVertex3f(topLeft.x, bottomRight.y, bottomRight.z);
			glVertex3f(topLeft.x, bottomRight.y, topLeft.z);
			glVertex3f(topLeft.x, topLeft.y, topLeft.z);
			
			// right
			glColor3f(colors[2].x, colors[2].y, colors[2].z);
			glVertex3f(bottomRight.x, topLeft.y, topLeft.z);
			glVertex3f(bottomRight.x, bottomRight.y, topLeft.z);
			glVertex3f(bottomRight.x, bottomRight.y, bottomRight.z);
			glVertex3f(bottomRight.x, topLeft.y, bottomRight.z);
			
			// top
			glColor3f(colors[3].x, colors[3].y, colors[3].z);
			glVertex3f(topLeft.x, topLeft.y, topLeft.z);
			glVertex3f(bottomRight.x, topLeft.y, topLeft.z);
			glVertex3f(bottomRight.x, topLeft.y, bottomRight.z);
			glVertex3f(topLeft.x, topLeft.y, bottomRight.z);
			
			// base
			glColor3f(colors[4].x, colors[4].y, colors[4].z);
			glVertex3f(topLeft.x, bottomRight.y, topLeft.z);
			glVertex3f(topLeft.x, bottomRight.y, bottomRight.z);
			glVertex3f(bottomRight.x, bottomRight.y, bottomRight.z);
			glVertex3f(bottomRight.x, bottomRight.y, topLeft.z);

			// back
			glColor3f(colors[5].x, colors[5].y, colors[5].z);
			glVertex3f(bottomRight.x, topLeft.y, bottomRight.z);
			glVertex3f(bottomRight.x, bottomRight.y, bottomRight.z);
			glVertex3f(topLeft.x, bottomRight.y, bottomRight.z);
			glVertex3f(topLeft.x, topLeft.y, bottomRight.z);
			
		glEnd();
	}
}