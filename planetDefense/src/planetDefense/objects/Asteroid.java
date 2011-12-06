

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

public class Asteroid extends GameObject {
        public boolean start = true;    
        //public double placement;
        public double zPosition;

        public Asteroid(GL2 gl, double zPos){
            placement = zPos;

        }
        
        public double randomNum(double min, double max){
            Random rand =  new Random();
            double randomNumber = min + rand.nextDouble()*max - max/2;
            System.out.println(randomNumber);
            return randomNumber;
        }
        
        public double placement = randomNum(700,1000);
        
        private void initializeVectors() {
            
            //position = new double[]{START_POS[0], START_POS[1], START_POS[2]};
        }

        public void display(GL2 gl,GLUquadric SOLID,GLU glu,Texture asteroidTexture, double xPos, double yPos, double zPos){
       
            zPosition = zPos;
            //placement = zPos;
            
            gl.glPushMatrix();
            
            asteroidTexture.enable(gl);
            asteroidTexture.bind(gl);
            gl.glPushMatrix();
            gl.glTranslated(xPos, yPos, placement + 800);
            glu.gluSphere(SOLID, 8f, 5, 5);
            gl.glPopMatrix();
            asteroidTexture.disable(gl);      
            gl.glPopMatrix();

        }
        //double placement = zPosition;
        public void update(long delta){
            
                if(placement <= -1000){
                    placement = zPosition;
                }
                placement -= 5;
                
            
        }


}
