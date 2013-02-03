import java.util.List;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector3f;
import static org.lwjgl.opengl.GL11.*;

public class Model {

    public List<Vector3f> vertices;
    public List<Vector3f> normals;
    public List<Face> faces;
    
    private boolean isLight;
    private Vector3f location;
    private Vector3f color;

    public Model() {
    	this.vertices = new ArrayList<Vector3f>();
    	this.normals = new ArrayList<Vector3f>();
    	this.faces = new ArrayList<Face>();
    	
    	this.isLight = false;
    	this.location = new Vector3f();
    	this.color = new Vector3f(0.4f, 0.4f, 0.3f);
    }
    
    public void draw() {
    	draw(location);
    }
    
    public void draw(Vector3f loc) {
    	glPushMatrix();
    	glTranslatef(loc.x, loc.y, loc.z);
    	
    	glColor3f(color.x, color.y, color.z);
//    	glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    	
    	if (isLight) {
    		glEnable(GL_COLOR_MATERIAL);
    		glColorMaterial(GL_FRONT, GL_EMISSION);
    	}
    	
    	glEnable(GL_CULL_FACE);
    	glCullFace(GL_BACK);
    	
    	glBegin(GL_TRIANGLES);
    	for (Face face : this.faces) {
    		Vector3f n1 = normals.get((int) (face.normal().x - 1));
			glNormal3f(n1.x, n1.y, n1.z);
			Vector3f v1 = vertices.get((int) (face.vertex().x - 1));
			glVertex3f(v1.x, v1.y, v1.z);

			Vector3f n2 = normals.get((int) (face.normal().y - 1));
			glNormal3f(n2.x, n2.y, n2.z);
			Vector3f v2 = vertices.get((int) (face.vertex().y - 1));
			glVertex3f(v2.x, v2.y, v2.z);

			Vector3f n3 = normals.get((int) (face.normal().z - 1));
			glNormal3f(n3.x, n3.y, n3.z);
			Vector3f v3 = vertices.get((int) (face.vertex().z - 1));
			glVertex3f(v3.x, v3.y, v3.z);
		}
		glEnd();
    	
		glDisable(GL_CULL_FACE);
		if (isLight)
			glDisable(GL_COLOR_MATERIAL);
		glPopMatrix();
    }
    
    public void setLight(boolean isLight) {
    	this.isLight = isLight;
    }
    
    public void setLocation(Vector3f loc) {
    	this.location = loc;
    }
    
    public void setColor(Vector3f color) {
    	this.color = color;
    }
}
