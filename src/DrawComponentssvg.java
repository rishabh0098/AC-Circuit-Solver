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
public class DrawComponentssvg  extends Complexarithmetic {
   public  static String drawline(int x1 ,int y1 , int x2 ,int y2 )
    {
       
        // <line x1="500" y1="100" x2="650" y2="100" stroke-width="4" stroke="blue"/>
        String s = "<line x1=\""+x1+"\" y1=\""+y1+"\" x2=\""+x2+"\" y2=\""+y2+"\" stroke-width=\"4\" stroke=\"blue\" />";
        
        return s;
    }
    
   public static String  capacitor(int n1x ,int n1y,int n2x ,int n2y,int i,int H ,String name ,String value,int idno )   
    {
       
        //<line x1="850" y1="275" x2="950" y2="275" stroke-width="4" stroke="blue"/>
        //<line x1="850" y1="325" x2="950" y2="325" stroke-width="4" stroke="blue"/>
        //<circle  cx="500" cy="200" r="30" fill = "none" stroke = "blue" stroke-width = "2" />
        //<text x="650" y="600" font-size="40" fill="red">
        //Circuit </text>

        String s =(drawline(n1x,n1y,n1x+i*H,n1y)+"\n");
        s = s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+n1y+"\" r=\""+7+"\" fill =\"blue\" />";
        s = s + drawline(n1x+i*H,n1y,n1x+i*H,(n2y+n1y)/2 - 15)+"\n";        
        s = s + drawline(n1x+i*H -15,(n2y+n1y)/2 - 15,n1x+i*H+15,(n2y+n1y)/2 - 15)+"\n";
        s = s + drawline(n1x+i*H - 15,(n2y+n1y)/2 + 15,n1x+i*H +15,(n2y+n1y)/2 + 15)+"\n";
       
        s = s + drawline(n1x+i*H,(n2y+n1y)/2 + 15,n1x+i*H,n2y)+"\n";
        
        s = s + (drawline(n2x,n2y,n2x+i*H,n2y)+"\n");
        s = s + "<circle cx=\""+(n2x+i*H)+"\" cy=\""+n2y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";
         
        s = s + "<text x=\""+(n1x+i*H-40)+"\" y=\""+((n2y+n1y)/2-20)+"\" font-size=\""+15+"\" fill =\"black\" onClick=\"display"+idno+"()\" >"+"\n";
        s = s + name + "</text>"+"\n";
        s = s + "<text x=\""+(n1x+i*H-50)+"\" y=\""+((n2y+n1y)/2)+"\" font-size=\""+15+"\" fill =\"black\" onClick=\"display"+idno+"()\" >"+"\n";
        s = s + value + "</text>" + "\n";
        return s ;
        
    }
    
   public static String resistor(int n1x ,int n1y,int n2x ,int n2y,int i,int H,String name ,String value,int idno)
    {
        //<path d="M 650 100 L 655,90 660,100" fill="none" stroke="blue" />
        //<path d="M 660 100 L 665,110 670,100" fill="none" stroke="blue" />
      
        
        String s =(drawline(n1x,n1y,n1x+i*H,n1y)+"\n");
        s = s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+n1y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";
        s = s+drawline(n1x+i*H,n1y,n1x+i*H,(n2y+n1y)/2 - 20)+"\n";
        
        s = s+ "<path d=\"M "+(n1x+i*H)+" "+((n2y+n1y)/2 - 20)+" L "+(n1x+i*H+10)+","+((n2y+n1y)/2 - 15)+" "+(n1x+i*H)+","+((n2y+n1y)/2 -10)+"\" fill=\"none\" stroke=\"blue\"  />"+"\n"; 
        s = s+ "<path d=\"M "+(n1x+i*H)+" "+((n2y+n1y)/2 -10)+" L "+(n1x+i*H-10)+","+((n2y+n1y)/2-5)+" "+(n1x+i*H)+","+((n2y+n1y)/2 )+"\" fill=\"none\" stroke=\"blue\" />"+"\n";
         
        s = s+ "<path d=\"M "+(n1x+i*H)+" "+((n2y+n1y)/2 )+" L "+(n1x+i*H+10)+","+((n2y+n1y)/2 +5)+" "+(n1x+i*H)+","+((n2y+n1y)/2 +10)+"\" fill=\"none\" stroke=\"blue\" />"+"\n"; 
        s = s+ "<path d=\"M "+(n1x+i*H)+" "+((n2y+n1y)/2 +10)+" L "+(n1x+i*H-10)+","+((n2y+n1y)/2+15)+" "+(n1x+i*H)+","+((n2y+n1y)/2 +20)+"\" fill=\"none\" stroke=\"blue\" />"+"\n";
         
         
        s = s + drawline(n1x+i*H,(n2y+n1y)/2 + 20,n1x+i*H,n2y)+"\n";
        
        s = s + (drawline(n2x,n2y,n2x+i*H,n2y)+"\n");
        s = s+"<circle cx=\""+(n2x+i*H)+"\" cy=\""+n2y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";
         
        s = s+"<text x=\""+(n1x+i*H-40)+"\" y=\""+((n2y+n1y)/2-20)+"\" font-size=\""+15+"\" fill =\"black\" onClick=\"display"+idno+"()\" >"+"\n";
        s = s+name+"</text>"+"\n";
        s = s+"<text x=\""+(n1x+i*H-50)+"\" y=\""+((n2y+n1y)/2)+"\" font-size=\""+15+"\" fill =\"black\" onClick=\"display"+idno+"()\" >"+"\n";
        s = s+value+"</text>"+"\n";
        return s ;
        
    }
  public  static String voltagesource(int n1x ,int n1y,int n2x ,int n2y,int i,int H,String name,int idno)
    {
        //   <circle  cx="500" cy="200" r="30" fill = "none" stroke = "blue" stroke-width = "2" />
        //<circle  cx="500" cy="80" r="30" fill = "none" stroke = "blue" stroke-width = "2" />

        //<path d="M 480 200 Q 490,190 500,200" fill="none" stroke="blue" />
        //<path d="M 500 200 Q 510,210 520,200" fill="none" stroke="blue" />

        
        String s =(drawline(n1x,n1y,n1x+i*H,n1y)+"\n");
        s = s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+n1y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";
        s = s + drawline(n1x+i*H,n1y,n1x+i*H,(n2y+n1y)/4 + 30)+"\n";
        
        s = s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+(n2y+n1y)/4+"\" r=\""+30+"\" fill =\"none\" stroke =\"blue\" stroke-width =\"2\" />"+"\n";
        s = s+ "<path d=\"M "+((n1x+i*H)-20)+" "+((n2y+n1y)/4 )+" Q "+(n1x+i*H-10)+","+((n2y+n1y)/4 -10)+" "+(n1x+i*H)+","+((n2y+n1y)/4 )+"\" fill=\"none\" stroke=\"blue\" />"+"\n"; 
        s = s+ "<path d=\"M "+(n1x+i*H)+" "+((n2y+n1y)/4 )+" Q "+(n1x+i*H+10)+","+((n2y+n1y)/4 +10)+" "+(n1x+i*H+20)+","+((n2y+n1y)/4)+"\" fill=\"none\" stroke=\"blue\" />"+"\n"; 
      
        s = s + drawline(n1x+i*H,(n2y+n1y)/4 - 30,n1x+i*H,n2y)+"\n";
        
        s = s + (drawline(n2x,n2y,n2x+i*H,n2y)+"\n");
        s = s+"<circle cx=\""+(n2x+i*H)+"\" cy=\""+n2y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";
         
        s = s+"<text x=\""+(n1x+i*H-70)+"\" y=\""+((n2y+n1y)/4-40)+"\" font-size=\""+10+"\" fill =\"black\" onClick=\"display"+idno+"()\" >"+"\n";
        s = s+name+"</text>"+"\n";
        return s ;
    }
  public  static String inductor(int n1x ,int n1y,int n2x ,int n2y,int i, int H,String name ,String value,int idno)
    {
   //<path d="M 100 200 Q 70,210 100,220" fill="none" stroke="blue" stroke-width ="2" />

        
        String s =(drawline(n1x,n1y,n1x+i*H,n1y)+"\n");
        s= s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+n1y+"\" r=\""+7+"\" fill =\"none\" />"+"\n";
        s =s + drawline(n1x+i*H,n1y,n1x+i*H,(n2y+n1y)/2 - 30)+"\n";
        
        s=s+ "<path d=\"M "+((n1x+i*H))+" "+((n2y+n1y)/2-30)+" Q "+(n1x+i*H-30)+","+((n2y+n1y)/2 -20)+" "+(n1x+i*H)+","+((n2y+n1y)/2-10)+"\" fill=\"none\" stroke=\"blue\" stroke-width=\"4\" />"+"\n"; 
        s=s+ "<path d=\"M "+(n1x+i*H)+" "+((n2y+n1y)/2-10)+" Q "+(n1x+i*H-30)+","+((n2y+n1y)/2)+" "+(n1x+i*H)+","+((n2y+n1y)/2 +10)+"\" fill=\"none\" stroke=\"blue\" stroke-width=\"4\" />"+"\n"; 
        s=s+ "<path d=\"M "+(n1x+i*H)+" "+((n2y+n1y)/2+10)+" Q "+(n1x+i*H-30)+","+((n2y+n1y)/2+20)+" "+(n1x+i*H)+","+((n2y+n1y)/2 +30)+"\" fill=\"none\" stroke=\"blue\" stroke-width=\"4\" />"+"\n";        
        s =s + drawline(n1x+i*H,(n2y+n1y)/2 + 30,n1x+i*H,n2y)+"\n";
        
        s =s + (drawline(n2x,n2y,n2x+i*H,n2y)+"\n");
         
        s= s+"<circle cx=\""+(n2x+i*H)+"\" cy=\""+n2y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";
         
        s= s+"<text x=\""+(n1x+i*H-40)+"\" y=\""+((n2y+n1y)/2-20)+"\" font-size=\""+15+"\" fill =\"black\" onClick=\"display"+idno+"()\" >"+"\n";
        s=s+name+"</text>"+"\n";
        s= s+"<text x=\""+(n1x+i*H-50)+"\" y=\""+((n2y+n1y)/2)+"\" font-size=\""+15+"\" fill =\"black\" onClick=\"display"+idno+"()\" >"+"\n";
        s=s+value+"</text>"+"\n";
        return s ;
    }
  public  static String  currentsource(int n1x ,int n1y,int n2x ,int n2y,int i,int H,String name,int idno)
    {
//<path d="M 80 100 L 120,100 120,160" fill="none" stroke="blue" />
//<path d="M 120 160 L 80,160 80,100" fill="none" stroke="blue" />
//<circle  cx="100" cy="120" r="15" fill = "none" stroke = "blue" stroke-width = "2" />
//<circle  cx="100" cy="140" r="15" fill = "none" stroke = "blue" stroke-width = "2" />


        
         String s =(drawline(n1x,n1y,n1x+i*H,n1y)+"\n");
         s= s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+n1y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";  //dot
         s =s + drawline(n1x+i*H,n1y,n1x+i*H,(n2y+n1y)/4 + 25)+"\n";
        
         s=s+ "<path d=\"M "+(n1x+i*H-20)+" "+((n2y+n1y)/4 - 30)+" L "+(n1x+i*H+20)+","+((n2y+n1y)/4 -30)+" "+(n1x+i*H+20)+","+((n2y+n1y)/4 +30)+"\" fill=\"none\" stroke=\"blue\" />"+"\n"; 
         s=s+ "<path d=\"M "+(n1x+i*H+20)+" "+((n2y+n1y)/4 +30)+" L "+(n1x+i*H-20)+","+((n2y+n1y)/4+30)+" "+(n1x+i*H-20)+","+((n2y+n1y)/4-30)+"\" fill=\"none\" stroke=\"blue\" />"+"\n";
         
         
         s= s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+((n2y+n1y)/4-10)+"\" r=\""+15+"\" fill =\"none\" stroke =\"blue\" stroke-width =\"2\" />"+"\n";
         s= s+"<circle cx=\""+(n1x+i*H)+"\" cy=\""+((n2y+n1y)/4+10)+"\" r=\""+15+"\" fill =\"none\" stroke =\"blue\" stroke-width =\"2\" />"+"\n";
        
         
         
         s =s + drawline(n1x+i*H,((n2y+n1y)/4 - 25),n1x+i*H,n2y)+"\n";
        
         s =s + (drawline(n2x,n2y,n2x+i*H,n2y)+"\n");
         s= s+"<circle cx=\""+(n2x+i*H)+"\" cy=\""+n2y+"\" r=\""+7+"\" fill =\"blue\" />"+"\n";
         
         s= s+"<text x=\""+(n1x+i*H-70)+"\" y=\""+((n2y+n1y)/4+40)+"\" font-size=\""+10+"\" fill =\"black\"  onClick=\"display"+idno+"()\" >"+"\n";
         s=s+name+"</text>"+"\n";
        return s ;
    }
  public  static String  Earthing(int n1x ,int n1y)
    {
  
//<line x1="700" y1="500" x2="700" y2="515" stroke-width="4" stroke="blue"/>
//<line x1="680" y1="515" x2="720" y2="515" stroke-width="2" stroke="blue"/>
//<line x1="685" y1="520" x2="715" y2="520" stroke-width="2" stroke="blue"/>
//<line x1="690" y1="525" x2="710" y2="525" stroke-width="2" stroke="blue"/>
//<line x1="695" y1="530" x2="705" y2="530" stroke-width="2" stroke="blue"/>
        
         String s = drawline(n1x,n1y,n1x,(n1y-20))+"\n";
         s= s+"<circle cx=\""+(n1x)+"\" cy=\""+n1y+"\" r=\""+7+"\" fill =\"none\" />"+"\n";  //dot
         
         s =s + (drawline(n1x-20,n1y-20,n1x+20,n1y-20)+"\n");
         s =s + (drawline(n1x-15,n1y-25,n1x+15,n1y-25)+"\n");
         s =s + (drawline(n1x-10,n1y-30,n1x+10,n1y-30)+"\n");
         s =s + (drawline(n1x-5,n1y-35,n1x+5,n1y-35)+"\n");
        
        return s ;
    }
  
  public  static String  displayelement(double voltR[] , double currR[] ,double voltC[] , double currC[] ,int idno, double freq[])
    {   String s1 = "";
       
        for (int i = 0; i < voltR.length; i++)
        {   double Vmagnitude = Math.sqrt(voltR[i]*voltR[i] + voltC[i]*voltC[i]);   
            double Vangle = Math.atan(voltR[i]/voltC[i]);
            double Cmagnitude = Math.sqrt(currR[i]*currR[i] + currC[i]*currC[i]);   
            double Cangle = Math.atan(currR[i]/currC[i]);
           s1  = s1 +"freq "+freq[i]+"  V ="+Vmagnitude+" angle "+Vangle+"  I = "+Cmagnitude+" angle "+Cangle+"  ";
        }
        
        String s = "      function display"+idno+"()\n" +
"	{\n" +
"	 $(\"#data\").text(\""+s1+"\");\n" +
"	 document.getElementById(\"data\").style.display = \"block\";\n" +
"	}\n";
     return s ; 
    }
  public  static double analyseunit (String s)
    {  
       if(s.contains("k") || s.contains("K"))
       {   int a = s.indexOf('k') + s.indexOf('K')+1;
           return(Double.parseDouble(s.substring(0,a))*1000);
       } else
           if(s.contains("m") || s.contains("M"))
       {   int a = s.indexOf('m') + s.indexOf('M')+1;
           return(Double.parseDouble(s.substring(0,a))*1000000);
       } else
               if(s.contains("n") || s.contains("N"))
       {   int a = s.indexOf('n') + s.indexOf('N')+1;
           return(Double.parseDouble(s.substring(0,a))*0.000000001);
       } else
                   if(s.contains("p") || s.contains("P"))
       {   int a = s.indexOf('p') + s.indexOf('P')+1;
          
           return(Double.parseDouble(s.substring(0,a))*0.000000000001);
       }
       else
           {return 0;}
       
    
    }
    

}
