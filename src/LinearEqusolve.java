/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2cop;

/**
 *
 * @author kunal
 */

public class LinearEqusolve {
 
    public static double[] gaussianEliminatoncaller(double mat1[][],double mat2[][],int r)
    {
        double mat12[][] = new double[r][r+1];
        for (int i = 0; i < r; i++) 
        {
            for (int j = 0; j < r; j++)
            {
                mat12[i][j] = mat1[i][j];
            }
   
        }
        for (int i = 0; i < r; i++) 
        {
            
            {
                mat12[i][r] = mat2[i][0];
            }
   
        }
       return gaussianEliminaton(mat12, r);
    }
    
    public static double[] gaussianEliminaton(double mat[][],int r){
        int singular_flag = forwardElim(mat,r);
        if (singular_flag != -1)
        {
            System.out.println("Singular Matrix");
            if (mat[singular_flag][r] != 0)
                System.out.println("Inconsistent System.");
            else
                System.out.println("May have infinitely many solutions.");
            
        }
        return backSub(mat,r);
    }

   public   static void swapRow(double mat[][], int i, int j,int r){
        for (int k=0; k<=r; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }

     public  static int forwardElim(double mat[][],int r){
        for (int k = 0; k < r; k++){
            int i_max = k;
            int v_max = (int)mat[i_max][k];

            for (int i = k + 1; i < r; i++){
                if (Math.abs(mat[i][k]) > v_max){
                    v_max = (int)mat[i][k];
                    i_max = i;
                }
            }

            if ((mat[k][i_max] == 0)) return k;
 
            if (i_max != k) swapRow(mat, k, i_max,r);
 
            for (int i = k + 1; i < r; i++){
                double f = mat[i][k]/mat[k][k];
                for (int j = k + 1; j <= r; j++){
                    mat[i][j] -= (mat[k][j]*f);
                }
                mat[i][k] = 0;
            }
        }
        return -1;
    }

   public  static double[] backSub(double mat[][],int r)
    {
        double x[] = new double[r];
        for (int i = r-1; i >= 0; i--){
            x[i] = mat[i][r];
            for (int j = i+1; j < r; j++){
                x[i] -= mat[i][j]*x[j];
            }
            x[i] = x[i]/mat[i][i];
        }
 
        //System.out.print("\nSolution for the system:\n");
        //        for (int i = 0; i < r; i++)
//            System.out.println(x[i]);
        return x;
    }
}
