import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class Matrix_2Norm {						// Class for calculating a norm 2 of a '2x2 matrix'
	double P = 0, P_ = 0;						// Max of 100 tries is P and max of 1000000 tries is P'
												// As tries increase norm 2 of matrix is more accurate 
	double[][] A = new double [2][2]; 			// Store matrix as A
	public void scan_matrix (double[][] A) {	// Scan input of user for a 2x2 matrix
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input a 2x2 matrix:");
		System.out.print("A[0][0] = ");A[0][0] = scan.nextDouble();
		System.out.print("A[0][1] = ");A[0][1] = scan.nextDouble();
		System.out.print("A[1][0] = ");A[1][0] = scan.nextDouble();
		System.out.print("A[1][1] = ");A[1][1] = scan.nextDouble();	
	}
	public void print_matrix (double[][] A) { 	// Print the matrix for user
		System.out.println("Matrix A is:");
		for (double[] row : A)
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
	}
	public double norm2 (double[] c) {	// Return norm 2 of vector c
		return Math.sqrt((c[0]*c[0])+ (c[1]*c[1]));
	}
	public double[] multiply (double[][] A,double[] x) { // Multiply Ax then return as c,  c = Ax
		double[] c = new double[2];
		c[0] = A[0][0] * x[0] + A[0][1] * x[1];
		c[1] = A[1][0] * x[0] + A[1][1] * x[1];
		return c;
	}
	public double L_calculator (Matrix_2Norm matrix_2norm) { // Calculate L for a random x,   L = ||Ax||
		Random rand = new Random();//instance of random class
		double a = 0, b=0;		// x = [a,b]
		double[] c = new double[2]; // c = Ax
		double[] x = new double[2];
		double L = 0; // L = ||Ax||
		a=rand.nextDouble();
		a *= 100;
		a %= 2;
		a -= 1;
		b = Math.sqrt(1-(a*a));
		x[0] = a; x[1]=b;
		c = matrix_2norm.multiply(A, x); // c = Ax
		L = matrix_2norm.norm2(c);
		return L;
	}
	public void do_100 (Matrix_2Norm matrix_2norm) { // Find max ||Ax|| for 100 tries call it P
		double L = 0; // L = ||Ax||
		for (int i=0;i<100;i++) {
			L = matrix_2norm.L_calculator(matrix_2norm);
			if (L > matrix_2norm.P) {
				matrix_2norm.P = L;
				matrix_2norm.P_ = L;
			}
		}
	}
	public void do_1000000 (Matrix_2Norm matrix_2norm) { // Find max ||Ax|| for 1000000 tries call it P'
		double L = 0; // L = ||Ax||
		for (int i=0;i<1000000;i++) {
			L = matrix_2norm.L_calculator(matrix_2norm);
			if (L > matrix_2norm.P_) {
				matrix_2norm.P_ = L;
			}
		}
	}
	public static void main(String[] args) {  // main
		Matrix_2Norm matrix_2norm = new Matrix_2Norm();  // Make an object of class Matrix_2norm 
		matrix_2norm.scan_matrix (matrix_2norm.A);		// Scan input of user for a 2x2 matrix
		matrix_2norm.print_matrix(matrix_2norm.A);		// Print the matrix for user
		matrix_2norm.do_100(matrix_2norm);				// Find max ||Ax|| for 100 tries call it P 
		System.out.print("The value of P is : "); System.out.println(matrix_2norm.P); // Print P
		matrix_2norm.do_1000000(matrix_2norm);			// Find max ||Ax|| for 1000000 tries call it P'
		System.out.print("The value of P' is : "); System.out.println(matrix_2norm.P_); // Print P'
	}
}
