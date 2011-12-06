
package planetDefense.objects;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.texture.*;
import javax.media.opengl.GL2;

public class AlienMissile extends GameObject {
    public double movement = 0; 
    
    public AlienMissile(GL2 gl){
        
    }
    
    public void display(GL2 gl,GLUquadric SOLID,GLU glu,Texture alienTexture){
        gl.glPushMatrix();
        gl.glTranslated(0,0,movement);
        gl.glPushMatrix();
        gl.glScalef(0.5f, 0.5f, 1f);
        gl.glPushMatrix();
        glu.gluCylinder(SOLID, 1, 1, 5, 10, 10);
        gl.glTranslatef(0,0,5);
        glu.gluDisk(SOLID, 0, 1, 10, 10);
        gl.glTranslatef(0,0,-5);
        gl.glRotated(180, 0,1,0);
        glu.gluCylinder(SOLID, 1, 0, 1, 10, 10);
        gl.glPopMatrix();
        gl.glPopMatrix();
        gl.glPopMatrix();
    }
    
    public Boolean Update(long delta, Boolean fired){
        
        if(fired){
           movement -= 2; 
        }
        
        if(movement <= -500){
            movement = 0;
            fired = false;
            
        }
        
        return fired;
    }
    
}
