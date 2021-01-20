import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class StatThread implements Runnable {
	private Socket socket;
//do fib memoized, give more text to user, exit: get out of loop and close client and server socket, 
	//Count: in O(n) it counts the number of 1 or number of 2 or number of 3 or 4, seperated by spaces,
	// ex: 
	//MIN, MAX... to lowercase, and give 0 , 1 2, 3, 4.. instead of MIN, MAX
	StatThread(Socket socket){
		this.socket = socket;
	}
	
	/**
	 * Converts an array of Strings to an array of int values
	 * @param takes an array of Strings
	 * @return Array of int values
	 */
	static int[] ToInt(String[] arrString)
	{
		int[] arrInt = new int[arrString.length];
		for(int i=0; i<arrString.length; i++)
		{
		   arrInt[i] = Integer.parseInt(arrString[i]);
		}
		return arrInt;
	}
	/**
	 * Converts an array of Strings to an array of doubles
	 * @param takes an array of Strings
	 * @return Array of double values
	 */
	double[] ToDouble(String[] arrString)
	{
		double[] arrDouble = new double[arrString.length];
		for(int i=0; i<arrString.length; i++)
		{
		   arrDouble[i] = Double.parseDouble(arrString[i]);
		}
		return arrDouble;
	}
	
	/**Calculates the median of an unsorted array
	 * @param Takes an array of doubles
	 * @return  the median of the unsorted array
	 */
	static double median(double[] values) {
	     // sort array
	     Arrays.sort(values);
	     double median;
	     // get count of scores
	     int totalElements = values.length;
	     // check if total number of scores is even
	     if (totalElements % 2 == 0) {
	        double sumOfMiddleElements = values[totalElements / 2] +
	                                  values[totalElements / 2 - 1];
	        // calculate average of middle elements
	        median = (sumOfMiddleElements) / 2;
	     } else {
	        // get the middle element
	        median = values[values.length / 2];
	  }
	  return median;
	 }
	/**
	 * Calculates the Mode of an unsorted array
	 * @param arr of type int[]
	 * @return Mode of this array of type int 
	 */
    public static double getMode(int[] arr) {    
        int[] arrCopy = arr.clone();
        Arrays.sort(arrCopy);
        
        Map<Integer, Integer> freq = new TreeMap<>();
        for (int i : arrCopy) {
            if (!freq.containsKey(i))
                freq.put(i, 1);
            else
                freq.put(i, freq.get(i) + 1);
        }

        double mode = arrCopy[0];
        int maxFreq = 1;

        for (Map.Entry<Integer, Integer> me : freq.entrySet()) {
            if (me.getValue() > maxFreq) {
                maxFreq = me.getValue();
                mode = me.getKey();
            }
        }

        return mode;
    }
    /**
     * Product of the entries in an array
     * @param ar array of numbers
     * @return The result of multiplying all the values in a range of cell locations
     */
    public static double Aproduct(double ar[]) 
    { 
        double result = 1; 
        for (int i = 0; i < ar.length; i++) 
            result = result * ar[i]; 
        return result; 
    } 
    /**
     * The standard deviation for a group of numbers based on a sample
     * @param numArray array of numbers
     * @return standard deviation of the values in the array
     */
    public static double calculateSD(double numArray[])
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;
        for(double num : numArray) {
            sum += num;
        }
        double mean = sum/length;
        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/length);
    }
    /**
     * factorial: the product of an integer and all the integers below it e.g. factorial four ( 4! ) is equal to 24.
     * @param n integer
     * @return factorial of n
     */
    public BigInteger factorial(double n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }
    //compute Binomial Probability 
    /**
     *  function to calculate nCr i.e., number of ways to choose r out of n objects
     * @param n
     * @param r
     * @return number of ways to choose r out of n objects
     */ 
    static int nCr(int n, int r) 
    { 
        // Since nCr is same as nC(n-r) 
        // To decrease number of iterations 
        if (r > n / 2) 
            r = n - r; 
      
        int answer = 1; 
        for (int i = 1; i <= r; i++) { 
            answer *= (n - r + i); 
            answer /= i; 
        } 
      
        return answer; 
    } 
    /**
     *  function to calculate binomial r.v. probability 
     * @param n int
     * @param k int
     * @param p float
     * @return a float binomial r.v. probability 
     */
    static float binomialProbability(int n, int k, float p) 
    { 
        return nCr(n, k) * (float)Math.pow(p, k) *  
                        (float)Math.pow(1 - p, n - k); 
    } 
    //COUNT
    /**
     * COUNT
     * @param arrA array of int values
     * @return String indicating the number of occurrences of each string in the array
     */
    public static String RepeatingElementUsingMap(int [] arrA){
        //Will store each character and it's count
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <arrA.length; i++) {
            if(map.containsKey(arrA[i])){
                map.put(arrA[i],map.get(arrA[i])+1);
            }else{
                map.put(arrA[i], 1);
            }
        }
        String s = new String("");
        for (int a :map.keySet())
        {
        	s = s + "number: " + Integer.toString(a) + ", occurances: " + Integer.toString(map.get(a)) + "; ";
        }
        return s;
    }
    /**
     * Count the letters, spaces, numbers and other characters of an input string
     * @param x String
     * @return the number of the letters, spaces, numbers and other characters.
     */
    public static String count(String x){
		char[] ch = x.toCharArray();
		int letter = 0;
		int space = 0;
		int num = 0;
		int other = 0;
		for(int i = 0; i < x.length(); i++){
			if(Character.isLetter(ch[i])){
				letter ++ ;
			}
			else if(Character.isDigit(ch[i])){
				num ++ ;
			}
			else if(Character.isSpaceChar(ch[i])){
				space ++ ;
			}
			else{
				other ++;
			}
		}
		String s = new String("The string is: " + x + ";  letter: " + letter  + ";  space: " + space + ";  number: " + num + ";  other: " + other);
		return s;
			}
    //Fibonacci memoized
    public static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    public static int Fib(int n) {
      if(hm.containsKey(n)) return hm.get(n);
      if(n == 0) return 0;
      if(n == 1) return 1;
      int nthValue = Fib(n - 1) + Fib(n - 2);
      hm.put(n, nthValue);
      return nthValue;
    }
    
	@Override
	public void run() {
		System.out.println("Connected: " + socket);
		try 
		{
			var in = new Scanner(socket.getInputStream());
			var out = new PrintWriter(socket.getOutputStream(), true);
			while (in.hasNextLine()) 
			{
				String[] exp = in.nextLine().toString().split(",");
				String command = exp[0].toLowerCase();
				String values = new String(exp[1]);
				double result = 0;
				// the use passes for example AVERAGE,1 2 3 4 5 6 
				// AVERAGE is exp[0]="AVERAGE" and the values are in exp[1]="1 2 3 4 5 6"
				// we want to find the average of the values in expression 1
				//so we need to take each value: they are exp[1]
				// but we want to convert them into Double values
				String[] valuesarr = exp[1].split("\\s");
				
				 // array of doubles that contains the values in double value
				if (command.equals("average"))
				{double[] arrDouble = ToDouble(valuesarr);	//sum the values in each entry of the array arrDouble and divide them by the size of this array
					for(int i = 0; i<arrDouble.length; i++)
					{
						result = result + arrDouble[i];
					}
					result = result/(arrDouble).length; //send the result to client
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if (command.equals("sum")) 
				{double[] arrDouble = ToDouble(valuesarr);
					for(int i = 0; i<arrDouble.length; i++)
					{
						result = result + arrDouble[i];
					}
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("min"))
				{double[] arrDouble = ToDouble(valuesarr);
					result = arrDouble[0];
					for(int i = 0; i<arrDouble.length; i++)
					{
						
						if(arrDouble[i]<result) 
						{
							result = arrDouble[i];
						}
					}
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("max"))
				{double[] arrDouble = ToDouble(valuesarr);
					result = arrDouble[0];
					for(int i = 0; i<arrDouble.length; i++)
					{
						
						if(arrDouble[i]>result) 
						{
							result = arrDouble[i];
						}
					}
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("abs"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = Math.abs(arrDouble[0]);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("fib"))
				{
					int[] arrInt = ToInt(valuesarr);
				//	result = (double)Fib(arrInt[0]);
					out.println("the result is: " + Fib(arrInt[0]) + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("median"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = median(arrDouble);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				
				else if(command.equals("mode"))
				{
					int[] arrInt = ToInt(valuesarr);
					result = getMode(arrInt);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("product"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = Aproduct(arrDouble);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("sqrt"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = Math.pow(arrDouble[0], 0.5);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("stdev"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = calculateSD(arrDouble);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("count"))
				{
					int[] arrInt = ToInt(valuesarr);
					String ss = new String(RepeatingElementUsingMap(arrInt));
					out.println(ss);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("countall"))
				{String ss = new String(count(values));
					out.println(ss);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				
				else if(command.equals("variance"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = calculateSD(arrDouble)*calculateSD(arrDouble);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				// return the square of a number
				else if(command.equals("square"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = Math.pow(arrDouble[0], 2);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				// return a^b (arrDouble[0])^(arrDouble[1])
				else if(command.equals("power"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					result = Math.pow(arrDouble[0], arrDouble[1]);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				//returns the sin of a number in degrees and radians
				else if(command.equals("sin"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					
					double angle = arrDouble[0];
				    double radian = Math.sin(angle);
				    double degree  = Math.sin( Math.toRadians( angle ) ) ;
			        out.println("in degrees, sin(" + arrDouble[0] + ") = " + degree + " ; in radians,  sin("+ arrDouble[0] 	+ ") = " + radian); 
					//result = radian;
				}
				//returns the cosine of a number in degrees and radians
				else if(command.equals("cos"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					
					double angle = arrDouble[0];
					double radian = Math.cos(angle);
					double degree  = Math.cos( Math.toRadians( angle ) ) ;
					out.println("in degrees, cos(" + arrDouble[0] + ") = " + degree + " ; in radians,  cos("+ arrDouble[0] 	+ ") = " + radian); 
					//result = radian;
				}
				//returns the tangent of a number in degrees and radians
				else if(command.equals("tan"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					
					double angle = arrDouble[0];
					double radian = Math.tan(angle);
					double degree  = Math.tan( Math.toRadians( angle ) ) ;
					out.println("in degrees, tan(" + arrDouble[0] + ") = " + degree + " ; in radians,  tan("+ arrDouble[0] 	+ ") = " + radian); 
					result = radian;
				}
				//returns the arcsine of a number in degrees and radians
				else if(command.equals("asin"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					
					double angle = arrDouble[0];
					double radian = Math.asin(angle);
					double degree  = Math.asin( Math.toRadians( angle ) ) ;
					out.println("in degrees, asin(" + arrDouble[0] + ") = " + degree + " ; in radians,  asin("+ arrDouble[0] 	+ ") = " + radian); 
					result = radian;
				}
				//returns the arc cosine of a number in degrees and radians
				else if(command.equals("acos"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					
					double angle = arrDouble[0];
					double radian = Math.acos(angle);
					double degree  = Math.acos( Math.toRadians( angle ) ) ;
					out.println("in degrees, acos(" + arrDouble[0] + ") = " + degree + " ; in radians,  acos("+ arrDouble[0] 	+ ") = " + radian); 
					result = radian;
				}
				//returns the arc tangent of a number in degrees and radians
				else if(command.equals("atan"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					
					double angle = arrDouble[0];
					double radian = Math.atan(angle);
					double degree  = Math.atan( Math.toRadians( angle ) ) ;
					out.println("in degrees, atan(" + arrDouble[0] + ") = " + degree + " ; in radians,  atan("+ arrDouble[0] 	+ ") = " + radian); 
					result = radian;
					//out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				//factorial: the product of an integer and all the numbers below it e.g.  ( 4! ) is equal to 24. \r\n
				else if(command.equals("factorial"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					out.println(factorial(arrDouble[0]));
				}
				//n(n+1)/2 = 1 + 2 + 3 + 4 + 5 + 6 + 7 + ... + n 
				else if(command.equals("sum1n"))
				{
					double[] arrDouble = ToDouble(valuesarr);
					double d = arrDouble[0] * (arrDouble[0] + 1)/2 ;
					result = d;
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("binomialproba"))
				{
					int[] arrInt = ToInt(valuesarr);
					float f = (float) arrInt[2];
					result = (float) binomialProbability(arrInt[0], arrInt[1],f);
					out.println("the result is: " + result + "; to continue, enter a commmand, in lower of upper case, or exit");
				}
				else if(command.equals("comb"))
				{
					int[] arrInt = ToInt(valuesarr);
					BigInteger a = factorial(arrInt[0]);
					BigInteger b = factorial(arrInt[1]);
					BigInteger c = factorial(arrInt[0] - arrInt[1]);
					 
					out.println(a.divide((b.multiply(c))));
				}
				else if(command.equals("perm"))
				{
					int[] arrInt = ToInt(valuesarr);
					BigInteger a = factorial(arrInt[0]);
					BigInteger c = factorial(arrInt[0] - arrInt[1]);
					
					out.println(a.divide(c));
				}
//				else if(command.equals("TERMINATE"))
//				{
//					socket.close();
//				}
				else if (command.equals("term"))
				{
					break;
				}
				else
				{
					out.println("command not available!");
				}
				//sent the result back sqr root, sin, cos, tang, inv sine, inv cos,series, binomial, factorial the more you do the better, power power series a^n, client gives you n, you give him back the answer
				
			}
			in.close();
			out.close();
			socket.close();
		}
		catch(Exception e) {
			System.out.println("Error: " + socket);
		}
		finally
		{
			try 
			{
				socket.close();
			}
			catch(IOException e) {
				System.out.println("Closed: " + socket);
			}
		}
	}
}
