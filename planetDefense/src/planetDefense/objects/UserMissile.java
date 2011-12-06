/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planetDefense.objects;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.texture.*;
import javax.media.opengl.GL2;

public class UserMissile extends GameObject {
    
    public double movement = 0; //get x,y,z position and angle
    
    public UserMissile(GL2 gl){
        
    }
    
    public void display(GL2 gl,GLUquadric SOLID,GLU glu,Texture alienTexture){
         
    }
    
    public void Update(long delta){
        movement -= 1;//get direction user is facing and calc trajectory
    }
    
    
}
