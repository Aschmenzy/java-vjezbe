package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan02.nogomet.zapisi.Adresa;

public class Stadion extends Gradjevina {
    private String naziv;
    private Integer kapacitet;
    private Integer godinaIzgradnje;
    private Boolean natkriven;

    public Stadion(Adresa adresa, String naziv, String naziv1) {
        super(adresa, naziv);
        this.naziv = naziv1;
    }

    @Override
    public String getKapacitetZaIspis() {
        if (kapacitet == null) {
            return "Kapacitet: nije unesen";
        }
        return "Kapacitet: " + kapacitet + " gledatelja";
    }


    //getteri
    public String getNaziv() {
        return naziv;
    }

    public Integer getKapacitet() {
        return kapacitet;
    }

    public Integer getGodinaIzgradnje() {
        return godinaIzgradnje;
    }

    public Boolean getNatkriven() {
        return natkriven;
    }

    //setteri
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }

    public void setGodinaIzgradnje(Integer godinaIzgradnje) {
        this.godinaIzgradnje = godinaIzgradnje;
    }

    public void setNatkriven(Boolean natkriven) {
        this.natkriven = natkriven;
    }

}
