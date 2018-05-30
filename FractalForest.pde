////////////// Elias Berkhout ////////////////
///////////////// 23/11/17 //////////////////
////////////// Fractal Forest //////////////

float angle = 0;
float speed = 0;
int direction;
int mode = 0;

void setup() {
  size(1024, 768);
  stroke(230, 120, 140, 150);
  background(16);
}

void draw() {
  pushStyle();
  fill(0, 5);
  rect(0, 0, width, height);
  popStyle();
  // create a "tree"
  if (mode == 0) { // mode 1 – rotating 'forest' of fractal trees, ferns, & synapses of fluctuating size
    direction = round(random(3));
    pushMatrix();
    translate(width/2 + 20, height/2 + 20);
    rotate(-PI*direction);
    branch(random(50, 150), -PI*0.25);
    popMatrix();
    speed += 0.01;
  }
  if (mode == 1) { // mode 2 – the bad way to do a forest, very slow, but still visually effective
    for (int i = 0; i<=width; i+=width/4) {
      for (int j = 0; j<=height; j+=height/4) {
        direction = round(random(3));
        pushMatrix();
        translate(i, j);
        rotate(-PI*direction);
        branch(random(5, 150), -PI*0.25);
        popMatrix();
        speed += 0.05;
      }
    }
  }
}

void branch(float len, float ang) {
  rotate(speed);
  pushMatrix();
  rotate(ang);
  strokeWeight(len/15);
  line(0, 0, len, 0);
  translate(len, 0);

  if (len > 2.0) {
    angle = random(10) * width;
    float newAng1 = noise(speed);
    float newAng2 = noise(-speed*5);

    branch(len*0.7, -newAng1);

    branch(len*0.7, newAng2);

  } else {
    //draw rectangular leaf
    fill(0, 120, 0, 20);
    rect(0, 0, 3, 3);
  }
  popMatrix();
}

void keyPressed() { // mode switching & screenshots
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
