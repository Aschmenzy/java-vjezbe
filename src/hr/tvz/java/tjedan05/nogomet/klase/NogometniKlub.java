package hr.tvz.java.tjedan05.nogomet.klase;

import hr.tvz.java.tjedan05.nogomet.apstraktne.Gradjevina;
import hr.tvz.java.tjedan05.nogomet.genericki.KlupskaEvidencija;
import hr.tvz.java.tjedan05.nogomet.sucelja.Opisivo;

public class NogometniKlub implements Opisivo {
    private String naziv;
    private String grad;
    private int godinaOsnivanja;
    private Gradjevina[] gradjevine;
    private Trener trener;
    private Igrac[] igraci;
    private KlupskaEvidencija<Navijac> navijaci;

    public NogometniKlub(String naziv, String grad, int godinaOsnivanja, Gradjevina[] gradjevina, Trener trener, Igrac[] igraci,KlupskaEvidencija<Navijac> navijaci) {
        this.naziv = naziv;
        this.grad = grad;
        this.godinaOsnivanja = godinaOsnivanja;
        this.gradjevine = gradjevina;
        this.trener = trener;
        this.igraci = igraci;
        setNavijaci(navijaci);
    }

    @Override
    public String getOpis() {
        String opis = "===== " + naziv.toUpperCase() + " =====\n";
        opis += "Grad: " + grad + "\n";
        opis += "Godina osnivanja: " + godinaOsnivanja + "\n";
        opis += "Trener: " + trener.getIme() + " " + trener.getPrezime() + "\n";

        opis += "\nIgrači:\n";
        for (int i = 0; i < igraci.length; i++) {
            Igrac ig = igraci[i];
            opis += (i + 1) + ". " + ig.getIme() + " " + ig.getPrezime() + ", "
                    + ig.getGodinaRodenja() + "., dres " + ig.getBrojDresa() + ", " + ig.getPozicija() + "\n";
        }

        opis += "\nNavijači:\n";
        for (int i = 0; i < navijaci.brojElemenata(); i++) {
            Navijac navijac = navijaci.dohvati(i);
            opis += (i + 1) + ". " + navijac.getIme() + " " + navijac.getPrezime() + ", "
                    + navijac.getGodinaRodenja() + "., "
                    + navijac.getBrojPosjecenihUtakmica() + " " + oblikPosjecenihUtakmica(navijac.getBrojPosjecenihUtakmica())
                    + ", glasnoća " + navijac.getGlasnocaNavijanja() + "/10\n";
        }

        opis += "\nGrađevine:\n";
        for (int i = 0; i < gradjevine.length; i++) {
            Gradjevina g = gradjevine[i];

            opis += "\n" + (i + 1) + ". " + g.getVrsta() + ": " + g.getNaziv() + "\n";
            opis += "   Adresa: " + g.getAdresa().ulica() + " " + g.getAdresa().kucniBroj()
                    + ", " + g.getAdresa().grad() + "\n";
            opis += "   " + g.getKapacitetZaIspis() + "\n";
            opis += g.getPosebniPodaci();
        }

        opis += "\nUkupan broj kreveta: " + getUkupanBrojKreveta() + "\n";
        return opis;
    }

    // 1, 21, 31... -> "posjećena utakmica"; 2-4, 22-24... -> "posjećene utakmice"; ostalo -> "posjećenih utakmica"
    private String oblikPosjecenihUtakmica(int broj) {
        int zadnjaZnamenka = broj % 10;
        int zadnjeDvije = broj % 100;

        if (zadnjaZnamenka == 1 && zadnjeDvije != 11) {
            return "posjećena utakmica";
        }
        if (zadnjaZnamenka >= 2 && zadnjaZnamenka <= 4 && (zadnjeDvije < 12 || zadnjeDvije > 14)) {
            return "posjećene utakmice";
        }
        return "posjećenih utakmica";
    }

    public int getUkupanBrojKreveta(){
        int kreveta = 0;
        for (Gradjevina g : gradjevine) {
           if (g instanceof SportskiDom s) kreveta += s.getBrojKreveta();
        }
        return kreveta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public int getGodinaOsnivanja() {
        return godinaOsnivanja;
    }

    public void setGodinaOsnivanja(int godinaOsnivanja) {
        this.godinaOsnivanja = godinaOsnivanja;
    }

    public Gradjevina[] getGradjevine() {
        return gradjevine;
    }

    public void setGradjevine(Gradjevina[] gradjevine) {
        this.gradjevine = gradjevine;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Igrac[] getIgraci() {
        return igraci;
    }

    public void setIgraci(Igrac[] igraci) {
        this.igraci = igraci;
    }

    public KlupskaEvidencija<Navijac> getNavijaci() {
        return navijaci;
    }

    public void setNavijaci(KlupskaEvidencija<Navijac> navijaci) {
        if (navijaci == null) {
            this.navijaci = new KlupskaEvidencija<>();
        } else {
            this.navijaci = navijaci;
        }
    }
}
