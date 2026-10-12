package hr.tvz.java.tjedan01.pripremalabos1;

public class NogometniKlub {
    private String naziv;
    private String grad;
    private int godinaOsnivanja;
    private Stadion stadion;
    private Trener trener;
    private Igrac[] igraci;

    public NogometniKlub(String naziv, String grad, int godinaOsnivanja, Stadion stadion, Trener trener, Igrac[] igraci) {
        this.naziv = naziv;
        this.grad = grad;
        this.godinaOsnivanja = godinaOsnivanja;
        this.stadion = stadion;
        this.trener = trener;
        this.igraci = igraci;
    }


    //getteri
    public String getNaziv() {return this.naziv;}
    public String getGrad() {return this.grad;}
    public int getGodinaOsnivanja() {return this.godinaOsnivanja;}
    public Stadion getStadion() {return this.stadion;}
    public Trener getTrener() {return this.trener;}
    public Igrac[] getIgraci() {return this.igraci;}

    public String getOpis(){
        return  "===== " + naziv.toUpperCase() + " =====" + "\n" +
                "Grad: " + grad + " \n" +
                "Godina osnivanja: " + godinaOsnivanja + "\n" +
                "Trener: " + trener.getIme() + " "+ trener.getPrezime() + "\n" +
                "Stadion: " + stadion.getNaziv();
    }

    //setteri
    public void setNaziv(String naziv) {this.naziv = naziv;}
    public void setGrad(String grad) {this.grad = grad;}
    public void setGodinaOsnivanja(int godinaOsnivanja) {this.godinaOsnivanja = godinaOsnivanja;}
    public void setStadion(Stadion stadion) {this.stadion = stadion;}
    public void setTrener(Trener trener) {this.trener = trener;}
    public void setIgraci(Igrac[] igraci) {this.igraci = igraci;}


}
