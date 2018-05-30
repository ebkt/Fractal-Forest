import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FractalForest extends PApplet {

////////////// Elias Berkhout ////////////////
///////////////// 23/11/17 //////////////////
////////////// Fractal Forest //////////////

float angle = 0;
float speed = 0;
int direction;
int mode = 0;

public void setup() {
  
  stroke(230, 120, 140, 150);
  background(16);
}

public void draw() {
  pushStyle();
  fill(0, 5);
  rect(0, 0, width, height);
  popStyle();
  // create a "tree"
  if (mode == 0) { // mode 1 \u2013 rotating 'forest' of fractal trees, ferns, & synapses of fluctuating size
    direction = round(random(3));
    pushMatrix();
    translate(width/2 + 20, height/2 + 20);
    rotate(-PI*direction);
    branch(random(50, 150), -PI*0.25f);
    popMatrix();
    speed += 0.01f;
  }
  if (mode == 1) { // mode 2 \u2013 the bad way to do a forest, very slow, but still visually effective
    for (int i = 0; i<=width; i+=width/4) {
      for (int j = 0; j<=height; j+=height/4) {
        direction = round(random(3));
        pushMatrix();
        translate(i, j);
        rotate(-PI*direction);
        branch(random(5, 150), -PI*0.25f);
        popMatrix();
        speed += 0.05f;
      }
    }
  }
}

public void branch(float len, float ang) {
  rotate(speed);
  pushMatrix();
  rotate(ang);
  strokeWeight(len/15);
  line(0, 0, len, 0);
  translate(len, 0);

  if (len > 2.0f) {
    angle = random(10) * width;
    float newAng1 = noise(speed);
    float newAng2 = noise(-speed*5);

    branch(len*0.7f, -newAng1);

    branch(len*0.7f, newAng2);

  } else {
    //draw rectangular leaf
    fill(0, 120, 0, 20);
    rect(0, 0, 3, 3);
  }
  popMatrix();
}

public void keyPressed() { // mode switching & screenshots
  if (key == '1') {
    mode = 0;
  }
  if (key == '2') {
    mode = 1;
  }
  if (key == 's'){
    saveFrame("fractalForest-#####.png");
  }
}
  public void settings() {  size(1024, 768); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FractalForest" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
