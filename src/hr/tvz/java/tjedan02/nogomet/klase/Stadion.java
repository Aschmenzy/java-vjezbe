package hr.tvz.java.tjedan02.nogomet.klase;

public class Stadion {
    private String naziv;
    private String grad;
    private Integer kapacitet;
    private Integer godinaIzgradnje;
    private Boolean natkriven;

    public Stadion(String naziv, String grad) {
        this.naziv = naziv;
        this.grad = grad;
    }

    //getteri
    public String getNaziv() {return naziv;}
    public String getGrad() {return grad;}
    public Integer getKapacitet() {return kapacitet;}
    public Integer getGodinaIzgradnje() {return godinaIzgradnje;}
    public Boolean getNatkriven() {return natkriven;}

    //setteri
    public void setNaziv(String naziv) {this.naziv = naziv;}
    public void setGrad(String grad) {this.grad = grad;}
    public void setKapacitet(Integer kapacitet) {this.kapacitet = kapacitet;}
    public void setGodinaIzgradnje(Integer godinaIzgradnje) {this.godinaIzgradnje = godinaIzgradnje;}
    public void setNatkriven(Boolean natkriven) {this.natkriven = natkriven;}

}
