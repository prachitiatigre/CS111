/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *  @author: Prachiti Atigre
 *
 *************************************************************************/

public class RecursiveAppend {
    public static String appendNTimes (String original, int n) {
    	if(n > 0)
    		return original + appendNTimes(original, n-1);
    	return original;
    }

    public static void main (String[] args) {
    	System.out.println(appendNTimes("cat" , 0));
    	System.out.println(appendNTimes("cat" , 1));
    	System.out.println(appendNTimes("cat" , 2));
    }
}
