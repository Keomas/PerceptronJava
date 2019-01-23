/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author keomas
 * 
 * Rodando jar, abrir prompt de comando e :
 * 
 * java -jar /path/JavaApplication1.jar
 * 
 */
public class Perceptron {
    /**
     * @param args the command line arguments
     */
   
    //calcula y_in
    public static double calcula_yin(double b, double entrada[], double weight [] ){
        double y_aux=0;
        for(int i =0; i < entrada.length; i++){
            
            y_aux=y_aux+ entrada[i]*weight[i];
        
        }
        
        return y_aux+b;
    
    
    }
     //calcula a saida da rede   
     public static double y_out(double y,double thresh){
         double y_aux1=0;
         if(y > thresh){
             y_aux1 =(double) 1;
         
         }else if (((-1*thresh) <= y) && (y<=thresh)){
             
             y_aux1 = (double)0;
             
         }else if (y < (-1*thresh) ){
             y_aux1 =(double) -1;
         }
         return y_aux1;
     }
     //atualiza os pesos
     public static void update_w(double [] entrada,double wi [] ,double t, double tx){
         
            for (int i =0; i < wi.length; i++){
                
                wi[i]=wi[i]+ entrada[i]*t*tx;
            }
     
     }
    
     public static void print_epoca(int x, double  [] we,double b){
         System.out.println("Epoca : "+x+" ");
         for(int i=0; i < we.length; i++){
             System.out.print("w"+i+"   ");
         }
         System.out.print("bias");
         System.out.println();
         for(int i=0; i < we.length; i++){
             System.out.print(we[i]+"   ");
         }
         System.out.print(b);
         System.out.println();
         System.out.println("########################");
         
     }
     
     
     
     
     public static void print_final(double[][] entrada, double [] ws, double bi,double tr){
         System.out.println("RESULTADO FINAL:");
         
         for(int i=0; i < ws.length; i++){
             System.out.print("w"+i+"   ");
         }
         System.out.print("bias");
         System.out.println();
         for(int i=0; i < ws.length; i++){
             System.out.print(ws[i]+"   ");
         }
         System.out.println(bi);
         System.out.println("----------------------------------");
         System.out.println("verificando:");
         System.out.println();
         for(int i=0; i < entrada.length; i++){
             System.out.print("entrada"+i+" :");
             for(int j=0; j < entrada[i].length;j++){
                 
                 System.out.print(entrada[i][j]+"*"+ws[j]+" + ");
                 
         }
             System.out.print(bi+" ");
             System.out.print(" = "+calcula_yin(bi,entrada[i],ws)+" y_out ~> "+y_out(calcula_yin(bi,entrada[i],ws),tr) );
             System.out.println();
             System.out.println();
             System.out.println();
         }
     
     
     
     }
     
     
     public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
    //variaveis
    //matriz com as entradas para treinamento, cada linha refere-se a uma entrada (x1,x2,x3) relacionada a um target.
    //{{x1,x2,x3},{x1,x2,x3},{x1,x2,x3}....};
    double vector_treinamento [][]= {{1,1,1},{1,1,0},{1,0,1},{0,1,1}}; 
    double target []= {1,-1,-1,-1};  //vetor de target, cada target está relacionado a uma linha da matriz de treinamento.
    double threshold = 0.1;          
    double  tx_l=1;                //taxa de aprendizagem
    double bias = 0;                 //bias, peso para entrada constante igual a 1(input_bias)..inicialmente 0  
    double w []={0,0,0};             //vetor de pesos, inicialemnte 0 e se relaciona na forma w={ wx1,wx2,wx3} onde wxn é peso da ligação entre xn -> y(unidade de saida)
    boolean has_change=false;        //auxiliar para controle de loop
    double y_in=0;                  // entrada da unidade de saida y
    double y=0;                     //saida da rede
    int count=1;                    //contador auxiliar
    
    /* o procedimento é generico, vetor de treinamento pode ser alterado para quaisquer quantidade de entradas, por exemplo:
     para redes com apenas 2 unidades de entrada:
    double vector_treinamento [][]= {{1,1},{1,0},{0,1},{0,0}}; obs: cada linha necessita de um target.
     double w []={0,0};    
     
    */
    //procedimento
    
     while(!has_change ){
         has_change=true;
         for(int i=0; i < vector_treinamento.length; i++){
                
                y_in= calcula_yin(bias,vector_treinamento[i],w);
             
                y=y_out(y_in,threshold);
                
                if(y != target[i]){
                    has_change=false;
                    update_w(vector_treinamento[i],w,target[i],tx_l);
                    bias=bias+tx_l*target[i];
                }
         
         }
         
         print_epoca(count, w,bias);
         count++;
        Thread.sleep(1500);
        }
     System.out.println();
     print_final(vector_treinamento,w,bias,threshold);  
    }
    
}
