package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan02.nogomet.zapisi.Adresa;

public class MedicinskaOrdinacija extends Gradjevina {
    private int brojAmbulanti;

    public MedicinskaOrdinacija(Adresa adresa, String naziv, int brojAmbulanti) {
        super(adresa, naziv);
        this.brojAmbulanti = brojAmbulanti;
    }

    @Override
    public String getKapacitetZaIspis(){
        return "Broj ambulanti: " + brojAmbulanti;
    };

}
