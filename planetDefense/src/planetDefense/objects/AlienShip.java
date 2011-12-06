

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
        //public double placement = 600;
        public double gameTime = 0;
        
        public Boolean missile1Fired = false;
        public Boolean missile2Fired = false;
        AlienMissile aMiss1;
        AlienMissile aMiss2;
        public double zPosition;
        public AlienShip(GL2 gl){
            aMiss1 = new AlienMissile(gl);
            aMiss2 = new AlienMissile(gl);

        }
        
        public double randomNum(double min, double max){
            Random rand =  new Random();
            double randomNumber = min + rand.nextDouble()*max - max/2;
            System.out.println(randomNumber);
            return randomNumber;
        }
        public double placement = randomNum(700,1000);

        private void initializeVectors() {


        }

        public void display(GL2 gl,GLUquadric SOLID,GLU glu,Texture alienTexture, double xPos, double yPos, double zPos){
       
            zPosition = zPos;
            
          
            alienTexture.enable(gl);
            alienTexture.bind(gl);
                       
            gl.glPushMatrix();

            gl.glTranslated(xPos, yPos, placement + 600);

            gl.glBegin(gl.GL_QUADS);
            gl.glColor3f(1,0,1);
            gl.glVertex3f(0,0,0);
            gl.glVertex3f(0,0,-10);
            gl.glVertex3f(10,0,-10);
            gl.glVertex3f(10,0,0);          
            gl.glEnd();
            
            //Missiles
            gl.glColor3f(1,1,1);
            gl.glPushMatrix();
            gl.glTranslatef(2,-0.25f,-10);
            aMiss1.display(gl, SOLID, glu, alienTexture);
            gl.glTranslatef(6,0,0);
            aMiss2.display(gl, SOLID, glu, alienTexture);
            gl.glPopMatrix();
            
            
            
            gl.glBegin(gl.GL_TRIANGLES);
            
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
            gl.glEnd();
            
            gl.glColor3f(1,1,1);
            
            gl.glPopMatrix();
            alienTexture.disable(gl);
            
            
        }
        public void update(long delta){
            
                placement -= 1;
                gameTime += .0625;
                if(placement <= -1000){
                    placement = zPosition;
                }
                //System.out.println(gameTime);
                
                if(gameTime >= 5 && gameTime <= 8){
                    missile1Fired = true;
                    if(gameTime >= 10){
                        
                    }
                }
                if(gameTime >= 12 && gameTime <= 15){
                    missile2Fired = true;
                    if(gameTime >= 10){
                        
                    }
                }
                if(gameTime >= 25 && gameTime <= 28){
                    gameTime = 0;
                }
                
                missile1Fired = aMiss1.Update(0, missile1Fired);
                missile2Fired = aMiss2.Update(0,missile2Fired);
                
            
        }


}
