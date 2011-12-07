package planetDefense;
import java.io.InputStream;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.io.*;
import java.awt.*;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

//import jig.engine.ImageResource;
//import jig.engine.ResourceFactory;

import planetDefense.objects.UserShip;
import planetDefense.objects.Asteroid;
import planetDefense.objects.AlienShip;
import java.util.Random;


import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.opengl.util.texture.spi.awt.IIOTextureProvider;
import com.jogamp.opengl.util.gl2.GLUT;
import java.io.*;

import java.applet.*;
import java.net.*;

import sun.audio.*;


public class PlanetDefense extends JFrame implements GLEventListener {

	private static final long serialVersionUID = 1L;
	private static final double CAM_BEHIND_SCALAR = 3;
	private static final double CAM_ABOVE_SCALAR = 1;
        public static int score;
	// FPSAnimator performs animation by repeatedly calling
	// the display method
	private FPSAnimator animator;

    // light properties
    private final float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
    private final float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
    private final float[] lightSpecular = {1.0f, 1.0f, 1.0f, 1.0f};
    private final float[] lightPosition = {1000.0f, 1000.0f, 0.0f, 0.0f};
	private final double MAX_POSITION = 10000.0;

        private GLU glu;
	private UserShip user;
	private Texture earth;
        private Texture asteroidTexture;
        private Texture randomTexture;
        private Texture starTexture;
        private StarScape Starscape;
        private StarScapeCube StarscapeCube;
        GLProfile glp = GLProfile.getDefault();
        public static Boolean GameStarted = false;
        //private Asteroid asteroid;
        private AlienShip alienShip;
        public int asteroidNumber = 1;
        public double gameTime = 0;
        public int NUMBER_OF_ALIENSHIPS = 30;
        public int NUMBER_OF_ASTEROIDS = 200;
        double xRand[] = new double[NUMBER_OF_ASTEROIDS];
        double yRand[] = new double[NUMBER_OF_ASTEROIDS];
        double zRand[] = new double[NUMBER_OF_ASTEROIDS];
        double xRandA[] = new double[NUMBER_OF_ALIENSHIPS];
        double yRandA[] = new double[NUMBER_OF_ALIENSHIPS];
        double zRandA[] = new double[NUMBER_OF_ALIENSHIPS];
        public Asteroid asteroids[] = new Asteroid[NUMBER_OF_ASTEROIDS];
        public AlienShip alienShips[] = new AlienShip[NUMBER_OF_ALIENSHIPS];
        double earthRotations = 0;
        GLUT glut = new GLUT();
        
	public static void main (final String[] args){
		final PlanetDefense app = new PlanetDefense();
		app.run();
	}
	public PlanetDefense(){
		super("Space");
//		ResourceFactory.getFactory().loadResources("resources/", "resources.xml");
	}

	public void centerWindow(final Component frame){
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final Dimension frameSize = frame.getSize();

		if(frameSize.width > screenSize.width){
			frameSize.width = screenSize.width;
		}
		if(frameSize.height > screenSize.height){
			frameSize.height = screenSize.height;
		}

		frame.setLocation(
			(screenSize.width - frameSize.width) >> 1,
			(screenSize.height - frameSize.height) >> 1
		);
	}
        
        public double randomNum(double min, double max){
            Random rand =  new Random();
            double randomNumber = min + rand.nextDouble()*max - max/2;
            //System.out.println(randomNumber);
            return randomNumber;
        }

	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#display(javax.media.opengl.GLAutoDrawable)
	 */
	@Override
	public void display(final GLAutoDrawable glDrawable) {
		
                update();
		final GL2 gl = glDrawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		positionCamera();
		gl.glPushMatrix();

		// draw planet
            GLUquadric SOLID = glu.gluNewQuadric();
            glu.gluQuadricDrawStyle( SOLID, GLU.GLU_FILL);
            //glu.gluQuadricDrawStyle(qobj0, GLU.GLU_FLAT);
            glu.gluQuadricNormals( SOLID, GLU.GLU_SMOOTH );
//                        gl.glColor4f(1, 0, 0, 1);
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3f(0, 0, 0);
            gl.glVertex3f(400, 0, 0);
            gl.glEnd();
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3f(0, 0, 0);
            gl.glVertex3f(0, 400, 0);
            gl.glEnd();
            gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex3f(0, 0, 0);
            gl.glVertex3f(0, 0, 400);
            gl.glEnd();
            glu.gluQuadricTexture(SOLID, true);

            earth.enable(gl);
            earth.bind(gl);
            
                
            
            gl.glDisable(GL2.GL_LIGHTING);
            gl.glPushMatrix();
            gl.glRotated(earthRotations,0,1,0);
            glu.gluSphere(SOLID, 100f, 50, 50);
            gl.glPopMatrix();
            earth.disable(gl);
            //Starscape.display(gl,SOLID, glu, starTexture);
            StarscapeCube.display(gl,SOLID,glu,starTexture);
            //if(GameStarted){
            for(int i = 0; i < NUMBER_OF_ASTEROIDS; i++){
                asteroids[i].display(gl, SOLID, glu, asteroidTexture, xRand[i], yRand[i], zRand[i]);
                
            }//}
            //if(GameStarted){
            for(int i = 0; i < NUMBER_OF_ALIENSHIPS; i++){
                alienShips[i].display(gl, SOLID, glu, asteroidTexture, xRandA[i], yRandA[i], zRandA[i]);
            }//}
            
            gl.glEnable(GL2.GL_LIGHTING);

            
            user.display(gl);
            
            
                 


		gl.glPopMatrix();
		glDrawable.swapBuffers();
	}


	/**
	 *
	 */
	private void positionCamera() {
		double[] cameraPosition = user.getPosition().clone();
		double[] userDirection = user.getRollAxis();
		double[] userUp = user.getYawAxis();
		for(int i = 0; i < 3; ++i){
			cameraPosition[i] += CAM_BEHIND_SCALAR * -userDirection[i];
			cameraPosition[i] += CAM_ABOVE_SCALAR * userUp[i];
		}

		double[] userPosition = user.getPosition();
		glu.gluLookAt(cameraPosition[0], cameraPosition[1], cameraPosition[2], // camera position
				userPosition[0], userPosition[1], userPosition[2], 	// look at position
				userUp[0], userUp[1], userUp[2]);	// up direction
//		glu.gluLookAt(300, 100, 200, // camera position
//				300, 0, 0, 	// look at position
//				0, 1, 0);	// up direction

	}

	/**
	 *
	 */
	private void update() {
                if(GameStarted){
                    gameTime += .03125;
                }
		user.update(0);
                //alienShip.update(0);
                //System.out.println(gameTime);
                earthRotations += .03125;
                int ph = 0;
                if(gameTime > 5){
                    if(GameStarted){
                        for(int i = 0; i<NUMBER_OF_ASTEROIDS*1/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 15){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*2/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 25){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*3/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 35){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*4/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 45){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*5/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 55){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*6/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 65){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*7/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 75){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*8/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 85){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*9/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 95){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*10/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 105){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*11/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 115){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*12/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 125){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*13/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 135){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*14/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 145){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*15/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 155){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*16/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 165){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*17/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 175){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*18/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
                if(gameTime > 185){
                    if(GameStarted){
                        for(int i = ph; i<NUMBER_OF_ASTEROIDS*19/40; i++){
                        asteroids[i].update(0);
                        ph++;
                        System.out.println(ph);
                    }}
                }
               
                int ph2 = 0;
                if(gameTime > 10){
                    if(GameStarted){
                        for(int i = 0; i<NUMBER_OF_ALIENSHIPS/6; i++){
                            alienShips[i].update(0);
                            ph2++;
                        }
                    }
                }
                if(gameTime > 25){
                    if(GameStarted){
                        for(int i = ph2; i<NUMBER_OF_ALIENSHIPS/6; i++){
                            alienShips[i].update(0);
                            ph2++;
                        }
                    }
                }
                if(gameTime > 40){
                    if(GameStarted){
                        for(int i = ph2; i<NUMBER_OF_ALIENSHIPS/6; i++){
                            alienShips[i].update(0);
                            ph2++;
                        }
                    }
                }
                
                /*
                if(GameStarted){
                for(int i = 0; i<NUMBER_OF_ASTEROIDS; i++){
                    asteroids[i].update(0);
                }}
                *//*
                if(GameStarted){
                for(int i = 0; i<NUMBER_OF_ALIENSHIPS; i++){
                    alienShips[i].update(0);
                }}
                */
                Starscape.Update(0);
                StarscapeCube.Update(0);
                //score += 1;
	}

	public void displayChanged(final GLAutoDrawable glDrawable, final boolean modeChanged, final boolean deviceChanged){
		glDrawable.getGL().getGL2().glViewport(0, 0, getWidth(), getHeight());
		display(glDrawable);
	}

	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#dispose(javax.media.opengl.GLAutoDrawable)
	 */
	@Override
	public void dispose(final GLAutoDrawable glDrawable) {
	}





	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#init(javax.media.opengl.GLAutoDrawable)
	 */
	@Override
	public void init(final GLAutoDrawable glDrawable) {
		glu = new GLU();
                score = 0;
                

		final GL2 gl = glDrawable.getGL().getGL2();
		gl.glShadeModel(GL2.GL_FLAT);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    gl.glEnable(GL2.GL_DEPTH_TEST);
	    gl.glDepthFunc(GL2.GL_LEQUAL);
		// set light properties
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, lightSpecular, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);

		gl.glPointSize(1.0f);
		gl.glLineWidth(1.0f);
                //if(GameStarted){
                for(int i = 0; i < NUMBER_OF_ASTEROIDS; i++){
                    xRand[i] = randomNum(0,300);//-300,300);
                    yRand[i] = randomNum(0,300);//-200,200);
                    zRand[i] = randomNum(700, 1200);
                    asteroids[i] = new Asteroid(gl, zRand[i]);
                }//}
                //if(GameStarted){
                for(int i = 0; i < NUMBER_OF_ALIENSHIPS; i++){
                    xRandA[i] = randomNum(0,200);
                    yRandA[i] = randomNum(0,200);
                    zRandA[i] = randomNum(800, 1000);
                    alienShips[i] = new AlienShip(gl);
                    
                }
                //}
                //asteroid = new Asteroid(gl, 0);
		user = new UserShip(gl);
                Starscape = new StarScape(gl);
                StarscapeCube = new StarScapeCube(gl);
                //alienShip = new AlienShip(gl);
		this.getContentPane().getComponent(0).addKeyListener(user);
		this.getContentPane().getComponent(0).addMouseListener(user);
		this.getContentPane().getComponent(0).requestFocus();

        try {

                    
                    InputStream stream = getClass().getResourceAsStream("planet2.jpg");
                    TextureData data = TextureIO.newTextureData(glp, stream, false, "jpg");
                    earth = TextureIO.newTexture(data);
                    stream = getClass().getResourceAsStream("asteroid1.jpg");
                    data = TextureIO.newTextureData(glp, stream, false, "jpg");
                    asteroidTexture = TextureIO.newTexture(data);
                    stream = getClass().getResourceAsStream("klendathu.png");
                    data = TextureIO.newTextureData(glp, stream, false, "png");
                    randomTexture = TextureIO.newTexture(data);
                    stream = getClass().getResourceAsStream("starscape2.jpg");
                    data = TextureIO.newTextureData(glp, stream, false, "jpg");
                    starTexture = TextureIO.newTexture(data);
                    
                    
                    

          }
          catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
          }
        
            
        
        
	}


	private double[] normalize(final double[] vector) {
		final double magnitude = Math.sqrt(vector[0]*vector[0] + vector[1]*vector[1] + vector[2]*vector[2]);
		final double[] normal = new double[3];
		normal[0] = vector[0]/magnitude;
		normal[1] = vector[1]/magnitude;
		normal[2] = vector[2]/magnitude;
		return normal;
	}



	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#reshape(javax.media.opengl.GLAutoDrawable, int, int, int, int)
	 */
	@Override
	public void reshape(final GLAutoDrawable glDrawable, final int x, final int y, final int width, final int height) {
		final GL2 gl = glDrawable.getGL().getGL2();
		// set viewport
		gl.glViewport(0, 0, width, height);
		// set perspective
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		final float widthHeightRatio = (float) width / (float) height;
		glu.gluPerspective(45.0f, widthHeightRatio, 1.0f, 1.5*MAX_POSITION);
		display(glDrawable);
	}


	private void run() {
                /*
                final SplashScreen splash = SplashScreen.getSplashScreen();
                if(splash == null){
                    System.out.println("Splash Screen Failed To Load.");
                }*/
                
            
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final GLProfile prof = GLProfile.get(GLProfile.GL2);
		final GLCapabilities glcaps = new GLCapabilities(prof);
		glcaps.setDoubleBuffered(true);
		glcaps.setHardwareAccelerated(true);
		glcaps.setDepthBits(16);
		final GLCanvas glcanvas = new GLCanvas(glcaps);
		glcanvas.addGLEventListener(this);
//		glcanvas.setSize(width, height);
                
		this.getContentPane().add(glcanvas, BorderLayout.CENTER);
                //this.getContentPane().add(score, BorderLayout.NORTH); 
//		setSize(800, 800);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		centerWindow(this);
		animator = new FPSAnimator(30);
		animator.add(glcanvas);
		setVisible(true);
		animator.start();
	}



}