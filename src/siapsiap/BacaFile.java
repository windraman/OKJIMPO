package siapsiap;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosyid
 */
public class BacaFile {

    public BacaFile() { // constructor
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
}
