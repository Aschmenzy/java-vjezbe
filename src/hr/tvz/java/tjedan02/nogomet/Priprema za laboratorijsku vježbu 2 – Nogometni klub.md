# Priprema za laboratorijsku vježbu 2 – Nogometni klub

## Cilj pripreme

Nadogradite **postojeću Java konzolnu aplikaciju za evidenciju nogometnih klubova** iz prve pripreme. Izrađujete samo tražene promjene; sve prethodne funkcionalnosti i pravila koja ovdje nisu izmijenjena i dalje vrijede.

Naglasak je na primjeni sljedećih koncepata:

- apstraktne klase i nasljeđivanje
- sučelja (`interface`), njihova implementacija i nasljeđivanje jednog sučelja iz drugog
- polimorfizam
- modifikator pristupa `protected`
- nabrajanja (`enum`)
- zapisi (`record`)
- operator `instanceof`
- paketi (`package`) i organizacija Java projekta
- prilagodba postojećeg korisničkog unosa i ispisa novom modelu

**Preduvjet:** funkcionalna aplikacija iz prve pripreme. I dalje koristite **obične nizove**, bez `ArrayList` i drugih kolekcija. Program se mora uspješno kompajlirati i pokrenuti prije laboratorijske vježbe.

Za izradu i pokretanje aplikacije koristi se **Java 25**.

Za pitanja vezana uz pripremu javiti se na **mario.seider@tvz.hr**, uz **marko.tomic@tvz.hr** u CC-u.

---

## 1. Polazno stanje i nova struktura aplikacije

U prethodnoj pripremi postojale su klase `NogometniKlub`, `Osoba`, `Igrac`, `Trener` i `Stadion`.

Sada je potrebno uvesti nove tipove i izmijeniti postojeće odnose među njima:

- `Osoba` postaje **apstraktna klasa**, koju nasljeđuju `Igrac` i `Trener`.
- Uvodi se **apstraktna klasa `Gradjevina`**, koju nasljeđuju `Stadion`, `MedicinskaOrdinacija` i `SportskiDom`.
- Uvodi se sučelje **`Opisivo`** s deklaracijom metode `getOpis()`. Klasa `NogometniKlub` implementira to sučelje.
- Sučelje **`KapacitetObjekta` nasljeđuje (`extends`) sučelje `Opisivo`** i dodaje metodu `getKapacitetZaIspis()`.
- `Gradjevina` implementira `KapacitetObjekta`, a njezine konkretne podklase daju vlastitu implementaciju metode `getKapacitetZaIspis()`.
- Dosadašnje tekstualne konstante za pozicije igrača zamjenjuju se tipom `Pozicija` (`enum`).
- Uvodi se zapis `Adresa` (`record`), kojim se predstavlja adresa građevine.
- `NogometniKlub` umjesto jednog atributa `Stadion stadion` sadrži **niz `Gradjevina[] gradjevine`**.

### Povezanost tipova

```text
Osoba (abstract)
├── Igrac
└── Trener

NogometniKlub ── implements ──> Opisivo (interface)
KapacitetObjekta (interface) ── extends ──> Opisivo (interface)
Gradjevina (abstract) ── implements ──> KapacitetObjekta (interface)
├── Stadion
├── MedicinskaOrdinacija
└── SportskiDom

Igrac ── koristi ──> Pozicija (enum)
Gradjevina ── ima ──> Adresa (record)

NogometniKlub
├── Trener trener
├── Igrac[] igraci
└── Gradjevina[] gradjevine
```

---

## 2. Refaktoriranje projekta – organizacija u pakete

Postojeće i nove tipove potrebno je rasporediti u odgovarajuće pakete prema sljedećoj strukturi:

```text
src/
└── hr/
    └── tvz/
        └── nogomet/
            ├── aplikacija/
            │   └── Main.java
            ├── apstraktne/
            │   ├── Osoba.java
            │   └── Gradjevina.java
            ├── klase/
            │   ├── NogometniKlub.java
            │   ├── Igrac.java
            │   ├── Trener.java
            │   ├── Stadion.java
            │   ├── MedicinskaOrdinacija.java
            │   └── SportskiDom.java
            ├── sucelja/
            │   ├── Opisivo.java
            │   └── KapacitetObjekta.java
            ├── enumeracije/
            │   └── Pozicija.java
            └── zapisi/
                └── Adresa.java
```

**Zadaci:**

1. Premjestiti postojeće klase u pripadajuće pakete.
2. Dodati odgovarajuće deklaracije `package` i potrebne `import` naredbe.
3. Dodati nove Java datoteke prema zadanoj strukturi.
4. Provjeriti da projekt nema klase u neimenovanom (_default_) paketu.
5. Izbjegavati dupliciranje zajedničkih atributa i metoda u podklasama.

Nazive klasa, paketa i metoda potrebno je dosljedno koristiti u cijelom projektu.

---

## 3. Apstraktna klasa `Osoba`

Postojeću klasu `Osoba` pretvorite u **apstraktnu klasu**. Zadržite njezine postojeće atribute, konstruktor i get/set metode. Klase `Igrac` i `Trener` i dalje je nasljeđuju. Klasa `Osoba` ne smije se izravno instancirati.

---

## 4. `enum Pozicija` – promjena u klasi `Igrac`

Zamijenite dosadašnje tekstualne konstante nabrajanjem `Pozicija` s vrijednostima `VRATAR`, `BRANIC`, `VEZNJAK` i `NAPADAC`.

U klasi `Igrac` zamijenite `String pozicija` atributom `Pozicija pozicija` i prilagodite konstruktor, get/set metode te korisnički unos. Odabrana pozicija mora se pohraniti kao vrijednost enuma, a ne kao `String`.

---

## 5. Nova apstraktna klasa `Gradjevina`

Izradite apstraktnu klasu `Gradjevina`, koja predstavlja zajedničku osnovu svih građevina povezanih s nogometnim klubom.

Klasa mora sadržavati:

```java
private String naziv;
private Adresa adresa;
```

Potrebno je:

1. Napraviti konstruktor koji postavlja naziv i adresu.
2. Napraviti odgovarajuće get/set metode za navedene atribute.
3. Deklarirati da `Gradjevina` implementira sučelje `KapacitetObjekta`.
4. Omogućiti da `Stadion`, `MedicinskaOrdinacija` i `SportskiDom` naslijede zajedničke podatke, bez dupliciranja.

### Modifikator `protected`

Konstruktor klase `Gradjevina` mora imati modifikator pristupa **`protected`**. Podklase ga pozivaju pri inicijalizaciji svojih naslijeđenih podataka. Ostali atributi ostaju `private`.

Klasa `Gradjevina` ne sadrži vlastitu metodu `getOpis()`. Način na koji građevine nasljeđuju ponašanje vezano uz opis definiran je sučeljima u sljedećem poglavlju.

---

## 6. Sučelja `Opisivo` i `KapacitetObjekta`

Izradite dva sučelja u paketu `sucelja`.

### 6.1. Sučelje `Opisivo`

Sučelje `Opisivo` mora deklarirati metodu:

```java
String getOpis();
```

Klasa `NogometniKlub` mora implementirati `Opisivo` i **zadržati postojeću metodu `getOpis()` iz prve pripreme**, uz prilagodbu njezina sadržaja novim podacima. Deklaracija metode nalazi se u sučelju, a implementacija u klasi `NogometniKlub` označava se anotacijom `@Override`.

### 6.2. Sučelje `KapacitetObjekta`

Sučelje `KapacitetObjekta` mora **nasljeđivati sučelje `Opisivo`** pomoću `extends`. Dodatno mora deklarirati metodu:

```java
String getKapacitetZaIspis();
```

Budući da nasljeđuje `Opisivo`, sučelje `KapacitetObjekta` također preuzima ugovor za `getOpis()`.

Da se metoda `getOpis()` ne bi morala dodavati u klase građevina, u sučelju `KapacitetObjekta` potrebno je osigurati **`default` implementaciju metode `getOpis()`**. Ona vraća rezultat dobiven pozivanjem `getKapacitetZaIspis()`.

Na taj način:

- `Opisivo` definira ugovor za dohvat opisa.
- `KapacitetObjekta` nasljeđuje taj ugovor i proširuje ga podatkom o kapacitetu.
- `NogometniKlub` samostalno implementira `getOpis()`.
- Građevine koriste naslijeđenu `default` implementaciju metode `getOpis()` bez pisanja te metode u pojedinim klasama.

### 6.3. Točan format kapaciteta građevina

Klasa `Gradjevina` deklarira `implements KapacitetObjekta`. Svaka konkretna podklasa mora uz `@Override` implementirati `String getKapacitetZaIspis()`.

**Metoda mora vraćati tekst u sljedećem formatu** (umjesto oznake `<broj>` ispisuje se vrijednost atributa konkretnog objekta):

| Klasa | Atribut | Obavezan rezultat metode |
| --- | --- | --- |
| `Stadion` | `kapacitet` | `Kapacitet: <broj> gledatelja` |
| `MedicinskaOrdinacija` | `brojAmbulanti` | `Broj ambulanti: <broj>` |
| `SportskiDom` | `brojKreveta` | `Broj kreveta: <broj>` |

Tekst mora sadržavati **upravo navedenu oznaku, dvotočku, razmak i broj**. Metoda vraća `String`, ne ispisuje ga izravno na konzolu. Za stadion, kojemu je `kapacitet` tipa `Integer`, ako vrijednost nije postavljena (`null`), metoda treba vratiti `Kapacitet: nije unesen`.

U `NogometniKlub.getOpis()` kapacitet svake građevine dohvatite pozivanjem `getKapacitetZaIspis()` nad elementom niza `Gradjevina[]`; **bez `instanceof` ili zasebnih uvjeta za pojedine vrste kapaciteta**.

---

## 7. `record Adresa`

Izradite zapis `Adresa` u paketu `zapisi`.

Zapis treba sadržavati tri komponente:

```java
String ulica
String kucniBroj
String grad
```

Zapis `Adresa` koristi se **u svim građevinama**, preko zajedničkog atributa naslijeđenog iz klase `Gradjevina`. Dosadašnji podatak `grad` u klasi `Stadion` treba prilagoditi novom modelu: grad se dohvaća iz adrese, a ne pohranjuje dodatno u stadionu.

Prilikom unosa svake građevine korisnik unosi ulicu, kućni broj i grad. Ti podaci moraju biti dostupni za prikaz pronađenog kluba.

---

## 8. Konkretne građevine

Sve tri klase nasljeđuju `Gradjevina`, koriste naslijeđene podatke o nazivu i adresi te implementiraju metodu `getKapacitetZaIspis()` iz sučelja `KapacitetObjekta`.

### 8.1. Klasa `Stadion`

Zadržavaju se posebni atributi stadiona iz prethodne pripreme:

```java
private Integer kapacitet;
private Integer godinaIzgradnje;
private Boolean natkriven;
```

Naziv i adresa ne pohranjuju se zasebno u klasi `Stadion`, nego dolaze iz `Gradjevina`.

Prilagodite konstruktor i get/set metode te implementirajte `getKapacitetZaIspis()` koristeći atribut `kapacitet`. Zadržite uporabu navedenih **wrapper** tipova (`Integer`, `Boolean`).

### 8.2. Klasa `MedicinskaOrdinacija`

Klasa mora sadržavati:

```java
private int brojAmbulanti;
private boolean imaHitnuSluzbu;
```

Napravite konstruktor, get/set metode i implementaciju metode `getKapacitetZaIspis()` koja koristi `brojAmbulanti`.

### 8.3. Klasa `SportskiDom`

Klasa mora sadržavati:

```java
private int brojKreveta;
private boolean imaBlagovaonicu;
```

Napravite konstruktor, get/set metode i implementaciju metode `getKapacitetZaIspis()` koja koristi `brojKreveta`.

Nijedna konkretna klasa građevine ne mora zasebno implementirati `getOpis()` jer je odgovarajuća `default` implementacija dostupna preko sučelja `KapacitetObjekta`.

---

## 9. Prilagodba klase `NogometniKlub`

Zadržite postojeće podatke o klubu, treneru i igračima. Atribut `Stadion stadion` zamijenite s:

```java
private Gradjevina[] gradjevine;
```

U isti niz mogu se pohraniti objekti `Stadion`, `MedicinskaOrdinacija` i `SportskiDom`. Prilagodite konstruktor i get/set metode.

`NogometniKlub` mora implementirati `Opisivo`. Postojeću metodu `getOpis()` označite s `@Override` i proširite ispisom svih građevina, njihovih adresa i podataka. Za kapacitet pozovite `getKapacitetZaIspis()`; nemojte ponavljati logiku iz konkretnih podklasa.

---

## 10. Upotreba `instanceof` – ukupan broj kreveta

U klasu `NogometniKlub` dodajte metodu:

```java
public int getUkupanBrojKreveta()
```

Metoda prolazi kroz niz `Gradjevina[]` i zbraja broj kreveta svih objekata tipa `SportskiDom`.

Potrebno je:

1. Provjeriti je li trenutačni element tipa `SportskiDom` pomoću `instanceof`.
2. Ako jest, dohvatiti broj kreveta i dodati ga ukupnom zbroju.
3. Ostale vrste građevina ne uključivati u zbroj.
4. Vratiti ukupni zbroj.

Možete koristiti klasični `instanceof` uz pretvorbu tipa ili `instanceof` s prepoznavanjem tipa u Javi 25.

Ukupan broj kreveta treba biti prikazan u opisu pronađenog kluba, uključujući i situaciju u kojoj klub nema nijedan sportski dom.

`instanceof` se u ovom zadatku koristi za operaciju specifičnu za `SportskiDom`. Za zajednički prikaz kapaciteta koristite **polimorfizam**, a ne provjere tipa.

---

## 11. Unos podataka

Zadržite dosadašnji unos kluba, trenera i igrača. Dodatno prilagodite unos pozicije igrača za `enum Pozicija`, a umjesto jednog stadiona omogućite unos **proizvoljnog broja građevina**. Za svaku se građevinu biraju vrsta, naziv, adresa i posebni atributi pripadajuće klase. Sve se građevine pohranjuju u `Gradjevina[]`. Nema ograničenja broja pojedine vrste građevine.

### Primjer tijeka unosa

Sljedeći primjer prikazuje jedan klub, jednog igrača i četiri građevine. Brojevi i vrijednosti služe samo za prikaz tijeka programa; **ne propisuju koliko objekata student smije unijeti**. Numerički način odabira enuma i vrste građevine također je prikaz mogućeg korisničkog sučelja.

```text
Koliko klubova želite unijeti: 1

===== UNOS KLUBA 1 =====
Naziv kluba: Hajduk
Grad: Split
Godina osnivanja: 1911

Ime trenera: Ivan
Prezime trenera: Horvat
Godina rođenja: 1975
Godine iskustva: 15

Koliko igrača želite unijeti: 1
--- IGRAČ 1 ---
Ime: Marko
Prezime: Marić
Godina rođenja: 1998
Broj dresa: 4
Pozicija (1 - VRATAR, 2 - BRANIC, 3 - VEZNJAK, 4 - NAPADAC): 2

Koliko građevina želite unijeti: 4

--- GRAĐEVINA 1 ---
Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): 1
Naziv: Poljud
Ulica: Ulica Mediteranskih igara
Kućni broj: 2
Grad: Split
Kapacitet: 33000
Godina izgradnje: 1979
Natkriven (true/false): false

--- GRAĐEVINA 2 ---
Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): 2
Naziv: Klupska ordinacija
Ulica: Zrinsko-frankopanska
Kućni broj: 8
Grad: Split
Broj ambulanti: 3
Ima hitnu službu (true/false): true

--- GRAĐEVINA 3 ---
Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): 3
Naziv: Sportski dom A
Ulica: Put Brodarice
Kućni broj: 12
Grad: Split
Broj kreveta: 30
Ima blagovaonicu (true/false): true

--- GRAĐEVINA 4 ---
Vrsta (1 - Stadion, 2 - MedicinskaOrdinacija, 3 - SportskiDom): 3
Naziv: Sportski dom B
Ulica: Put Supavla
Kućni broj: 5
Grad: Split
Broj kreveta: 20
Ima blagovaonicu (true/false): false
```

Kod svakog odabira vrste treba tražiti **samo atribute koji pripadaju odabranoj građevini**, uz zajedničke podatke `naziv` i `Adresa`.

---

## 12. Rad programa nakon unosa

Izbornik i pretraživanje ostaju kao u prvoj pripremi. Pronađeni klub ispisuje se metodom `NogometniKlub.getOpis()`. Uz dosadašnje podatke treba prikazati **sve građevine, njihove adrese, posebne atribute i kapacitete** te ukupan broj kreveta dobiven metodom `getUkupanBrojKreveta()`.

### Primjer očekivanog ispisa

Nastavak unosa iz prethodnog poglavlja:

```text
===== NOGOMETNI KLUBOVI =====
1 - Pretraži klub
0 - Završi program
Odabir: 1

Unesite naziv kluba: hajduk

===== HAJDUK =====
Grad: Split
Godina osnivanja: 1911
Trener: Ivan Horvat

Igrači:
1. Marko Marić, 1998., dres 4, BRANIC

Građevine:

1. Stadion: Poljud
   Adresa: Ulica Mediteranskih igara 2, Split
   Kapacitet: 33000 gledatelja
   Godina izgradnje: 1979
   Natkriven: false

2. Medicinska ordinacija: Klupska ordinacija
   Adresa: Zrinsko-frankopanska 8, Split
   Broj ambulanti: 3
   Ima hitnu službu: true

3. Sportski dom: Sportski dom A
   Adresa: Put Brodarice 12, Split
   Broj kreveta: 30
   Ima blagovaonicu: true

4. Sportski dom: Sportski dom B
   Adresa: Put Supavla 5, Split
   Broj kreveta: 20
   Ima blagovaonicu: false

Ukupan broj kreveta: 50

===== NOGOMETNI KLUBOVI =====
1 - Pretraži klub
0 - Završi program
Odabir: 0
```

Raspored elemenata i vizualno oblikovanje ostalog ispisa mogu se razlikovati, ali **svi traženi podaci moraju biti vidljivi**, a redci za kapacitete moraju odgovarati točnim formatima iz poglavlja 6.3. Zbroj kreveta u ovom primjeru iznosi **50** (30 + 20); ako nema sportskih domova, prikazuje se **0**.

---

**Završni zahtjev:** na laboratorijsku vježbu potrebno je doći s nadograđenom verzijom aplikacije iz prve pripreme koja se može kompajlirati, pokrenuti i demonstrirati kroz korisnički unos i pretraživanje klubova.
