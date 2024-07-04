package siapsiap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosyid
 */
public class Berkas {

    public Berkas() { // constructor
    }

    public Vector bacaFileTxt(String namaTxt) { // method dengan parameter nama file

        java.io.FileReader frSI = null;
        Vector vecTampung = new Vector();
        try {
            frSI = new java.io.FileReader(namaTxt); // baca file txt

            java.io.BufferedReader br = new java.io.BufferedReader(frSI);
            while (true) {
                java.lang.String kalimat = br.readLine();   // baca per line dari txt
                if (kalimat == null) {  // jika uda habis barisnya, break
                    break;
                } else {
                    vecTampung.addElement(kalimat); // jika kata masih ada, tampung di vecTampung
                }
            }
        } catch (IOException ex) {      //exception handling
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        } finally {
            try {
                frSI.close();
            } catch (IOException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
        return (vecTampung);
    }

    public void tulisFileTxt(String namaTxt, Vector txtData) {
        try {
            FileWriter fwSI = new java.io.FileWriter(namaTxt);
            BufferedWriter bw = new BufferedWriter(fwSI);

            for (int i = 0; i < txtData.size(); i++) {
                
                    bw.write(txtData.get(i).toString());
                    bw.write('\r');
                    bw.write('\n');
            }
            //bw.write(vAllJmlData.toString());
            
            bw.close();

        } catch (IOException ex) {      //exception handling
            Logger.getLogger("global").log(Level.SEVERE, null, ex);
        }

    }

}
