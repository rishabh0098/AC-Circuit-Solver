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
public class Complexarithmetic extends LinearEqusolve
{    
    public static double[] addcomplex(double a ,double b ,double c ,double d) 
    {
       double[] ans  = new double[2];
       
       ans[0] = (a+c);
       ans[1] = (b+d);
      //   a +  b i + c +  d i = (a+c)+(b+d)j
      return ans; 
    }
    
    public static double[] minuscomplex(double a ,double b ,double c ,double d) 
    {
       double[] ans  = new double[2];
       
       ans[0] = (a-c);
       ans[1] = (b-d);
      //   a +  b i - (c +  d i) = (a-c)+(b-d)j
      return ans; 
    }
     public static double[] mutliplycomplex(double a ,double b ,double c ,double d) 
    {
       double[] ans  = new double[2];
       double sqrtval = Math.sqrt(c*c + d*d);
       ans[0] = (a*c - b*d)/sqrtval ;
       ans[1] = (b*c + a*d)/sqrtval ;
      //   a +  b i / c +  d i = (ac + bd ) + (bc - ad ) / sqrt(c*c + d*d)
      return ans; 
    }
    
    public static double[] dividecomplex(double a ,double b ,double c ,double d) 
    {
       double[] ans  = new double[2];
       double sqrtval = Math.sqrt(c*c + d*d);
       ans[0] = (a*c + b*d)/sqrtval ;
       ans[1] = (b*c - a*d)/sqrtval ;
      //   a +  b i / c +  d i = (ac + bd ) + (bc - ad ) / sqrt(c*c + d*d)
      return ans; 
    }
    
}
