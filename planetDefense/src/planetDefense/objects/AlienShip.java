

package planetDefense.objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.Vector;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.texture.*;
import java.util.Random;

import javax.media.opengl.GL2;

public class AlienShip extends GameObject {
        public boolean start = true;    
        public double placement = 600;
        private final double[] START_POS = {random(100,100), random(100,0), placement}; //Will Make Random

        public AlienShip(GL2 gl){
    

        }
        private double random(double min, double max){
            Random rand =  new Random();
            double randomNumber = rand.nextDouble() + min;
            return randomNumber;
            //double num = 0;
            //return num;
           //return min + (int)(Math.random() * ((max - min) + 1));
        }
        private void initializeVectors() {

            position = new double[]{START_POS[0], START_POS[1], START_POS[2]};
        }

        public void display(GL2 gl,GLUquadric SOLID,GLU glu,Texture alienTexture, int number, double xPos, double yPos){
       
          
            
            //gl.glPushMatrix();
            
            alienTexture.enable(gl);
            alienTexture.bind(gl);
                       
            gl.glPushMatrix();
            gl.glTranslated(xPos, yPos,placement);//random(0,100),random(0,100),placement);
            //gl.glTranslated(0,-5,400);
            
            
            //gl.glPushMatrix();
            gl.glBegin(gl.GL_TRIANGLES);
            gl.glColor3f(1,0,1);
            gl.glVertex3f(0,0,0);
            gl.glVertex3f(0,0,-10);
            gl.glVertex3f(10,0,-10);
            gl.glVertex3f(0,0,0);
            gl.glVertex3f(10,0,0);
            gl.glVertex3f(10,0,-10);
            
            
            
            
            gl.glColor3f(1,0,0);
            gl.glVertex3f(10,0,-10);
            gl.glVertex3f(10,0,0);
            gl.glVertex3f(5,5,-5);
            
            gl.glColor3f(0,1,0);
            gl.glVertex3f(0,0,0);
            gl.glVertex3f(0,0,-10);
            gl.glVertex3f(5,5,-5);
            
            gl.glColor3f(0,0,1);
            gl.glVertex3f(0,0,-10);
            gl.glVertex3f(10,0,-10);
            gl.glVertex3f(5,5,-5);
            
            gl.glColor3f(1,1,0);
            gl.glVertex3f(0,0,0);
            gl.glVertex3f(10,0,0);
            gl.glVertex3f(5,5,-5);
            
            
            gl.glColor3f(1,1,1);
            
            gl.glPopMatrix();
            alienTexture.disable(gl);
            
            //gl.glPopMatrix();
      // }
        }
        public void update(long delta){
            
                placement -= 1;
            
        }


}
