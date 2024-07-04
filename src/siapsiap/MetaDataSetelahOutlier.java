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
import java.awt.Color;
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
public class MetaDataSetelahOutlier {

    String namaFolder = "TotalPerKategoriKK";
   // File fl1 = new File(namaFolder);
   // File[] isiDir1 = fl1.listFiles();
    String[] kategoriList = new String[]{"DATA","HASIL","METODE","PROBLEM"};
    Integer[] kategoriJumlah = new Integer[]{0,0,0,0};
    Integer[] kategoriTotal = new Integer[]{0,0,0,0};
    List<Double> kategoriT;
    Double[] kategoriP = new Double[]{1.0,1.0,1.0,1.0};
    List<List<String>> listRow = new ArrayList<List<String>>();
    String folder = "fileKataKunci";
    
    public Integer jFile(){
        File f1 = new File(folder);
        File[] isiDir = f1.listFiles();
        return isiDir.length;
    }
        
    class Output {
        List<Double> hasil;
        List<Double> hasilT;
        String kelompok;
        List<String> header;
        List<List<String>> listRow = new ArrayList<List<String>>();
    }
   

    public Output buatTabel(Integer limitFile,Integer testingFile, Integer limKata, String testingKategori) throws FileNotFoundException {

        Vector isiFile = new Vector();
        Berkas berkas = new Berkas();

        Tokenizing tokenFile = new Tokenizing();
        Vector kata = new Vector();
        
        File f1 = new File(folder);
        File[] isiDir = f1.listFiles();
        List<String> listKataGabungan = new ArrayList<String>();
        
        String kata2 = "";
        int number = 0;
        PrintWriter pw = new PrintWriter(new File("tes_tabel.csv"));
        //PrintWriter pw2 = new PrintWriter(new File("input_outlier.csv"));
        int limit = limitFile;
        int testing = testingFile;
        int jumlahFile = 0;
        int jumlahDLatih = 0;
        double prKatData = 0;
        jumlahFile = jFile()-limit-1;
        jumlahDLatih = jumlahFile * kategoriList.length;
        prKatData = (float) jumlahFile/jumlahDLatih;
        int limitKata = limKata; 
        List<String> header = new ArrayList<String>();
        
        
        listKataGabungan = FrameProses.KataGabunganBaru;      
        
       // System.out.println(listKataGabungan.toString());
         StringBuilder sb = new StringBuilder(); 
        // StringBuilder sb2 = new StringBuilder(); 
           for (int i = 0; i < listKataGabungan.size()-limitKata; i++) {
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
                             header.add("F"+ testingKategori +"Test" + testing);
//                                for(int m = 0; m < kategoriList.length;m++){
//                                    sb.append(kategoriList[m].substring(0,1)+"/"+isiDir[testing-1].toString().substring(0,1) + isiDir[testing-1].toString().substring(isiDir[testing-1].toString().length()-2,isiDir[testing-1].toString().length())+"-Testing");
//                                    sb.append(",");
//                                }
                        }                            
                    }
                    header.add("Pr(Vdata)");
                    header.add("FD");
                    header.add("FH");
                    header.add("FM");
                    header.add("FP");
                    header.add("F"+ testingKategori +"Test" + testing);
                    header.add("P(W|V)D");
                    header.add("P(W|V)H");
                    header.add("P(W|V)M");
                    header.add("P(W|V)P");
                }
                number++;                
                
                kata2 = listKataGabungan.get(i).toString();
                row.add(String.valueOf(number));
                row.add(kata2);                
//                sb.append(number);
//                sb.append(",");
//                sb.append(kata2);
//                sb.append(",");
                
                //System.out.println(isiDir.length);
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
                                        row.add(kata1.get(1).toString());
                                        kategoriJumlah[k] = kategoriJumlah[k] + Integer.parseInt(kata1.get(1).toString());
                                    }else{
                                        //sb.append("0");   
                                        row.add("0");  
                                    }
                            }else{
                                //sb.append("0");
                                row.add("0");
                            }
                           // if(j < 2){
                             //sb.append(",");
                            //}
                        }
                    }
                    if(j==isiDir.length-limit-1){
                        int FTest=0;
                        //for(int k = 0; k < kategoriList.length;k++){
                            isiFile1 = berkas.bacaFileTxt(isiDir[testing] + "\\"+ testingKategori + ".txt" );
                            Vector kata1 = new Vector();  
                            if (!isiFile1.isEmpty()) {                              
                                kata1 = tokenFile.methodTokenizer(isiFile1.toString(), "[,"); // parameter:text,Pemisah 
                                    if (kata2.equalsIgnoreCase(kata1.get(0).toString())) {
                                        FTest += Integer.parseInt(kata1.get(1).toString());
                                        //sb.append(kata1.get(1).toString());
                                    }else{
                                        //sb.append("0");                                
                                    }
                            }else{
                                //sb.append("0");
                            }
                           // if(j < 2){
                             //sb.append(",");
                                    //}

//                        }
                       // sb.append(FTest);
                        //sb.append(",");
                        //sb.append(prKatData);
                        //sb.append(",");
                        row.add(String.valueOf(FTest));
                        row.add(String.valueOf(prKatData));
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
          
         
          for(int p = 0; p < listRow.size(); p++){
               List<String> isiRow = new ArrayList<String>();
               isiRow = listRow.get(p);
               for(int o = 0;o < kategoriJumlah.length;o++){
                   kategoriTotal[o] += Integer.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+4+(o)));
               }    
               
          }
          
           
          
          for(int p = 0; p < listRow.size(); p++){
               List<String> isiRow = new ArrayList<String>();
               isiRow = listRow.get(p);
               isiRow.add(String.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+2)));
               for(int o = 0;o < kategoriJumlah.length;o++){
                   //System.out.println(Integer.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+4+o))+1 + "/" + kategoriTotal[o] + "/" + number);
                   float frD = (float) Integer.valueOf(isiRow.get(((isiDir.length-limit-1)*4)+4+o))+1/(float)kategoriTotal[o]/(float) number;
                   isiRow.add(String.valueOf(frD));
               }   

          }

          for(int p = 0; p < listRow.size(); p++){
               List<String> isiRow = new ArrayList<String>();
               isiRow = listRow.get(p);
               for(int o = 0;o < kategoriJumlah.length;o++){
                   if(Integer.valueOf(isiRow.get(isiRow.size()-5))>0){
                       //System.out.println(isiRow.get(0));
                       kategoriP[o] *= Double.parseDouble(isiRow.get(isiRow.size()-(kategoriJumlah.length-3+(o))));
                    }
               }
          }
          
          double max = new Double(0);          
          String kelompok = "";
          List<Double> nilaiP = new ArrayList<>();
          for(int q = 0;q < kategoriP.length; q++){
             nilaiP.add(kategoriP[q]);
             double curMax = new Double(kategoriP[q]);
             // System.out.println(curMax + ">" + max + "=" + Boolean.valueOf(curMax > max));
            if (curMax > max)
             {
                 max = kategoriP[q];              
                 kelompok = kategoriList[q].toString();
             }
          }
          Output output = new Output();
          output.hasil = nilaiP;
         // output.hasil = kategoriT;
          output.kelompok = kelompok;
          output.header = header;
          output.listRow = listRow;
//          for(int o = 0;o < kategoriTotal.length;o++){
//                output.hasilT.add(Double.valueOf(kategoriTotal[o]));
//          } 
          
         for(int p = 0; p < listRow.size(); p++){
               sb.append(listRow.get(p).toString().replaceAll("\\[", "").replaceAll("\\]",""));
               sb.append("\n");
               List<String> isiRow = new ArrayList<String>();
               isiRow = listRow.get(p);
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
