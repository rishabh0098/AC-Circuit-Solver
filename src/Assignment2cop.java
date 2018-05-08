/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2cop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment2cop extends DrawComponentssvg  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException ,FileNotFoundException
    {   
        int[] netarray = new int[30];
        for (int i = 0; i < 30; i++) 
        {
            netarray[i] = 50+i*150;
        }
        //V1 Net1 0 SINE ( 0.0 1.0 10Khz 0.0S 0.0 )
        String res_regex = "(\\s)*[Rr][0-9](\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([0-9]+)[kK]?[Mm]?[Nn]?[Pp]?(\\s)*";
        String cap_regex = "(\\s)*[Cc][0-9](\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([0-9]+)[kK]?[Mm]?[Nn]?[Pp]?[fF](\\s)*";
        String ind_regex = "(\\s)*[Ll][0-9](\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([0-9]+)[kK]?[Mm]?[Nn]?[Pp]?[hH](\\s)*";
        String volt_regex = "(\\s)*[Vv][0-9](\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([Ss][Ii][Nn][Ee])(\\s)*[(](\\s)?([0-9]+)(\\.[0-9]+)?(\\s)+([0-9]+)(\\.[0-9]+)?(\\s)+([0-9]+)(\\.[0-9]+)?[kK]?[Mm]?[Nn]?[Pp]?[Hh][Zz](\\s)+([0-9]+)(\\.[0-9]+)?[Ss](\\s)+([0-9]+)(\\.[0-9]+)?(\\s)+[)](\\s)*";
        String curr_regex = "(\\s)*[Ii][0-9](\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([Nn][eE][tT])?[0-9]+(\\s)+([Ss][Ii][Nn][Ee])(\\s)*[(](\\s)?([0-9]+)(\\.[0-9]+)?(\\s)+([0-9]+)(\\.[0-9]+)?(\\s)+([0-9]+)(\\.[0-9]+)?[kK]?[Mm]?[Nn]?[Pp]?[Hh][Zz](\\s)+([0-9]+)(\\.[0-9]+)?[Ss](\\s)+([0-9]+)(\\.[0-9]+)?(\\s)+[)](\\s)*";
        FileWriter fw = new FileWriter("top.html");
        BufferedWriter bw = new BufferedWriter(fw);
        File text = new File("top.cir");
        Scanner scan = new Scanner(text);
        Pattern empty_pat = Pattern.compile(res_regex);
        Pattern res_pat   = Pattern.compile(res_regex);
        Pattern cap_pat   = Pattern.compile(cap_regex);
        Pattern ind_pat   = Pattern.compile(ind_regex);
        Pattern volt_pat  = Pattern.compile(volt_regex);
        Pattern curr_pat  = Pattern.compile(curr_regex);
       String s1 =  "<html><head>\n" +
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
"</head><body><svg xmlns=\"http://www.w3.org/2000/svg\" width=100% height=100%>\n <script type=\"text/JavaScript\"> document.body.style.zoom =\"30%\";</script> \n <script type=\"text/JavaScript\">\n  var zoomlevel = 60 ; \n function zoomoutmethod() {                                   <!-- display4 method  to   zoomout -->\n" +
"	zoomlevel = zoomlevel - 10 ; \n" +
"        document.body.style.zoom = zoomlevel+\"%\";\n" +
"        }\n" +
"	function zoominmethod() {                                  <!-- zoominmethod called to   zoomin -->\n" +
"	zoomlevel = zoomlevel + 10 ;\n" +
"        document.body.style.zoom = zoomlevel+\"%\";\n" +
"        }</script>\n <text x=\"200\" y=\"20\" font-size=\"35\" fill =\"black\" onClick=\"zoomoutmethod()\" >  <!-- zoomoutmethod called to   zoomout -->\n" +
"  zout - </text>\n" +
"<text x=\"290\" y=\"20\" font-size=\"35\" fill =\"black\" onClick=\"zoominmethod()\" >  <!-- zoomin method called to   zoomin -->\n" +
"  zin + </text> \n <text id=\"data\" x=\"390\" y=\"20\" font-size=\"15\" fill =\"black\" onClick=\"Clicked()\" display=\"none\">data</text>\n <text  x=\"400\" y=\"22\" font-size=\"15\" fill =\"black\" >_________________________________________________________________________________________________________</text>";
        bw.write(s1+"\n");
        int lineno = 0;
        int firstline[] = new int[30];
        int basemax = 1 ;
        double[][] GR = new double[100][100];
        double[][] GC = new double[100][100];
        double[][] GI = new double[100][100];
        int[][] componentconnection = new int[4][100];
        double[] compnentconnectionvalueimpedance = new double[100];
        double[] compnentconnectionvaluefrequency = new double[100];
        int componentcount = 0;
        double[]   voltagefrequencyvalue = new double[100];
        int[]      voltagefrequencyno = new int[100];  // no. of voltage source with same frequency of same type
        int        vfrequencyno = -1;    // no. of different frequency
        int noofvoltagesource = 0 ;
        int[][][] B = new int[100][100][100];
        double[][] C = new double[100][100];
        double[]   currentfrequencyvalue = new double[100];
        int        cfrequencyno = -1;
        double[][] Cvoltage = new double[100][100];
        int n1=0,n2=0;
        int nmax =0;
        while(scan.hasNext())
        {   
            lineno++;
            String testcase=scan.nextLine();
            Matcher resm   = res_pat.matcher(testcase);
            Matcher emptym = empty_pat.matcher(testcase);
            Matcher capm   = cap_pat.matcher(testcase);
            Matcher indm   = ind_pat.matcher(testcase);
            Matcher voltm  = volt_pat.matcher(testcase);
            Matcher currm  = curr_pat.matcher(testcase);
            
            testcase= testcase.trim();
            if(resm.matches()){
                String comp[] = testcase.split("\\s");
                String net1   = comp[1];
                String net2   = comp[2];
                
                if(net1.length()>1){
                    n1 = Integer.parseInt(net1.substring(3));
                }
                else{
                    n1 = -1;
                }
                if(net2.length()>1){
                    n2 = Integer.parseInt(net2.substring(3));
                }
                else{
                    n2 = -1;
                }
                 double valueofResistance =  analyseunit(comp[3]);
                componentconnection[0][componentcount] = n1 ;
                componentconnection[1][componentcount] = n2 ;
                componentconnection[2][componentcount] = 0 ;     // resistance code 0
                compnentconnectionvalueimpedance[componentcount] = valueofResistance; 
                
               
                if(n1>=0 )
                GR[n1][n1] = GR[n1][n1] + (1.0/valueofResistance);
                if((n2>=0))
                GR[n2][n2] = GR[n2][n2] + (1.0/valueofResistance);
                if(n1>=0 && (n2>=0))
                { GR[n1][n2] = GR[n1][n2] - (1.0/valueofResistance);
                GR[n2][n1] = GR[n1][n2];
                }
                if(n1<n2)
                { 
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(resistor(80, netarray[n1+1], 80, netarray[n2+1],0,100, comp[0], comp[3],componentcount));  
                        firstline[n1+1]=1;
                    }
                    else
                    { 
                        bw.write(resistor(80, netarray[n1+1], 80, netarray[n2+1],basemax,100, comp[0], comp[3],componentcount));
                        basemax++;
                    }
                }
                else
                {
                    if(Math.abs(n1-n2)==1 && firstline[n2+1]==0)
                    {   
                        bw.write(resistor(80, netarray[n1+1], 80, netarray[n2+1],0,100, comp[0], comp[3],componentcount));  
                        firstline[n2+1]=1;
                    }
                    else
                    {   
                        bw.write(resistor(80, netarray[n1+1], 80, netarray[n2+1],basemax,100, comp[0], comp[3],componentcount));
                        basemax++;
                    }
                } 
                  
             componentcount++;
            }
            else if(capm.matches()){
                String comp[] = testcase.split("\\s");
                String net1   = comp[1];
                String net2   = comp[2];
                
                if(net1.length()>1){
                      n1 = Integer.parseInt(net1.substring(3));
                }
                else{
                      n1 = -1;
                }
                if(net2.length()>1){
                      n2 = Integer.parseInt(net2.substring(3));
                }
                else{
                      n2 = -1;
                }
                double valueofCapacitance =  analyseunit(comp[3]);
                componentconnection[0][componentcount] = n1 ;
                componentconnection[1][componentcount] = n2 ;
                componentconnection[2][componentcount] = 1 ;     // capacitance code 1  
                compnentconnectionvalueimpedance[componentcount] = valueofCapacitance;
                
                if(n1>=0)
                GC[n1][n1] = GC[n1][n1] + (valueofCapacitance);
                if((n2>=0))
                GC[n2][n2] = GC[n2][n2] + (valueofCapacitance);
                if(n1>=0 && (n2>=0))
                { GC[n1][n2] = GC[n1][n2] - (valueofCapacitance);
                GC[n2][n1] = GC[n1][n2];
                }
                if(n1<n2)
                {     
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(capacitor(80, netarray[n1+1], 80, netarray[n2+1],0,100, comp[0], comp[3],componentcount));  
                        firstline[n1+1]=1;
                    }  
                    else
                    { 
                        bw.write(capacitor(80, netarray[n1+1], 80, netarray[n2+1],basemax,100, comp[0], comp[3],componentcount));
                        basemax++;
                    }
                }
                else
                {
                    if(Math.abs(n1-n2)==1 && firstline[n2+1]==0)
                    {   
                        bw.write(capacitor(80, netarray[n1+1], 80, netarray[n2+1],0,100, comp[0], comp[3],componentcount));  
                        firstline[n2+1]=1;
                    }  
                    else
                    { 
                        bw.write(capacitor(80, netarray[n1+1], 80, netarray[n2+1],basemax,100, comp[0], comp[3],componentcount));
                        basemax++;
                    }
                }
                 componentcount++;   
            }
            else if(indm.matches()){
                String comp[] = testcase.split("\\s");
                String net1   = comp[1];
                String net2   = comp[2];
                
                if(net1.length()>1){
                       n1 = Integer.parseInt(net1.substring(3));
                }
                else{
                       n1 = -1;
                }
                if(net2.length()>1){
                       n2 = Integer.parseInt(net2.substring(3));
                }
                else{
                       n2 = -1;
                }
                double valueofinductance =  analyseunit(comp[3]);
                componentconnection[0][componentcount] = n1 ;
                componentconnection[1][componentcount] = n2 ;
                componentconnection[2][componentcount] = 2 ;     // inductance code 2  
                compnentconnectionvalueimpedance[componentcount] = valueofinductance;
                
                
                
                if(n1>=0)
                GI[n1][n1] = GI[n1][n1] + (1.0/valueofinductance);
                if(n2>=0)
                GI[n2][n2] = GI[n2][n2] + (1.0/valueofinductance);
                if(n1>=0 && (n2>=0))
                { GI[n1][n2] = GI[n1][n2] - (1.0/valueofinductance);
                GI[n2][n1] = GI[n1][n2];
                }
                if(n1<n2)
                {
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(inductor(80, netarray[n1+1], 80, netarray[n2+1],0,100, comp[0], comp[3],componentcount));  
                        firstline[n1+1]=1;
                    }  
                    else
                    { 
                        bw.write(inductor(80, netarray[n1+1], 80, netarray[n2+1],basemax,100, comp[0], comp[3],componentcount));
                        basemax++;
                    }
                }
                else
                {
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(inductor(80, netarray[n1+1], 80, netarray[n2+1],0,100, comp[0], comp[3],componentcount));  
                        firstline[n2+1]=1;
                    }  
                    else
                    { 
                        bw.write(inductor(80, netarray[n1+1], 80, netarray[n2+1],basemax,100, comp[0], comp[3],componentcount));
                        basemax++;
                    }
                }
                componentcount++;
            }
            else if(voltm.matches()){
                String comp[] = testcase.split("\\s");
                String net1   = comp[1];
                String net2   = comp[2];
               double frequencyofvolt = analyseunit(comp[7]);
               double amplitude = Double.parseDouble(comp[6]);
                String namevalue = comp[3]+" "+comp[4]+comp[5]+" "+comp[6]+" "+comp[7]+" "+comp[8]+" "+comp[9]+comp[10];
                
                if(net1.length()>1){
                       n1 = Integer.parseInt(net1.substring(3));
                }
                else{
                       n1 = -1;
                }
                if(net2.length()>1){
                       n2 = Integer.parseInt(net2.substring(3));
                }
                else{
                       n2 = -1;
                }
                
                componentconnection[0][componentcount] = n1 ;
                componentconnection[1][componentcount] = n2 ;
                componentconnection[2][componentcount] = 3 ;     // voltage code 3  
                compnentconnectionvalueimpedance[componentcount] = amplitude;
                compnentconnectionvaluefrequency[componentcount] = frequencyofvolt ;
                
                
                
                int freqindex = 0;
                for (freqindex= 0; freqindex < 10; freqindex++)
                {
                 if(voltagefrequencyvalue[freqindex]==frequencyofvolt)
                 { break ;
                 }
                }
                 int indexxx = 0;
                if(freqindex==10)
                {   
                   vfrequencyno++;
                   voltagefrequencyno[vfrequencyno]++;
                   voltagefrequencyvalue[vfrequencyno]=frequencyofvolt;
                   indexxx = voltagefrequencyno[vfrequencyno];
                   if(n1>=0)
                      B[vfrequencyno][n1][indexxx-1]=1;
                    
                   if(n2>=0)
                      B[vfrequencyno][n2][indexxx-1]=-1;
                
                   Cvoltage[vfrequencyno][0] = amplitude;
                }
                else
                { voltagefrequencyno[freqindex]++;
                  indexxx = voltagefrequencyno[freqindex];
                  if(n1>=0)
                   B[freqindex][n1][indexxx-1]=1;
                  if(n2>=0)
                   B[freqindex][n2][indexxx-1]=-1;
                 
                  Cvoltage[freqindex][indexxx-1] = amplitude;
                }
                
                
                noofvoltagesource++;
                if(n1<n2)
                {
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(voltagesource(80, netarray[n1+1], 80, netarray[n2+1],0,100, namevalue,componentcount));  
                        firstline[n1+1]=1;
                    }  
                    else
                    { 
                        bw.write(voltagesource(80, netarray[n1+1], 80, netarray[n2+1],basemax,100,namevalue,componentcount));
                        basemax++;
                    }
                }
                else
                {
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(voltagesource(80, netarray[n1+1], 80, netarray[n2+1],0,100,namevalue,componentcount));  
                        firstline[n2+1]=1;
                    }  
                    else
                    { 
                        bw.write(voltagesource(80, netarray[n1+1], 80, netarray[n2+1],basemax,100,namevalue,componentcount));
                        basemax++;
                    }
                }
                componentcount++;
            }
            else if(currm.matches()){
                String comp[] = testcase.split("\\s");
                String net1 = comp[1];
                String net2 = comp[2];
                double frequencyofcurr = analyseunit(comp[7]);
                double amplitude = Double.parseDouble(comp[6]); 
                String namevalue = comp[3]+" "+comp[4]+comp[5]+" "+comp[6]+" "+comp[7]+" "+comp[8]+" "+comp[9]+comp[10];
               
                if(net1.length()>1){
                       n1 = Integer.parseInt(net1.substring(3));
                }
                else{
                       n1 = -1;
                }
                if(net2.length()>1){
                       n2 = Integer.parseInt(net2.substring(3));
                }
                else{
                       n2 = -1;
                }
                
                componentconnection[0][componentcount] = n1 ;
                componentconnection[1][componentcount] = n2 ;
                componentconnection[2][componentcount] = 4 ;
                compnentconnectionvalueimpedance[componentcount] = amplitude;  // current code 4
                compnentconnectionvaluefrequency[componentcount] = frequencyofcurr ;
                
                
                
                int freqindex =0 ;
                for (freqindex= 0; freqindex < 10; freqindex++)
                {
                 if(currentfrequencyvalue[freqindex]==frequencyofcurr)
                 { break ;
                 }
                }
               
                if(freqindex==10)
                {   
                   cfrequencyno++;
                   currentfrequencyvalue[cfrequencyno]=frequencyofcurr;
                 
                   if(n1>=0)
                C[cfrequencyno][n1]=amplitude;
                if(n2>=0)
                C[cfrequencyno][n2]=-amplitude;
                }
                else
                { 
                  
                  if(n1>=0)
                C[freqindex][n1]= C[freqindex][n1] + amplitude;
                if(n2>=0)
                C[freqindex][n2]= C[freqindex][n2] - amplitude;
                }
                
                
                if(n1<n2)
                {
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(currentsource(80, netarray[n1+1], 80, netarray[n2+1],0,100, namevalue,componentcount));  
                        firstline[n1+1]=1;
                    }  
                    else
                    { 
                        bw.write(currentsource(80, netarray[n1+1], 80, netarray[n2+1],basemax,100,namevalue,componentcount));
                        basemax++;
                    }
                }
                else
                {
                    if(Math.abs(n1-n2)==1 && firstline[n1+1]==0)
                    {   
                        bw.write(currentsource(80, netarray[n1+1], 80, netarray[n2+1],0,100,namevalue,componentcount));  
                        firstline[n2+1]=1;
                    }  
                    else
                    { 
                        bw.write(currentsource(80, netarray[n1+1], 80, netarray[n2+1],basemax,100,namevalue,componentcount));
                        basemax++;
                    }
                }
                componentcount++;
            }
            else if(testcase.isEmpty()){
                
            }
            else
            {
                System.out.println("error found in line  "+lineno);
            }
        
            
            if(n1>nmax && n1>n2)
                { nmax = n1 ;
                }
            if(n2>nmax && n2>n1)
                { nmax = n2 ;
                }
            
        }
        nmax++;
        
        double[][] GRnew = new double[nmax][nmax];
        double[][] GCnew = new double[nmax][nmax];
        double[][] GInew = new double[nmax][nmax];
        
        
        double[]   voltagefrequencyvaluenew = new double[vfrequencyno+1];
        int[]      voltagefrequencynonew = new int[vfrequencyno+1];
        
        
        for (int i = 0; i < cfrequencyno+1; i++) 
        {
            System.out.print(currentfrequencyvalue[i]+"  ");
        }
        System.out.println("current frequency value ----------");
        System.out.println("");
//        for (int i = 0; i < vfrequencyno+1; i++) 
//        {   for (int j = 0; j < 10; j++)
//        {
//            System.out.print(Cvoltage[i][j]+"  ");
//        }
//            System.out.println("");
//        }
        System.out.println(nmax+"-------nmax");
        for (int i = 0; i < vfrequencyno+1; i++) 
        {
           voltagefrequencyvaluenew[i]= voltagefrequencyvalue[i];
        }
        
        for (int i = 0; i < vfrequencyno+1; i++) 
        {
           voltagefrequencynonew[i]= voltagefrequencyno[i];
        }
        
        
        
        for (int i = 0; i < nmax; i++)
        {  
            for (int j = 0; j < nmax; j++) 
            {
                GRnew[i][j]=GR[i][j];
            }
        }
        
        for (int i = 0; i < nmax; i++)
        {  
            for (int j = 0; j < nmax; j++) 
            {
               GCnew[i][j]= GC[i][j];
            }
        }
        
        for (int i = 0; i < nmax; i++)
        {  
            for (int j = 0; j < nmax; j++) 
            {
               GInew[i][j]= GI[i][j];
            }
        }
        
        
        for (int l= 0; l < vfrequencyno+1; l++) 
        {
            for (int i = 0; i < nmax; i++)
        {  
            for (int j = 0; j < voltagefrequencyno[l]; j++) 
            {
                System.out.print(B[l][i][j]+"  ");
            }
            System.out.println("");
   
        }
            System.out.println("");
           
        }
        System.out.println("----------- B[][][]  array  ");
        System.out.println("");
        double[] comb_of_diff_freq = new double[vfrequencyno+cfrequencyno+2];
        for (int i = 0; i < vfrequencyno+1; i++) 
        {
         comb_of_diff_freq[i] = voltagefrequencyvaluenew[i];   
        }
        
        int k = vfrequencyno+1 ;
        for (int i = 0; i < cfrequencyno+1; i++) 
        {   int flag =0;
            for (int j = 0; j < vfrequencyno+1; j++)
            {
             if(currentfrequencyvalue[i]==voltagefrequencyvaluenew[j])
             {
              flag=1;
             }
            }
            if(flag!=1)
            {  comb_of_diff_freq[k] = currentfrequencyvalue[i] ;
               k++;
            }
        }
        
        for (int i = 0; i < k; i++) {
            System.out.print(comb_of_diff_freq[i]+"  ");
        }
        System.out.println("--------------- all frequency value  ");
        System.out.println("");
        double[][] Z ,A;double[] variable;
        double[][][] voltcurracrosscomprealcomplex = new double[k][4][componentcount]; ;
        double angularfrequency ;
        //-------------------------------------------------------
        for (int l= 0; l < vfrequencyno+1; l++) 
        { 
            A = new double[2*(nmax+voltagefrequencyno[l])][2*(nmax+voltagefrequencyno[l])];
            variable = new double[2*(nmax+voltagefrequencyno[l])];
            angularfrequency = voltagefrequencyvalue[l]*2*3.14;
            for (int i = 0; i < 2*nmax; i++)
        {  
            for (int j = 0; j < 2*nmax; j++) 
            {
                A[i][j] = GRnew[i/2][(j/2)];
                A[i][j+1] = -(GCnew[i/2][j/2]*angularfrequency+GInew[i/2][j/2]/angularfrequency);
                j++;
            }
            for (int j = 2*nmax; j < 2*(nmax+voltagefrequencyno[l]) ; j++) 
            {
                A[i][j] = B[l][i/2][(j-(2*nmax))/2];
                A[i+1][j] = A[i][j];
                A[i][j+1] = -A[i][j];
                A[i+1][j+1] = A[i][j];
                j++;
            }
            i++;
            for (int j = 0; j < 2*nmax; j++) 
            {
                A[i][j+1] = GRnew[i/2][j/2];
                A[i][j] = (GCnew[i/2][j/2]*angularfrequency+GInew[i/2][j/2]/angularfrequency);
                j++;
            }
   
        }
         
            // creating lower half traingle of the matrices
            for (int i = 0 ; i < 2*(nmax+voltagefrequencyno[l]); i++)
            {
                for (int j = 2*nmax; j < 2*(nmax+voltagefrequencyno[l]); j++) 
                {
                   A[j][i] = A[i][j]; 
                }
            }
           
            int currindex = -1 ;
            for (int i = 0; i < cfrequencyno+1; i++) 
            {
                if(voltagefrequencyvaluenew[l]==currentfrequencyvalue[i])
                { 
                    currindex = i ;
                    break ;
                }
            }
            
            
            Z = new double[2*(nmax+voltagefrequencyno[l])][1] ;  
            if(currindex!=-1)
            {
                for (int i = 0 ; i < 2*(nmax); i++)
            {
               Z[i][0] = C[currindex][i/2];
               Z[i+1][0] = C[currindex][i/2];
               i++;
            }
            }
            else
            {
               for (int i = 0 ; i < 2*(nmax); i++)
            {
               Z[i][0] = 0;
            }
            }
         
            for (int i = 2*nmax ; i < 2*(nmax+voltagefrequencyno[l]) ; i++)
            {
               Z[i][0] = Cvoltage[l][(i-2*nmax)/2];
               Z[i+1][0] = Cvoltage[l][(i-2*nmax)/2];
               i++;
            }
            for (int j = 0; j < 2*(nmax+voltagefrequencyno[l]); j++) 
            {
                System.out.println(Z[j][0]+"  ");
            }
            System.out.println("--------------- Z array  when voltage source for that frequency exists ");
            System.out.println("");
          
            // printing A ;
            for (int i = 0; i < 2*(nmax+voltagefrequencyno[l]); i++)
            {  
              for (int j = 0; j < 2*(nmax+voltagefrequencyno[l]); j++) 
              {
                System.out.print(A[i][j]+"     ");
              }
            System.out.println("");
   
            }
            System.out.println("");
            System.out.println("");
        
            System.out.println("----------------- Array A when voltage source for that frequency exists");
        System.out.println("");
            // variable array contains voltage of each  node and current through each voltage source 
            
            
            variable = LinearEqusolve.gaussianEliminatoncaller(A,Z,2*(nmax+voltagefrequencyno[l]));
            
             // ------ data 
            int voltindexno = 0 ;
            for (int i = 0; i < componentcount; i++) 
            {   
                int n11 = componentconnection[0][i];
                int n21 = componentconnection[1][i];
                if(componentconnection[2][i]==0)   // ------ data for resistor
                {
                    if(n11>=0 && n21>=0)
                    {
                     voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                     voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                     
                    }
                    else
                    {  if(n11>=0)
                    { 
                         voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                    } 
                       else
                    { 
                         voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                    }    
                    }
                    voltcurracrosscomprealcomplex[l][2][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,compnentconnectionvalueimpedance[i],0)[0];
                     voltcurracrosscomprealcomplex[l][3][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,compnentconnectionvalueimpedance[i],0)[1];
                }
                else
                {
                    if(componentconnection[2][i]==1)   // ------ data for capacitor
                {
                    if(n11>=0 && n21>=0)
                    {
                     voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                     voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                   //  voltcurracrosscomprealcomplex[l][2][i] 
                     
                    }
                    else
                    {  if(n11>=0)
                    { 
                         voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                    } 
                       else
                    { 
                         voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                    }    
                    }
                    voltcurracrosscomprealcomplex[l][2][i] = mutliplycomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[0];
                     voltcurracrosscomprealcomplex[l][3][i] = mutliplycomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[1];
                
                } 
                else
                {
                       if(componentconnection[2][i]==2)   // ------ data for inductor
                     {
                       if(n11>=0 && n21>=0)
                       {
                     voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                     voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                   //  voltcurracrosscomprealcomplex[l][2][i] 
                     
                        }
                       else
                        {   if(n11>=0)
                          { 
                           voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                           voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                          } 
                         else
                        { 
                         voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                        }    
                        }
                       
                     voltcurracrosscomprealcomplex[l][2][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[0];
                     voltcurracrosscomprealcomplex[l][3][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[1];
                     }
                       else
                       {
                            if(componentconnection[2][i]==3)   // ------ data for voltage
                            {
                                 if(compnentconnectionvaluefrequency[i]==voltagefrequencyvaluenew[l])
                                 {
                                  voltcurracrosscomprealcomplex[l][0][i] = compnentconnectionvalueimpedance[i];  //amplitude of volt
                                  voltcurracrosscomprealcomplex[l][1][i] = 0;
                                  voltcurracrosscomprealcomplex[l][2][i] = variable[2*nmax+2*voltindexno];
                                  voltcurracrosscomprealcomplex[l][3][i] = variable[2*nmax+2*voltindexno+1];
                                  voltindexno++ ;
                                 }
                            }
                            else
                            {
                                    if(componentconnection[2][i]==4 && compnentconnectionvaluefrequency[i]==voltagefrequencyvaluenew[l])   // ------ data for current source
                                    {
                                    if(n11>=0 && n21>=0)
                                    {
                                       voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                                       voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                                      //  voltcurracrosscomprealcomplex[l][2][i] 
                     
                                    }
                                    else
                                    {   if(n11>=0)
                                       { 
                                        voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                                        voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                                        } 
                                         else
                                        { 
                                        voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                                        voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                                        }    
                                    }
                                    voltcurracrosscomprealcomplex[l][2][i] =  compnentconnectionvalueimpedance[i]  ;  // amplitude of current source
                                    voltcurracrosscomprealcomplex[l][3][i] =  0 ;
                                    
                                    }
                            }
                       }
                           
                }
                }
            } //---
            System.out.println("start  of voltage current source ---------------------------------"+ comb_of_diff_freq[l] + " frequency ");
            for (int j = 0; j < componentcount; j++) 
                {   
                    System.out.print(componentconnection[2][j]+"     ");   
                }
            System.out.println("");
            
            
        }
            
            
            
            
        
        
        
        //-------------------------------------------------------------
        for (int l = vfrequencyno+1; l < k ; l++)
        {
           Z = new double[2*(nmax)][1] ;
           int currindex = -1 ;
            for (int i = 0; i < cfrequencyno+1; i++) 
            {
                if(comb_of_diff_freq[l]==currentfrequencyvalue[i])
                { 
                    currindex = i ;
                    break ;
                }
            }
            
           
            for (int j = 0; j < 2*nmax; j++) 
            {
              Z[j][0] = C[currindex][j/2];
              Z[j+1][0] = C[currindex][j/2];
              j++;
            }
            A = new double[2*(nmax)][2*(nmax)];
            
          angularfrequency = comb_of_diff_freq[l]*2*3.14;
            for (int i = 0; i < 2*nmax; i++)
        {  
            for (int j = 0; j < 2*nmax; j++) 
            {
                A[i][j] = GRnew[i/2][(j/2)];
                A[i][j+1] = -(GCnew[i/2][j/2]*angularfrequency+GInew[i/2][j/2]/angularfrequency);
                j++;
            }
            i++;
            for (int j = 0; j < 2*nmax; j++) 
            {
                A[i][j+1] = GRnew[i/2][j/2];
                A[i][j] = (GCnew[i/2][j/2]*angularfrequency+GInew[i/2][j/2]/angularfrequency);
                j++;
            }
        }
            for (int i = 0; i < 2*nmax; i++)
        {  
            for (int j = 0; j < 2*nmax; j++) 
            {
                System.out.print(A[i][j]+" ");
            }
            System.out.println("");
        }
            System.out.println("----------------- Array A when voltage source for that frequency doesnt exists");
            System.out.println("");
        for (int i = 0; i < 2*nmax; i++)
        {      
            System.out.println(Z[i][0]+"  ");
        }
            System.out.println("----------------- Array Z when voltage source for that frequency doesnt exists");
            
            
           // variable array contains voltage of each  node and current through each voltage source 
            System.out.println(k+" k ---- "+vfrequencyno+"  vfreq no .   ");
            
            variable = new double[2*(nmax)];
            variable = LinearEqusolve.gaussianEliminatoncaller(A,Z,2*(nmax));
            for (int i = 0; i < componentcount; i++) 
            {   
                int n11 = componentconnection[0][i];
                int n21 = componentconnection[1][i];
                if(componentconnection[2][i]==0)   // ------ data for resistor
                {   
                    if(n11>=0 && n21>=0)
                    {
                     voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                     voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                     
                    }
                    else
                    {  if(n11>=0)
                    { 
                         voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                    } 
                       else
                    {   
                         voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                    }    
                    }
                    voltcurracrosscomprealcomplex[l][2][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,compnentconnectionvalueimpedance[i],0)[0];
                    voltcurracrosscomprealcomplex[l][3][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,compnentconnectionvalueimpedance[i],0)[1];
                }
                else
                {
                    if(componentconnection[2][i]==1)   // ------ data for capacitor
                {
                    if(n11>=0 && n21>=0)
                    {
                     voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                     voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                   //  voltcurracrosscomprealcomplex[l][2][i] 
                     
                    }
                    else
                    {  if(n11>=0)
                    { 
                         voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                    } 
                       else
                    { 
                         voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                    }    
                    }
                    voltcurracrosscomprealcomplex[l][2][i] = mutliplycomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[0];
                     voltcurracrosscomprealcomplex[l][3][i] = mutliplycomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[1];
                
                } 
                else
                {
                       if(componentconnection[2][i]==2)   // ------ data for inductor
                     {
                       if(n11>=0 && n21>=0)
                       {
                     voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                     voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                   //  voltcurracrosscomprealcomplex[l][2][i] 
                     
                        }
                       else
                        {   if(n11>=0)
                          { 
                           voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                           voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                          } 
                         else
                        { 
                         voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                         voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                        }    
                        }
                       
                     voltcurracrosscomprealcomplex[l][2][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[0];
                     voltcurracrosscomprealcomplex[l][3][i] = dividecomplex(voltcurracrosscomprealcomplex[l][0][i],voltcurracrosscomprealcomplex[l][1][i]
                                                                          ,0,compnentconnectionvalueimpedance[i]*angularfrequency)[1];
                     }
                       else
                       {
                           
                            {
                                    if(componentconnection[2][i]==4 && compnentconnectionvaluefrequency[i]==comb_of_diff_freq[l])   // ------ data for current source
                                    {
                                    if(n11>=0 && n21>=0)
                                    {
                                       voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11] -  variable[2*n21] ;
                                       voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] -  variable[2*n21+1] ;
                                      //  voltcurracrosscomprealcomplex[l][2][i] 
                     
                                    }
                                    else
                                    {   if(n11>=0)
                                       { 
                                        voltcurracrosscomprealcomplex[l][0][i] =  variable[2*n11]  ;
                                        voltcurracrosscomprealcomplex[l][1][i] =  variable[2*n11+1] ;
                                        } 
                                         else
                                        { 
                                        voltcurracrosscomprealcomplex[l][0][i] =  -variable[2*n21]  ;
                                        voltcurracrosscomprealcomplex[l][1][i] =  -variable[2*n21+1] ;
                                        }    
                                    }
                                    voltcurracrosscomprealcomplex[l][2][i] =  compnentconnectionvalueimpedance[i]  ;  // amplitude of current source
                                    voltcurracrosscomprealcomplex[l][3][i] =  0 ;
                                    
                                    }
                            }
                       }
                           
                }
                }
            } //---
           
            //----
            System.out.println("");
            System.out.println("start  of only current source ---------------------------------"+ comb_of_diff_freq[l] + " frquency ");
            for (int j = 0; j < componentcount; j++) 
                {   
                    System.out.print(componentconnection[2][j]+"     ");   
                }
            System.out.println("");
            
        }
        for (int l = 0; l < k ; l++) {
        
        for (int i = 0; i < 4; i++)
            {    
                for (int j = 0; j < componentcount; j++) 
                {   
                    System.out.print(voltcurracrosscomprealcomplex[l][i][j]+"  ");   
                }
                System.out.println("");
                System.out.println("-------"+i);
            }
            System.out.println("");
            System.out.println("");
        }
        
        
        String s2 = "<script type=\"text/JavaScript\">";
        bw.write(s2+"\n");
        
        
        for (int i = 0; i < componentcount; i++)
        {
          
            {
              double[] voltR = new double[k];
              double[] voltC = new double[k];
              double[] currR = new double[k];
              double[] currC = new double[k];
              double[] freq = new double[k];
                for (int j = 0; j < k; j++) 
                {
                   voltR[j] = voltcurracrosscomprealcomplex[j][0][i] ;
                   voltC[j] = voltcurracrosscomprealcomplex[j][1][i];
                   currR[j] = voltcurracrosscomprealcomplex[j][2][i];
                   currC[j] = voltcurracrosscomprealcomplex[j][3][i];
                   freq[j]  = comb_of_diff_freq[j];
                }
                bw.write(displayelement(voltR, currR, voltC, currC, i,freq));
            }
        }
        
        
        
        bw.write("\n</script>\n");
        bw.write(Earthing(80,50));
//         
        s2 = "</svg></body></html>";
        bw.write(s2+"\n");
        System.out.println("done");
        bw.close();
        fw.close();
    }
        // TODO code application logic here
        
        
    
    
}
