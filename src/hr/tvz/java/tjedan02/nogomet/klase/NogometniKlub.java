package hr.tvz.java.tjedan02.nogomet.klase;

import hr.tvz.java.tjedan02.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan02.nogomet.sucelja.Opisivo;

public class NogometniKlub implements Opisivo {
    private String naziv;
    private String grad;
    private int godinaOsnivanja;
    private Gradjevina[] gradjevine;
    private Trener trener;
    private Igrac[] igraci;

    public NogometniKlub(String naziv, String grad, int godinaOsnivanja, Gradjevina[] gradjevina, Trener trener, Igrac[] igraci) {
        this.naziv = naziv;
        this.grad = grad;
        this.godinaOsnivanja = godinaOsnivanja;
        this.gradjevine = gradjevina;
        this.trener = trener;
        this.igraci = igraci;
    }


    //getteri
    public String getNaziv() {return this.naziv;}
    public String getGrad() {return this.grad;}
    public int getGodinaOsnivanja() {return this.godinaOsnivanja;}
    public Gradjevina[] getGradjevine() {
        return gradjevine;
    }
    public Trener getTrener() {return this.trener;}
    public Igrac[] getIgraci() {return this.igraci;}

    @Override
    public String getOpis(){
        String kapacitet = "";
        for(Gradjevina g : gradjevine){
          kapacitet +=  g.getKapacitetZaIspis() + "\n";
        }
        return  "===== " + naziv.toUpperCase() + " =====" + "\n" +
                "Grad: " + grad + " \n" +
                "Godina osnivanja: " + godinaOsnivanja + "\n" +
                "Trener: " + trener.getIme() + " "+ trener.getPrezime() + "\n" +
                kapacitet;

    }

    //setteri
    public void setNaziv(String naziv) {this.naziv = naziv;}
    public void setGrad(String grad) {this.grad = grad;}
    public void setGodinaOsnivanja(int godinaOsnivanja) {this.godinaOsnivanja = godinaOsnivanja;}
    public void setTrener(Trener trener) {this.trener = trener;}
    public void setIgraci(Igrac[] igraci) {this.igraci = igraci;}
    public void setGradjevine(Gradjevina[] gradjevine) {
        this.gradjevine = gradjevine;
    }


}
