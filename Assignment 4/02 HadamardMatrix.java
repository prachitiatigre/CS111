/*************************************************************************
 *  Compilation:  javac HadamardMatrix.java
 *  Execution:    java HadamardMatrix 2
 *
 *  @author: Prachiti Atigre
 *
 * The program HadamardMatrix prints H(n)
 *
 *  % java HadamardMatrix 2
 *  T T
 *  T F
 *
 *************************************************************************/

public class HadamardMatrix {
    public static void main(String[] args) {
	// WRITE YOUR CODE HERE
    int num = Integer.parseInt(args[0]);

        boolean matrix[][]  = new boolean[num][num];
        matrix[0][0] = true;

        for (int i = 1; i < num; i += i) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < i; k++) {
                    matrix[j+i][k]   =  matrix[j][k];
                    matrix[j][k+i]   =  matrix[j][k];
                    matrix[j+i][k+i] = !matrix[j][k];
                }
            }
        }

        for (int j = 0; j < num; j++) {
            for (int k = 0; k < num; k++) {
                if (matrix[j][k]) 
                    System.out.print("T ");
                else                
                    System.out.print("F ");
                }    
            }
            System.out.println();
        }
    }
}
