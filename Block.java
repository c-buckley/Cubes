import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;


public class Block {
	private Vector3f topLeft;
	private Vector3f bottomRight;
	
	public Block(Vector3f topLeft, Vector3f bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}
	
	public void render() {
		glBegin(GL_QUADS);
			
			// front
			glColor3f(0f, 0f, 1f);
			glVertex3f(topLeft.x, topLeft.y, topLeft.z);
			glVertex3f(topLeft.x, bottomRight.y, topLeft.z);
			glVertex3f(bottomRight.x, bottomRight.y, topLeft.z);
			glVertex3f(bottomRight.x, topLeft.y, topLeft.z);
			
			// left
			glColor3f(0f, 1f, 0f);
			glVertex3f(topLeft.x, topLeft.y, bottomRight.z);
			glVertex3f(topLeft.x, bottomRight.y, bottomRight.z);
			glVertex3f(topLeft.x, bottomRight.y, topLeft.z);
			glVertex3f(topLeft.x, topLeft.y, topLeft.z);
			
			// right
			glColor3f(0f, 1f, 0f);
			glVertex3f(bottomRight.x, topLeft.y, topLeft.z);
			glVertex3f(bottomRight.x, bottomRight.y, topLeft.z);
			glVertex3f(bottomRight.x, bottomRight.y, bottomRight.z);
			glVertex3f(bottomRight.x, topLeft.y, bottomRight.z);
			
			// top
			glColor3f(1f, 0f, 0f);
			glVertex3f(topLeft.x, topLeft.y, topLeft.z);
			glVertex3f(bottomRight.x, topLeft.y, topLeft.z);
			glVertex3f(bottomRight.x, topLeft.y, bottomRight.z);
			glVertex3f(topLeft.x, topLeft.y, bottomRight.z);
			
			// base
			glColor3f(1f, 0f, 0f);
			glVertex3f(topLeft.x, bottomRight.y, topLeft.z);
			glVertex3f(topLeft.x, bottomRight.y, bottomRight.z);
			glVertex3f(bottomRight.x, bottomRight.y, bottomRight.z);
			glVertex3f(bottomRight.x, bottomRight.y, topLeft.z);

			// back
			glColor3f(0f, 0f, 1f);
			glVertex3f(bottomRight.x, topLeft.y, bottomRight.z);
			glVertex3f(bottomRight.x, bottomRight.y, bottomRight.z);
			glVertex3f(topLeft.x, bottomRight.y, bottomRight.z);
			glVertex3f(topLeft.x, topLeft.y, bottomRight.z);
			
		glEnd();
	}
}