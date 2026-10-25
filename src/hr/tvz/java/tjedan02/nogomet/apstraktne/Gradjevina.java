package hr.tvz.java.tjedan02.nogomet.apstraktne;

import hr.tvz.java.tjedan02.nogomet.sucelja.KapacitetObjekta;
import hr.tvz.java.tjedan02.nogomet.zapisi.Adresa;

public abstract class Gradjevina implements KapacitetObjekta {
    private Adresa adresa;
    private String naziv;

    protected Gradjevina(Adresa adresa, String naziv) {
        this.adresa = adresa;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }
}
