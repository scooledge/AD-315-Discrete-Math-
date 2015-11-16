/* Class to store integers between -128 and 127 as 
an array of 8 booleans. Two's complement will be used to 
deal with negative integers. 
*/
 
/*
 * Binary Conversion HW 1
 * AD 315
 * @author: Sam Cooledge
 * */



public class BinNum {
     
  public static final int BINARY = 8;
  
  //Create a boolean array
  public boolean[] binary;
  
  
 /*
  * constructor
  * @param int n
  * */
  public BinNum(int n) {
  
      //create alias for boolean array
      binary = new boolean[8];
      
      int index = 0;
      
      //while loop sets condition for for 8 index spaces
      while(index < 8){
        
        //if number(51) % 2 = 1 then it is true(1)
        if(n%2 == 1){
          
          // targets the last index point
          binary[7-index] = true;
        }
        //else number(51) % 2 != 1 then it is false(0)
        else
          binary[7-index] = false;
        
        //with each iteration for index position, number is divided by 2
        n/=2;
        
        //updates index position to next
        index++;       
      }
      
    }
     
  public static BinNum add(BinNum a, BinNum b) {
      
      //create new BinNum named c
      BinNum c = new BinNum(0);
     
      //create carry reference, set it to false
      boolean carry = false;
      
      //for loop set up to target the last index position
      for(int i = BINARY -1; i >= 0; i--){
        
        //use new refence c
        // use "exclusive or" operator to a,b, and carry.
        c.binary[i] = a.binary[i] ^ b.binary[i] ^ carry;
         
        
        int count = 0;
       
        //if statement to target index of a and b and update count
        if(a.binary[i]) count++;
       
        if(b.binary[i]) count++;
       
        if(carry) count++;
       
        //carry gets set to 2 or 3
        carry = (count ==2 || count == 3) ;
         
     }
      
    return c;
    
    }
           
    //private void negate() {
    
   //displays the binary number            
  private void displayByteNum() {{
    
    System.out.println("Binary: " );
  }
     
     for(int i = 0 ; i < binary.length; i++) {
        
        if(binary[i] == true)        
          System.out.print(1);
        else
          System.out.print(0);       
      }
      
      System.out.println();
      
    }
 
  //converts binary to int
  private void displayNum() {
     
    System.out.println("Back to Ints: ");
    
    int count = 0;
  
    int power = 1;
      
      for(int i = BINARY-1; i >= 0; i--) {
           
        if(binary[i] == true) {
          
          count += 1*power;
      
        }
        
           power *= 2;
      
        }
      
           System.out.println(count);
      
      
      }

    public static void main(String[] args) {
      
      BinNum a = new BinNum(51);
      BinNum b = new BinNum(4);
      a.displayByteNum();
      b.displayByteNum();
      BinNum c = add(a,b);
      c.displayByteNum();
      a.displayNum();
      b.displayNum();
    }
}