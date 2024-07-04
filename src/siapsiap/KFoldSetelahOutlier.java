/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siapsiap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wahyu
 */
public class KFoldSetelahOutlier {
    
    class Output {
        int error;
        String cetak;
        Double FError;
    }
    
    public Output KFold(int limitFile, int perulangan, int lKata, String testingKategori) {
        
        MetaDataSetelahOutlier md = new MetaDataSetelahOutlier();
        int dTest = perulangan;
        int jFile = md.jFile()-limitFile;
        int kelFile = jFile/dTest;
        int sisaKelFile = jFile%dTest;
        int ulang = 0;
        int kali = 0;
        StringBuilder sb = new StringBuilder(); 
       // List<String> listKelompok = new ArrayList<String>();
        String[] kategoriList = new String[]{"DATA","HASIL","METODE","PROBLEM"};
       
        int jumlahError = 0;
        
        if(sisaKelFile == 0){
            ulang = kelFile;
        }else{
            ulang = kelFile + 1;
        }
         System.out.println(ulang + " Sisa = " + sisaKelFile);
         for(int i = 1; i <= ulang;i++ ){
             int error = 0;
             System.out.println("================");
             System.out.println("grup " + i);
             sb.append("================");
             sb.append("\n");
             sb.append("grup " + i);
             sb.append("\n");
             for(int j = 1; j <= dTest; j++){
                 System.out.println("data tes = " + Integer.valueOf(dTest*kali+j));
                 sb.append("data tes = " + Integer.valueOf(dTest*kali+j));
                 sb.append("\n");
                 
                 try {
                     MetaDataSetelahOutlier md2 = new MetaDataSetelahOutlier();
                     MetaDataSetelahOutlier.Output output;
                     output = md2.buatTabel(limitFile,Integer.valueOf(dTest*kali+j),lKata,testingKategori);
                     for(int k = 0;k < output.hasil.size();k++){
                         System.out.println("P(" + kategoriList[k] + "|DokUji) = " + output.hasil.get(k).toString());
                         sb.append("P(" + kategoriList[k] + "|DokUji) = " + output.hasil.get(k).toString());
                         sb.append("\n");
                     }
                     System.out.println("Kategori = " + output.kelompok);                     
                     sb.append("Kategori = " + output.kelompok);
                      sb.append("\n");
                     // listKelompok.add(output.kelompok);
                     if(output.kelompok!=testingKategori){
                         error++;
                     }
                     
                      
                 } catch (FileNotFoundException ex) {
                     Logger.getLogger(KFoldSetelahOutlier.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             kali++;
             //menentukan data yang unik dalam susunan array
            // Set<String> h = new HashSet<String>(Arrays.asList(listKelompok.toArray(new String[0])));
             //Set<String> h = new HashSet<String>(Arrays.asList(new String[]{"DATA", "DATA", "DATA", "PROBLEM", "DATA"}));
            System.out.println("Error = " + error);
            sb.append("Error = " + error);
            sb.append("\n");
            jumlahError += error;
            
      
             System.out.println("================");
             sb.append("================");
             sb.append("\n");
             
         }
         Output output = new Output();
         output.cetak = sb.toString();
         output.error = jumlahError;
         output.FError = Double.valueOf(jumlahError)/Double.valueOf(ulang*perulangan);
         return output;
    }
}
