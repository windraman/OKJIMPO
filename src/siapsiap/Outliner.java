/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package siapsiap;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Scanner;

/**
 *
 * @author marhusin
 */
public class Outliner {
    class Output {
        List<String> KataGabunganBaru = new ArrayList<String>();
        String cetak;
        double[][] matrik;
    }
    
     public Output Outliner(int limitFile, int nilaid, int nilaip, int limitKata) throws FileNotFoundException {
        // TODO code application logic here
        MetaData2 md2 = new MetaData2();
        MetaData2.Output outputmd2 = md2.buatTabel(limitFile, 3, limitKata);
        Vector hasilt = new Vector();
        List<List<String>> hasil = new ArrayList<List<String>>();
        
        StringBuilder sb = new StringBuilder(); 
        BacaFile buka = new BacaFile();
        Scanner scan = new Scanner(System.in);
        //hasilt = buka.bacaFileTxt("input_outlier.csv");
        hasil = outputmd2.hasil;
        int panjang = hasil.size();
        double [][] data = new double[panjang][4];
        int[] jarakJauh = new int[data.length];
         List<String> KataGabungan = new ArrayList<String>();
         KataGabungan = new MetaData2().KataGabungan();
        for (int i = 0; i < panjang; i++) {
            String s = hasil.get(i).toString().replaceAll("\\[", "").replaceAll("\\]","");
            StringTokenizer st = new StringTokenizer(s, " ,");
            int cek = 0;
           //
            while (st.hasMoreTokens()) // selama masih ada token, cetak
            {
                cek++;
                
                int isi = Integer.parseInt(st.nextToken());
                if (cek > 1) {
                    data[i][cek-2]=isi; 
                    System.out.println("matrik[" + i + "][" + Integer.valueOf(cek-2) + "]=" + isi);
                    sb.append("matrix[" + i + "][" + Integer.valueOf(cek-2) + "]=" + isi);    
                    sb.append("\n");
                }
            }
            cek = 0;

           // System.out.println("");
        }
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data[i].length;j++){
               System.out.print(data[i][j]+" ");
               sb.append(data[i][j]+" ");
            }
            System.out.println(" ");
            sb.append(" ");    
            sb.append("\n");
        }
        //inisialisasi matrik ecuilidiean
        double[][] matrik= new double [data.length][data.length];
        //System.out.println("len = " + data.length);
        for(int i=0;i<matrik.length;i++){
            for(int j=0;j<matrik[i].length;j++){
                for (int k=0;k<data[i].length;k++){
                    matrik[i][j] = matrik[i][j]+(Math.pow(data[i][k]-data[j][k],2));
                    
                    //System.out.println(matrik[i][j]);
                    //sb.append(matrik[i][j]);    
                    //sb.append("\n");
                }
            }
        }
        int d, p;
        System.out.println("");
        sb.append("");
        sb.append("\n");
       // System.out.print("input d = ");
        d = nilaid;
        //System.out.print("input p = ");
        p = nilaip;
        int temp=0;
        System.out.println("");
        sb.append("--MATRIK--");
        sb.append("\n");
        for(int i=0;i<matrik.length;i++){
            for(int j=0;j<matrik[i].length;j++){
               
               matrik[i][j] = Math.sqrt(matrik[i][j]);
                System.out.print(matrik[i][j]+" ");
                sb.append(matrik[i][j]+" ");
                
               if (matrik[i][j]>=d){
                   temp++;
               }
            }
            jarakJauh[i]=temp;
            temp=0;
            System.out.println(" ");
            sb.append(" ");
            sb.append("\n");
        }
        System.out.println("\nJarak >= " + d);
        sb.append("\nJarak >= " + d);
        sb.append("\n");
        for(int i=0;i<matrik.length;i++){
            System.out.println("jarak kata "+ KataGabungan.get(i).toString()+ " ="+jarakJauh[i]);
            sb.append("jarak kata "+ KataGabungan.get(i).toString()+ " = "+jarakJauh[i]);
            sb.append("\n");
            if(jarakJauh[i]>=p){
                System.out.print(KataGabungan.get(i)+" ");
                sb.append(KataGabungan.get(i)+" -> dieliminasi");

                KataGabungan.remove(KataGabungan.get(i));                
           }
            sb.append("\n");
        }
//        System.out.print("\nOutliner = ");
//        sb.append("\nOutliner = ");
//        sb.append("\n");
//        for(int i=0;i<matrik.length;i++){
//           
//        }
        System.out.println("");
         sb.append(" ");  
         sb.append("\n");
         
         Output output = new Output();
         output.KataGabunganBaru = KataGabungan;
         output.cetak = sb.toString();
         output.matrik = matrik;
         
         return output;
     }
    
    
}
