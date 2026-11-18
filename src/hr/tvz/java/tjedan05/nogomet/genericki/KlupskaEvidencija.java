package hr.tvz.java.tjedan05.nogomet.genericki;


import hr.tvz.java.tjedan05.nogomet.apstraktne.Osoba;

import java.util.ArrayList;
import java.util.List;

public class KlupskaEvidencija<T extends Osoba> {
    private final List<T> elementi;

    public KlupskaEvidencija() {
        this.elementi = new ArrayList<>();
    }

    public static <U extends Osoba> KlupskaEvidencija<U> izNiza(U[] niz) {
        if (niz == null) {
            throw new IllegalArgumentException("Niz ne smije biti null.");
        }

        KlupskaEvidencija<U> evidencija = new KlupskaEvidencija<>();

        for (U objekt : niz) {
            evidencija.dodaj(objekt);
        }
        return evidencija;
    }

    public void dodaj(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element ne smije biti null.");
        }
        elementi.add(element);
    }

    public T dohvati(int indeks) {
        return elementi.get(indeks);
    }

    public int brojElemenata() {
        return elementi.size();
    }

    public boolean jePrazna() {
        return elementi.isEmpty();
    }
}
