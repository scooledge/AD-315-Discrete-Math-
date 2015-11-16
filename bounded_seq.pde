/* AD 315
 * Programming Assignment 3
 *
 * Determining whether sequences are
 * bounded in a discrete complex space
 *
 * The sequence is recursively defined as
 * z_{n+1} = (z_n)^2 + c.
 * Here c is a complex number and z_0 = c
 * 
 
 *@author: Samuel Cooledge
 
 */

// variables for the real and complex part of 
// a complex number
double a, b;

// variables to determine the real and
// imaginary range of the viewing window
double xmin, xmax, ymin, ymax, dx, dy;

// variable to determine how many iterations
// of the sequence we will look at.
int iterations;

// variable to determine number of iterations
int iterationCount;

// color variable to represent number of iterations
color c;

// since we are in a discrete space, we will choose a finite 
// number to represent infinity
int infinity;


void setup() {
  // be sure to fill in the size method
  size(1440, 960);
  // HSB stands for hue, saturation, and brightness
  colorMode(HSB);
  // we are only writing to the display window once
  noLoop();
  // initialize the background.
  background(255);
  
  // set the number of iterations
  iterations = 100;
  
  // here we decide what our discrete
  // infinity will be
  infinity = 12;
  
  // initialize the viewing window
  xmin = -3.2;
  xmax = 1.6;
  ymin = -1.35;
  ymax = 1.35;
  // dx and dy should be defined using the 
  // height and width keywords. 
  dx = Math.abs(xmin-xmax)/width;
  dy = Math.abs(ymax-ymin)/height;
}

void draw(){ 
  
  
  // to use the pixels[] array you need to first 
  // call the loadpixels() method
  loadPixels();
  
  for (int i = 0; i < width; i++) {
    for (int j = 0; j < height; j++) {
      // a and b represent the current value of c
      // which depends on i and j. Here c = a + bi
      
      a = xmin + i * dx;
      b = ymin + j * dy;
      
 
      // determine whether the sequence is bounded
      // at the current value of c
      iterationCount = checkBounded(a,b);
      
      // write a color to the pixels[] array based
      // on iterationCount
      c = color(10-iterationCount);
      pixels[i+width*j] = c;
    }
  }
  
  // you need to call updatePixels() to display
  // the colors stored in the pixels[] array.
  updatePixels();

}
// method to check if the sequence is bounded. x 
// is the real part of c, and y is the imaginary part.


int checkBounded(double x, double y) {
  
  // result is used to determine if a sequence
  // is bounded for a given value of c
  int result;
  
  // temporary variables used for computation
  double x1 = x ;
  double x2;
  double y1 = y;
  double y2;
  
  // this loop will check if the sequence
  // is bounded for the particular value hof c
  for(int i = 0; i < iterations; i++) {
     x2 = (x1*x1)-(y1*y1)+a; //real
     y2 = 2*x1*y1+b; //complex
     result = (int)(x2*x2+y2*y2); //cast as int bc number is a double
  
    // if result is larger than our discrete infinity
    // return the number of iterations it took to get
    // to the discrete infinity
    if (result > infinity) {
      return i;
    }
    x1 = x2;
    y1 = y2;
  }

  // once we've ran through the specified
  // number of iterations we decide the
  // sequence is bounded for the particular
  // value of c and return 0 as a result.
  return 0;
}