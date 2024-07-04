package siapsiap;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author rosyid
 */
public class Tokenizing {

    Vector v;   // instant variable

    public Tokenizing() {   // constructor
    }

    public Vector methodTokenizer(String kalimat, String token) // metode (tipenya Vector)  dengan parameter "kalimat yang mau ditokenizing" dan "tokenizingnya berdasarkan apa"
    {
        StringTokenizer st = new StringTokenizer(kalimat, token);   // instant object dari kelas StringTokenizer yang uda disediakan java
        v = new Vector();   // bikin object Vektor v

        while (st.hasMoreTokens()) // selama masih ada token, tambahkan element pada vektor v
        {
            v.addElement(st.nextToken());
        }

        return v;
    }
}
