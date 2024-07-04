/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siapsiap;

import com.sun.javafx.image.impl.IntArgb;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.rowset.internal.Row;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author rosyid
 */
public class MetaData2 {

    String namaFolder = "TotalPerKategoriKK";
   // File fl1 = new File(namaFolder);
   // File[] isiDir1 = fl1.listFiles();
    String[] kategoriList = new String[]{"DATA","HASIL","METODE","PROBLEM"};
    Integer[] kategoriJumlah = new Integer[]{0,0,0,0};
    Integer[] kategoriTotal = new Integer[]{0,0,0,0};
    Double[] kategoriP = new Double[]{1.0,1.0,1.0,1.0};
    List<List<String>> listRow = new ArrayList<List<String>>();
    String folder = "fileKataKunci";
    Vector isiFile = new Vector();
    Berkas berkas = new Berkas();
    
        Tokenizing tokenFile = new Tokenizing();
        Vector kata = new Vector();
    
    public Integer jFile(){
        File f1 = new File(folder);
        File[] isiDir = f1.listFiles();
        return isiDir.length;
    }
    
    class Output {
        List<List<String>> hasil = new ArrayList<List<String>>();
        String kelompok;
        List<String> header;
    }
    
    public List<String> KataGabungan(){
        List<String> listKataGabungan = new ArrayList<String>();
        for(int h = 0; h < kategoriList.length;h++){
            isiFile = berkas.bacaFileTxt(namaFolder + "/" + kategoriList[h] + ".txt");
            for (int i = 0; i < isiFile.size(); i++) {
                kata = tokenFile.methodTokenizer(isiFile.get(i).toString(), "[,");
                
                if(!listKataGabungan.contains(kata.toString())){
                    listKataGabungan.add(kata.get(0).toString());                     
                }
            }
        }
        return listKataGabungan;
    }
   

    public Output buatTabel(Integer limitFile,Integer testingFile, Integer lkata) throws FileNotFoundException {    

        
        File f1 = new File(folder);
        File[] isiDir = f1.listFiles();
//        List<String> listKataGabungan = new ArrayList<String>();
        
        String kata2 = "";
        int number = 0;
        PrintWriter pw = new PrintWriter(new File("input_outlier.csv"));
        int limit = limitFile;
        int testing = testingFile;
        int jumlahFile = 0;
        int jumlahDLatih = 0;
        double prKatData = 0;
        jumlahFile = jFile()-limit-1;
        jumlahDLatih = jumlahFile * kategoriList.length;
        prKatData = (float) jumlahFile/jumlahDLatih;
        int limitKata = lkata; 
        List<String> header = new ArrayList<String>();
        
        
        
        
       // System.out.println(listKataGabungan.toString());
         StringBuilder sb = new StringBuilder(); 
         //StringBuilder sb2 = new StringBuilder(); 
           for (int i = 0; i < KataGabungan().size()-limitKata; i++) {
               List<String> row = new ArrayList<String>();
               
                if(i==0){                        

                    header.add("No");
                    header.add("Term");
                  
                    for (int n = 0; n < isiDir.length-limit; n++) { 
                        for(int m = 0; m < kategoriList.length;m++){
                            if(n!=testing-1){
                               // sb.append(kategoriList[m].substring(0,1)+"/"+isiDir[n].toString().substring(0,1) + isiDir[n].toString().substring(isiDir[n].toString().length()-2,isiDir[n].toString().length()));
                                //sb.append(",");
                                header.add(kategoriList[m].substring(0,1)+"/"+isiDir[n].toString().substring(0,1) + isiDir[n].toString().substring(isiDir[n].toString().length()-2,isiDir[n].toString().length()));
                            }
                        }
                        if(n==isiDir.length-limit-1){
                             //sb.append("FTest");
                             header.add("FTest");
//                                for(int m = 0; m < kategoriList.length;m++){
//                                    sb.append(kategoriList[m].substring(0,1)+"/"+isiDir[testing-1].toString().substring(0,1) + isiDir[testing-1].toString().substring(isiDir[testing-1].toString().length()-2,isiDir[testing-1].toString().length())+"-Testing");
//                                    sb.append(",");
//                                }
                        }                            
                    }
                    
                }
                number++;                
                
                kata2 = KataGabungan().get(i).toString();
                row.add(String.valueOf(number));
                //row.add(kata2);                
//                sb.append(number);
//                sb.append(",");
//                sb.append(kata2);
//                sb.append(",");
                
                
                for (int j = 0; j < isiDir.length-limit; j++) {                  //77
                   // System.out.println("isiDIr = " + isiDir[j] + "\\data.txt");
                    Vector isiFile1 = new Vector();
                    Berkas berkas1 = new Berkas();
                    
                     
                    for(int k = 0; k < kategoriList.length;k++){
                        isiFile1 = berkas.bacaFileTxt(isiDir[j] + "\\"+ kategoriList[k] + ".txt" );
                        Vector kata1 = new Vector();  
                        if(j!=testing-1){
                            if (!isiFile1.isEmpty()) {                              
                                kata1 = tokenFile.methodTokenizer(isiFile1.toString(), "[,"); // parameter:text,Pemisah 
                                    if (kata2.equalsIgnoreCase(kata1.get(0).toString())) {
                                        //sb.append(kata1.get(1).toString());
                                        //row.add(kata1.get(1).toString());
                                        kategoriJumlah[k] = kategoriJumlah[k] + Integer.parseInt(kata1.get(1).toString());
                                    }else{
                                        //sb.append("0");   
                                       // row.add("0");  
                                    }
                            }else{
                                //sb.append("0");
                                //row.add("0");
                            }
                           // if(j < 2){
                             //sb.append(",");
                            //}
                        }
                    }

                }
               
               for(int o = 0;o < kategoriJumlah.length;o++){
                   //sb.append(kategoriJumlah[o]);
                   //sb.append(",");
                   row.add(String.valueOf(kategoriJumlah[o]));
                   
                   
                   kategoriJumlah[o] = 0;
               }
               
               
               
              // sb.append('\n'); 
               listRow.add(row);
               
            }
          
         
//          for(int p = 0; p < listRow.size(); p++){
//               List<String> isiRow = new ArrayList<String>();
//               isiRow = listRow.get(p);
//               for(int o = 0;o < kategoriJumlah.length;o++){
//                   kategoriTotal[o] += Integer.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+4+(o)));
//               }    
//               
//          }
//          
//          for(int p = 0; p < listRow.size(); p++){
//               List<String> isiRow = new ArrayList<String>();
//               isiRow = listRow.get(p);
//               isiRow.add(String.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+1)));
//               for(int o = 0;o < kategoriJumlah.length;o++){
//                   //System.out.println(Integer.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+4+o))+1 + "/" + kategoriTotal[o] + "/" + number);
//                   float frD = (float) Integer.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+4+o))+1/(float)kategoriTotal[o]/(float) number;
//                   isiRow.add(String.valueOf(frD));
//
//               }   
////               isiRow.add(kelompok);
//               
//               System.out.println(isiRow.toString());
//          }
////          System.out.println(kategoriTotal[0].toString());
////               System.out.println(kategoriTotal[1].toString());
////               System.out.println(kategoriTotal[2].toString());
////               System.out.println(kategoriTotal[3].toString());
//          for(int p = 0; p < listRow.size(); p++){
//               List<String> isiRow = new ArrayList<String>();
//               isiRow = listRow.get(p);
//               for(int o = 0;o < kategoriJumlah.length;o++){
//                   if(Integer.valueOf(isiRow.get(isiRow.size()-6))>0){
//                       kategoriP[o] *= Double.parseDouble(isiRow.get(isiRow.size()-(kategoriJumlah.length-3+(o))));
//                    }
//               }
//          }
//          
//          double max = new Double(0);          
//          String kelompok = "";
//          List<Double> nilaiP = new ArrayList<>();
//          for(int q = 0;q < kategoriP.length; q++){
//             nilaiP.add(kategoriP[q]);
//             double curMax = new Double(kategoriP[q]);
//             // System.out.println(curMax + ">" + max + "=" + Boolean.valueOf(curMax > max));
//            if (curMax > max)
//             {
//                 max = kategoriP[q];              
//                 kelompok = kategoriList[q].toString();
//             }
//          }
          Output output = new Output();
          output.hasil = listRow;
//          output.kelompok = kelompok;
//          output.header = header;
          
         for(int p = 0; p < listRow.size(); p++){
             System.out.println(listRow.get(p).toString());
               sb.append(listRow.get(p).toString().replaceAll("\\[", "").replaceAll("\\]",""));
               sb.append("\n");
//               List<String> isiRow = new ArrayList<String>();
//               isiRow = listRow.get(p);
//               for(int o = 0;o < kategoriList.length;o++){
//                   if(Integer.valueOf(isiRow.get(isiRow.size()-6))>0){
//                       kategoriP[o] *= Double.parseDouble(isiRow.get(isiRow.size()-(kategoriJumlah.length-3+(o))));
//                    }
//               }
               
          }
          
         // pw.write(sb.toString());
         // pw.close();
          
          
          
          return output;
    }
    

}
