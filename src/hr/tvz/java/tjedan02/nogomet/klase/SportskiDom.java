package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan02.nogomet.zapisi.Adresa;

public class SportskiDom extends Gradjevina {
    private int brojKreveta;

    public SportskiDom(Adresa adresa, String naziv, int brojKreveta) {
        super(adresa, naziv);
        this.brojKreveta = brojKreveta;
    }

    @Override
    public String getKapacitetZaIspis(){
        return "Broj kreveta: " + brojKreveta;
    };

}
