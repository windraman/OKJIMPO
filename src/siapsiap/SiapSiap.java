/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siapsiap;

import java.util.Scanner;
import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosyid
 */
public class SiapSiap {

    /**
     * @param args the command line arguments
     */
    public static void praProses(String fileName) {
        // TODO code application logic here

        PraProses p = new PraProses();
        p.pisahPerFile(fileName);//menghasilakan 4 file Data.txt Problem.txt Metode.txt Hasil.txt        
        p.pisahPerKategori();
        p.kataKunci();        
      
        PraProsesTanpaDipisahperFile pP= new PraProsesTanpaDipisahperFile();
        pP.pisahPerKategori(fileName);
        pP.kataKunci();
        
    }
}
