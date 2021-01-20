import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class QueryClient {
	public static void main(String[] args) throws Exception {
		try (var socket = new Socket ("localhost", 6000)) 
		{
			System.out.println("(1) type the command(lower or uppercase, it doesn't matter) you want then press Enter. \r\n"
					+ "(2) type the number or sequence of number, depending on your command then press Enter. \r\n"
					);
			System.out.println("The available commands are: \n"+
					"ABS: \r\n" + 
					"    The absolute value of a number \r\n" + 
					"AVERAGE:\r\n "
					+"    The average or arithmetic mean for a group of numbers\r\n" 
					+ "COUNT:\r\n"
					+ "    The number of cell locations in a range that contain a numeric character \r\n" 
					+ "COUNTALL:\r\n"
					+ "    The number of cell locations in a range that contain a numeric character, or letters, spaces, and other characters\r\n" + 
					"MAX: \r\n" + 
					"    The highest numeric value in a group of numbers \r\n" + 
					"MEDIAN: \r\n" + 
					"    The middle number in a group of numbers (half the numbers in the group are higher than\r\n" + 
					"    the median and half the numbers in the group are lower than the median) \r\n" + 
					"MIN: \r\n" + 
					"    The lowest numeric value in a group of numbers \r\n" + 
					"MODE: \r\n" + 
					"    The number that appears most frequently in a group of numbers \r\n" + 
					"PRODUCT: \r\n"
					+"    The result of multiplying all the values in a range of cell locations\r\n" + 
					"SQRT: \r\n" + 
					"    The positive square root of a number \r\n" + 
					"STDEV:\r\n"+"    The standard deviation for a group of numbers based on a sample\r\n" + 
					"SUM: \r\n" + 
					"    The total of all numeric values in a group.\r\n" +
					"SQUARE: \r\n" + 
					"    The square of a number. \r\n" +
					"VARIANCE: \r\n" + 
					"    The variance for a group of numbers based on a sample. \r\n" +
					"POWER: \r\n" + 
					"    The power of the first number to the second number \r\n" + 
					"    Ex: \r\n"+"    POWER \r\n"+"    3 5 \r\n"+"    => returns 3^5. \r\n"+
					"SIN: \r\n" + 
					"    The sine of an angle in degrees and in randians. \r\n"+
					"COS: \r\n" + 
					"    The cosine of an angle in degrees and in randians. \r\n"+
					"TAN: \r\n" + 
					"    The tangent of an angle in degrees and in randians. \r\n"+
					"ASIN: \r\n" + 
					"    The arcsine of an angle in degrees and in randians. \r\n"+
					"ACOS: \r\n" + 
					"    The arc cosine of an angle in degrees and in randians. \r\n"+
					"ATAN: \r\n" + 
					"    The arc tangent of an angle in degrees and in randians. \r\n"+
					"FACTORIAL: \r\n" + 
					"    factorial: the product of an integer and all the numbers below it e.g.  ( 4! ) is equal to 24. \r\n"+
					"SUM1N: \r\n" + 
					"    n(n+1)/2 = 1 + 2 + 3 + 4 + 5 + 6 + 7 + ... + n \r\n"+
					"fib: \r\n" + 
					"    fibonacci of n, memoized \r\n"+
					"Comb: \r\n" + 
					"    combination formula: ex: comb 5 3 is C(5,3) \r\n"+
					"perm: \r\n" + 
					"    permutation formula: ex: perm 5 3 is P(5,3)"); 
			var scanner = new Scanner(System.in);
			var in = new Scanner(socket.getInputStream());
			var out = new PrintWriter(socket.getOutputStream(), true);
			//System.out.println("got here");
			while (scanner.hasNextLine()) { 
				//System.out.println("got here");
				String str = scanner.nextLine();
				//to terminate the program
				if (str.toLowerCase() == "term") {
					break;	
					}
				str = str + ",";
				
					str = str + scanner.nextLine();
					//System.out.println(str);
							//sString cmd = "AVERAGE,1 2 3 4 5 6"; // an example you can pass the server
				out.println(str);
				System.out.println(in.nextLine());
			//	System.out.println(str);
				
				}
			// after terminating the program
			in.close();
			out.close();
			socket.close();
			//System.out.println("ocket closed successfully");
		}
		
	}
}
