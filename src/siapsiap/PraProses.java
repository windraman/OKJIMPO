/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siapsiap;

import java.io.File;
import java.util.*;
import java.util.StringTokenizer;

/**
 *
 * @author rosyid
 */
public class PraProses {

    Vector label = new Vector();
    String fold = "filePerFile";
    String foldKategori = "filePerKategori";
    String foldKataKunci = "fileKataKunci";

    public void pisahPerFile(String namaFile) {
        Vector isiFile = new Vector();
        Berkas berkas = new Berkas();
        isiFile = berkas.bacaFileTxt(namaFile);
        int hitungFile = 0;
        int jumlahData = isiFile.size();
        // String s = ("");
        Vector vFile = new Vector();
        Vector vIsiFile = new Vector();

        //untuk memisah menjadi 77 file
        for (int i = 0; i < jumlahData; i++) {
            if (!isiFile.get(i).toString().startsWith("-")) {
                if (isiFile.get(i).toString().startsWith("File")) {
                    hitungFile++;
                    if (vIsiFile.size() != 0) {
                        Object a = vIsiFile;
                        vFile.addElement(a);
                        vIsiFile = new Vector();
                    }
                } else {
                    vIsiFile.addElement(isiFile.get(i));
                }
            }
        }
        vFile.addElement(vIsiFile);
        File fl = new File(".");
        boolean d;

        mkDir(fold);
        for (int i = 0; i < vFile.size(); i++) {
            if (i < 9) {
                berkas.tulisFileTxt(fold + "/file_0" + (i + 1) + ".txt", (Vector) vFile.get(i));
            } else {
                berkas.tulisFileTxt(fold + "/file_" + (i + 1) + ".txt", (Vector) vFile.get(i));
            }
        }
    }

    public void pisahPerKategori() {

        File file77 = new File(fold);
        File[] isiDir = file77.listFiles();
        Vector isiFile = new Vector();
        Vector temp = new Vector();
        Vector temp1 = new Vector();

        Vector[] hasil = new Vector[4];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new Vector();
        }

        label.addElement("METODE");
        label.addElement("DATA");
        label.addElement("PROBLEM");
        label.addElement("HASIL");

        //buat folder
        mkDir(foldKategori);
        for (int i = 0; i < isiDir.length; i++) {
            for (int j = 0; j < hasil.length; j++) {
                hasil[j].removeAllElements();
            }

            temp.removeAllElements();
            temp1.removeAllElements();
            // System.out.print(isiDir[i].toString());
            Berkas berkas = new Berkas();
            isiFile = berkas.bacaFileTxt(isiDir[i].toString());
            //  System.out.println(isiFile.size()+"\n");
            int jumlahData = isiFile.size();
            boolean cek = false;
            for (int j = 0; j < jumlahData; j++) {
                if (isiFile.get(j).toString().startsWith("Label")) {
                    cek = false;
                }
                if (isiFile.get(j).toString().startsWith("Label: METODE")) {
                    temp.add("METODE");
                    cek = true;
                } else if (isiFile.get(j).toString().startsWith("Label: DATA")) {
                    temp.add("DATA");
                    cek = true;
                } else if (isiFile.get(j).toString().startsWith("Label: PROBLEM")) {
                    temp.add("PROBLEM");
                    cek = true;
                } else if (isiFile.get(j).toString().startsWith("Label: HASIL")) {
                    temp.add("HASIL");
                    cek = true;
                } else if (cek) {
                    temp.add(isiFile.get(j));
                }
            }

            int no = 0;
            String s = ("");
            for (int j = 0; j < temp.size(); j++) {
                cek = false;
                if (temp.get(j).toString().startsWith("METODE")) {
                    if (s.length() != 0) {
                        temp1.addElement(s);
                        s = "";
                    }
                    cek = true;
                    temp1.addElement("METODE");
                    no++;
                }
                if (temp.get(j).toString().startsWith("DATA")) {
                    if (s.length() != 0) {
                        temp1.addElement(s);
                        s = "";
                    }
                    cek = true;
                    temp1.addElement("DATA");
                    no++;
                }
                if (temp.get(j).toString().startsWith("PROBLEM")) {
                    if (s.length() != 0) {
                        temp1.addElement(s);
                        s = "";
                    }
                    cek = true;
                    temp1.addElement("PROBLEM");
                    no++;
                }
                if (temp.get(j).toString().startsWith("HASIL")) {
                    if (s.length() != 0) {
                        temp1.addElement(s);
                        s = "";
                    }
                    cek = true;
                    temp1.addElement("HASIL");
                    no++;
                }
                if (!cek) {

                    s = s + temp.get(j).toString();
                }

                //System.out.println(i + " = " + isiFile.get(i));                           
            }
            temp1.addElement(s);
            no++;
            no = -1;
            for (int j = 0; j < temp1.size(); j++) {
                if (temp1.get(j).toString().startsWith(label.get(0).toString())) {
                    no = 0;
                }
                if (temp1.get(j).toString().startsWith(label.get(1).toString())) {
                    no = 1;
                }
                if (temp1.get(j).toString().startsWith(label.get(2).toString())) {
                    no = 2;
                }
                if (temp1.get(j).toString().startsWith(label.get(3).toString())) {
                    no = 3;
                }

                if (no >= 0) {

                    hasil[no].addElement(temp1.get(j + 1));

                }
                no = -1;
            }
            String namaFolder = foldKategori + "/" + isiDir[i].getName().substring(0, 7);
            mkDir(namaFolder);

            // System.out.print(fold+"/"+isiDir[i].getName().substring(0, 7));
            for (int j = 0; j < label.size(); j++) {
                // System.out.print(namaFolder+"/"+label.get(j).toString() + ".txt");
                berkas.tulisFileTxt(namaFolder + "/" + label.get(j).toString() + ".txt", hasil[j]);
            }
        }

    }

    public void kataKunci() {

        mkDir(foldKataKunci);
        // ********************* Baca StopList
        Vector vStopList = new Vector();
        Berkas fileStoplist = new Berkas();
        vStopList = fileStoplist.bacaFileTxt("stopList.txt");

        File fileKategori = new File(foldKategori);
        File[] isiDir = fileKategori.listFiles();
        for (int i = 0; i < isiDir.length; i++) {

            String namaFolder = foldKataKunci + "/" + isiDir[i].getName().substring(0, 7);
            mkDir(namaFolder);

            // System.out.print("\n"+isiDir[i].toString()+"\n");
            File isiFolder = new File(isiDir[i].toString());
            File[] isiFol = isiFolder.listFiles();
            for (int j = 0; j < isiFol.length; j++) {
                // System.out.print(isiFol[j].toString());
                File isiDlmFolder = new File(isiDir[i].toString());
                File[] isiDlmFol = isiDlmFolder.listFiles();

                Vector isiFileKategori = new Vector();
                Berkas berkas = new Berkas();

                // for (int k = 0; k < isiDlmFol.length; k++) {
                //********************* Membaca File
                String nFileK = isiDlmFol[j].toString();
                isiFileKategori = berkas.bacaFileTxt(nFileK);

                //System.out.print(nFileK + "\n");
                nFileK = isiDlmFol[j].getName();

                //********************** Tokenizing         
                String kalToken = isiFileKategori.toString();
                Vector vIsi = new Vector();
                Tokenizing tokenFile = new Tokenizing();
                //vIsi = tokenFile.methodTokenizer(kalToken, " ,\t,\n,,,.,(,),<,>,?,&,',\",!,%,[,],â,€,™,:,0,1,2,3,4,5,6,7,8,9"); // parameter:text,Pemisah 
                vIsi = tokenFile.methodTokenizer(kalToken, " ,\t,\n,,,.,(,),<,>,?,&,',\",!,%,[,],â,€,™,:,0,1,2,3,4,5,6,7,8,9,–,-,/,=,•,;,’,“,”"); // parameter:text,Pemisah 

// System.out.println(vIsi); 
                //Deteksi isi
                // ******************** Bandingkan dengan stoplist
                int x = 0;
                Vector vWordList = new Vector(); // untuk menampung hasil filtering 
                for (int l = 0; l < vIsi.size(); l++) {
                    for (int m = 0; m < vStopList.size(); m++) {
                        if (!vIsi.elementAt(l).toString().equalsIgnoreCase(vStopList.elementAt(m).toString())) {
                            x++; //comment di sini
                            if (x == vStopList.size()) {  // jika panjang x = size stoplist maka tidak ada dlm stoplist
                                vWordList.addElement(vIsi.elementAt(l));
                            }
                        }
                    }
                    x = 0;
                }

                //******************* Hitung kata yang sama
                int jk;
                Vector vJmlKata = new Vector();         //jumlah kata
                for (int l = 0; l < vWordList.size(); l++) {
                    jk = 1;
                    for (int m = l + 1; m < vWordList.size(); m++) {
                        if (!vWordList.get(l).toString().equalsIgnoreCase("XXXXX")) {
                            if (vWordList.get(l).toString().equalsIgnoreCase(vWordList.get(m).toString())) {
                                jk++;
                                vWordList.set(m, "XXXXX");
                            }
                        }
                    }
                    if (!vWordList.get(l).toString().equalsIgnoreCase("XXXXX")) {
                        vJmlKata.addElement(jk);
                    }
                }
                for (int l = vWordList.size() - 1; l >= 0; l--) {            //Dibalik biar tak pengaruh size
                    if (vWordList.get(l).toString().equalsIgnoreCase("XXXXX")) {
                        vWordList.remove(l);
                    }
                }
                // diurutkan desending  menggunakan insertion sort
//            berkas.tulisFileTxt(label.get(h).toString() + "Temp1Kata.txt", vWordList);
//            berkas.tulisFileTxt(label.get(h).toString() + "Temp1Jumlah.txt", vJmlKata);
                for (int l = 1; l < vJmlKata.size(); l++) {
                    int valueToSort = Integer.parseInt(vJmlKata.get(l).toString());
                    String ikut = vWordList.get(l).toString();
                    int m = l;
                    while (m > 0 && Integer.parseInt(vJmlKata.get(m - 1).toString()) < valueToSort) {
                        vJmlKata.set(m, vJmlKata.get(m - 1));
                        vWordList.set(m, vWordList.get(m - 1));
                        m--;
                    }
                    vJmlKata.set(m, valueToSort);
                    vWordList.set(m, ikut);
                }

                Vector gabung = new Vector();
                for (int l = 0; l < vWordList.size(); l++) {
                    gabung.addElement(vWordList.get(l).toString().toLowerCase() + "," + vJmlKata.get(l).toString());
                }

                berkas.tulisFileTxt(namaFolder + "/" + nFileK, gabung);
                //}
            }
            //System.out.print(isiDir[i].toString());
        }

    }

    private void mkDir(String nDir) {
        //Buat forder untuk file katakunci
        try {
            boolean ok = (new File(nDir).mkdir());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
