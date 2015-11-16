/* Program to approximate the value of 
 * a definite integral. User will pass
 * an interval, number of approximating 
 * rectangles, and method of integration
 * 
 * @author: Sam Cooledge
 * AD 315: HW 2
 */


public class Integrate {

 //create reference variables
  String str;
  
  //variables for the lower and upper limit
  static double a,b;
  
  static int n;
  
  //reference variables for 
  static double dx, TrapDx, LHE, RHE, Trap,count;
 
   static double actual;
  
    // Constructor takes the string called, the lower/upper limits and the number of rectangles
    Integrate(String str, double a, double b, int n) {
     
      this.str = str;
      
      this.count = 0;
      
      this.a = a;
      
      this.b = b;
      
      this.n = n;
      
      this.dx = ((b-a)/n);
      
      this.TrapDx = (b-a)/(2*n);

      
      //if else statements depending on which string is called
      if(str == "l") { 
       
     
        System.out.println(leftHand(a, b, n));
        
        
      } else if (str == "r") {
       
        System.out.println(rightHand(a, b, n));
       
      } else {
     
        System.out.println(trapPoint(a,b,n));
    }
    }
    
    //method for left hand endpoint
    private static double leftHand(double a, double b, int n){
      
      //loop to go through each number in the LHE equation
       for(int i = 0; i < n; i++) {
      
         
         //count originally set to 0. Count holds the initial equation through each index 
         count = evaluate((a+(i*dx)));
        
        //updates LHE to add each new count 
         LHE += count;
       }
       
       return LHE*dx;
       
    }
    
    //Method for Right Hand Endpoint. 
    private static double rightHand(double a, double b, int n) {
      
      //Same as LHE, except that index starts at 1 and ends at last
      for(int i = 1 ; i < n + 1; i++) {
        
         count = evaluate((a+(i*dx)));
         
     
         RHE += count;
       }
       
       return RHE*dx;
    
    }
   
    //Method for Trapezoid. Returns the actual value. 
    private static double trapPoint(double a, double b, int n){
      
      //First equation does not include multiply by 2
       Trap += evaluate(a+(0*dx)); 
       
       
       //starts at 1. multiplied by 2
        for(int i = 1; i < n; i++) {
      
          count = 2*(evaluate((a+(i*dx))));
     
          Trap += count;
    
        }
        
        //last equation does not include multiply by 2. Instead of i, N(the number of rectangles) is multiplied by delta x.
        Trap += evaluate((a+(n*dx)));
   
        return Trap*TrapDx;
        
    }
    // method to evalute a function
    private static double evaluate(double a) {
      //you can change the function to whatever you want. in this case f(x) = x^2
      return Math.pow(a,2);
    }

    
    // the main method
    public static void main(String[] args) {
     
      
      System.out.println("left hand endpoint:");
      
      //instantiate new object. call str "l", lower limit = 2, upper limit = 5, and use 6 rectangles
      Integrate left = new Integrate("l",2,5,6);
      
      System.out.println("The absolute error for the LHE:" + " " +Math.abs((((Math.pow(b,3)/3)-(Math.pow(a,3)/3))-(LHE*dx))));
      System.out.println("The relative error for the LHE:" + " " +Math.abs((((Math.pow(b,3)/3)-(Math.pow(a,3)/3))-(LHE*dx))/((Math.pow(b,3)/3)-(Math.pow(a,3)/3))));
      
      
      System.out.println("right hand endpoint:");
      Integrate right = new Integrate("r",2,5,6);
      System.out.println("The absolute error for the RHE:" + " " +Math.abs(((Math.pow(b,3)/3)-(Math.pow(a,3)/3))-(RHE*dx)));
      System.out.println("The relative error for the RHE:" + " " +Math.abs(((Math.pow(b,3)/3)-(Math.pow(a,3)/3))-(RHE*dx))/((Math.pow(b,3)/3)-(Math.pow(a,3)/3)));
      
      System.out.println("Trapezoid:");
      Integrate trap = new Integrate("t", 2,5,6);
      System.out.println("The absolute error for the Trapezoid:" + " " +Math.abs((((Math.pow(b,3)/3)-(Math.pow(a,3)/3))-(Trap*TrapDx))));
      System.out.println("The relative error for the Trapezoid:" + " " +Math.abs((((Math.pow(b,3)/3)-(Math.pow(a,3)/3))-(Trap*TrapDx))/((Math.pow(b,3)/3)-(Math.pow(a,3)/3))));
      
      
      System.out.println("The lower limit is:" + " " + a+ "," + " "+"the upper limit is:"+" " + b +"," + " " +"and the number of rectangles is:" + " " + n);
      System.out.println("The actual value is:" + " " +((Math.pow(b,3)/3)-(Math.pow(a,3)/3)));
      
    }
}