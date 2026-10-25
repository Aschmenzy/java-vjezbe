package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Osoba;
import hr.tvz.java.tjedan02.nogomet.enumeracije.Pozicija;

public class Igrac extends Osoba {
    private int brojDresa;
    private Pozicija pozicija;

    public Igrac(String ime, String prezime, int godinaRodenja, int brojDresa, Pozicija pozicija) {
        super(ime, prezime, godinaRodenja);
        this.brojDresa = brojDresa;
        this.pozicija = pozicija;
    }

    //getteri
    public int getBrojDresa() {
        return brojDresa;
    }

    public Pozicija getPozicija() {
        return pozicija;
    }

    //setteri
    public void setBrojDresa(int brojDresa) {
        this.brojDresa = brojDresa;
    }

    public void setPozicija(Pozicija pozicija) {
        this.pozicija = pozicija;
    }
}
