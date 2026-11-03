package hr.tvz.java.tjedan03.nogomet.klase;

import hr.tvz.java.tjedan03.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan03.nogomet.zapisi.Adresa;

public class Stadion extends Gradjevina {
    private Integer kapacitet;
    private Integer godinaIzgradnje;
    private Boolean natkriven;

    public Stadion(Adresa adresa, String naziv) {
        super(adresa, naziv);
    }

    public Stadion(Adresa adresa, String naziv, Integer kapacitet, Integer godinaIzgradnje, Boolean natkriven) {
        super(adresa, naziv);
        this.kapacitet = kapacitet;
        this.godinaIzgradnje = godinaIzgradnje;
        this.natkriven = natkriven;
    }

    @Override
    public String getKapacitetZaIspis() {
        if (kapacitet == null) {
            return "Kapacitet: nije unesen";
        }
        return "Kapacitet: " + kapacitet + " gledatelja";
    }

    @Override
    public String getVrsta() {
        return "Stadion";
    }

    @Override
    public String getPosebniPodaci() {
        return "   Godina izgradnje: " + godinaIzgradnje + "\n"
                + "   Natkriven: " + natkriven + "\n";
    }


    //getteri

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
