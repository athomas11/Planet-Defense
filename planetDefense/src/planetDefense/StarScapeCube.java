/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package planetDefense;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.texture.*;
import javax.media.opengl.GL2;


public class StarScapeCube{
    
    final float vert1[] = {0,0,0};
    final float vert2[] = {0,1,0};
    final float vert3[] = {1,1,0};
    final float vert4[] = {1,0,0};
    final float vert5[] = {0,0,1};
    final float vert6[] = {0,1,1};
    final float vert7[] = {1,1,1};
    final float vert8[] = {1,0,1};
    
    public StarScapeCube(GL2 gl){
        
    }
    
    public void display(GL2 gl,GLUquadric SOLID,GLU glu,Texture starTexture){
        
        
        
        
        gl.glPushMatrix();
        starTexture.enable(gl);
        starTexture.bind(gl);
        gl.glTranslatef(-5000, -5000, -5000);
        //gl.glTranslated(0,-15,400);
        gl.glScalef(10000,10000,10000);
        //gl.glScalef(30,30,30);
        
        gl.glBegin(gl.GL_QUADS);
        gl.glNormal3f(0,0,1);
        gl.glTexCoord2f(0,0);
        gl.glVertex3fv(vert1,0);
        gl.glTexCoord2f(1,0);
        gl.glVertex3fv(vert2,0);
        gl.glTexCoord2f(1,1);
        gl.glVertex3fv(vert3,0);
        gl.glTexCoord2f(0,1);
        gl.glVertex3fv(vert4,0);
        gl.glEnd();
        
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2f(0,0);
        gl.glVertex3fv(vert1,0);
        gl.glTexCoord2f(0,1);
        gl.glVertex3fv(vert5,0);
        gl.glTexCoord2f(1,1);
        gl.glVertex3fv(vert6,0);
        gl.glTexCoord2f(1,0);
        gl.glVertex3fv(vert2,0);
        gl.glEnd();
        
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2f(0,0);
        gl.glVertex3fv(vert4,0);
        gl.glTexCoord2f(0,1);
        gl.glVertex3fv(vert3,0);
        gl.glTexCoord2f(1,1);
        gl.glVertex3fv(vert7,0);
        gl.glTexCoord2f(1,0);
        gl.glVertex3fv(vert8,0);
        gl.glEnd();
        
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2f(0,0);
        gl.glVertex3fv(vert8,0);
        gl.glTexCoord2f(0,1);
        gl.glVertex3fv(vert7,0);
        gl.glTexCoord2f(1,1);
        gl.glVertex3fv(vert6,0);
        gl.glTexCoord2f(1,0);
        gl.glVertex3fv(vert5,0);
        gl.glEnd();
        
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2f(0,0);
        gl.glVertex3fv(vert1,0);
        gl.glTexCoord2f(0,1);
        gl.glVertex3fv(vert5,0);
        gl.glTexCoord2f(1,1);
        gl.glVertex3fv(vert8,0);
        gl.glTexCoord2f(1,0);
        gl.glVertex3fv(vert4,0);
        gl.glEnd();
        
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2f(0,0);
        gl.glVertex3fv(vert2,0);
        gl.glTexCoord2f(0,1);
        gl.glVertex3fv(vert6,0);
        gl.glTexCoord2f(1,1);
        gl.glVertex3fv(vert7,0);
        gl.glTexCoord2f(1,0);
        gl.glVertex3fv(vert5,0);
        gl.glEnd();
        
        starTexture.disable(gl);
        gl.glPopMatrix();
        
    }
    
    public void Update(long delta){
        
    }
    
}
