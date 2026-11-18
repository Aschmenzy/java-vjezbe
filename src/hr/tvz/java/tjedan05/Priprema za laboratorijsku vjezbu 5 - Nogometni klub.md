# Priprema za laboratorijsku vježbu 5 – Nogometni klub

## Cilj pripreme

Nadogradite **postojeću Java konzolnu aplikaciju za evidenciju nogometnih klubova** iz prethodnih laboratorijskih priprema. Izrađujete samo tražene promjene; **sve prethodne funkcionalnosti i pravila koja ovdje nisu izmijenjena i dalje vrijede**.

Naglasak je na primjeni **generičkog programiranja (Generics)**:

- izrada i uporaba vlastite generičke klase s ograničenim parametrom tipa `<T extends Osoba>`
- parametrizirani tipovi i sigurnost tipova pri kompilaciji (*type safety*)
- uporaba generičke evidencije za novu vrstu osoba u nogometnom klubu
- izrada generičke metode s vlastitim ograničenim parametrom tipa `<U extends Osoba>`
- korištenje operatora `<>` (*diamond operator*)
- uporaba zamjenskih tipova (*wildcards*) `?` i `? extends ...`
- razumijevanje ograničenja generičkih tipova i izbjegavanje *raw types*

**Preduvjet:** funkcionalna aplikacija iz četvrte pripreme, uključujući klasu `ExceptionHandler`, vlastitu iznimku `KlubNijePronadenException` i ispravan unos svih podataka. Za izradu i pokretanje aplikacije koristi se **Java 25**.

**Promjena u odnosu na prethodne pripreme:** dosadašnje ograničenje na uporabu isključivo običnih nizova **više ne vrijedi za novu klasu `KlupskaEvidencija<T>`**. Ona mora interno koristiti `ArrayList<T>` i u ovom se zadatku koristi **samo za novu klasu `Navijac`**. Igrači ostaju u običnom nizu `Igrac[]`, građevine u `Gradjevina[]`, a klubovi u `NogometniKlub[]`. Te nizove nije potrebno pretvarati u generičke evidencije.

Za pitanja vezana uz pripremu javiti se na **mario.seider@tvz.hr**, uz **marko.tomic@tvz.hr** u CC-u.

---

## 1. Polazno stanje i novi zadatak

U postojećoj verziji aplikacije `NogometniKlub` sadrži podatke o klubu, trenera, niz igrača `Igrac[]` i niz građevina `Gradjevina[]`. Građevine mogu biti `Stadion`, `MedicinskaOrdinacija` ili `SportskiDom`. Program omogućuje siguran unos, pretraživanje kluba po nazivu i ispis podataka.

U ovoj pripremi potrebno je:

1. Uvesti novu klasu **`Navijac`**, koja poput klase `Igrac` nasljeđuje apstraktnu klasu `Osoba`.
2. Izraditi **generičku klasu `KlupskaEvidencija<T extends Osoba>`** za pohranu i dohvat objekata koji nasljeđuju klasu `Osoba`.
3. U klasi `NogometniKlub` dodati evidenciju navijača:

```java
private KlupskaEvidencija<Navijac> navijaci;
```

Generička klasa ograničena je na tip `Osoba` i njezine podklase, ali **u ovoj aplikaciji upotrebljava se samo za navijače**. Postojeći `Igrac[] igraci` i `Gradjevina[] gradjevine` ostaju nepromijenjeni.

### Novi odnos među klasama

```text
Osoba (abstract)
├── Igrac
├── Trener
└── Navijac                             // NOVO

NogometniKlub
├── Trener trener
├── Igrac[] igraci                       // BEZ PROMJENE
├── KlupskaEvidencija<Navijac> navijaci // NOVO
└── Gradjevina[] gradjevine              // BEZ PROMJENE
    ├── Stadion
    ├── MedicinskaOrdinacija
    └── SportskiDom

KlupskaEvidencija<T extends Osoba>
└── List<T> elementi (implementacija: ArrayList<T>)
```

**Važno:** `T extends Osoba` ograničava dopuštene tipove elemenata, ali `KlupskaEvidencija<T>` time **ne nasljeđuje** klasu `Osoba`. Ona je zasebna generička klasa.

---

## 2. Organizacija projekta

Zadržite sve postojeće pakete i klase. U paketu `hr.tvz.nogomet.klase` dodajte `Navijac.java`, a u novom paketu `hr.tvz.nogomet.genericki` izradite `KlupskaEvidencija.java`.

```text
src/hr/tvz/nogomet/
├── aplikacija/
│   └── Main.java                        // IZMJENA
├── apstraktne/
│   ├── Osoba.java
│   └── Gradjevina.java
├── klase/
│   ├── NogometniKlub.java              // IZMJENA
│   ├── Igrac.java
│   ├── Trener.java
│   ├── Navijac.java                    // NOVO
│   ├── Stadion.java
│   ├── MedicinskaOrdinacija.java
│   └── SportskiDom.java
├── genericki/
│   └── KlupskaEvidencija.java           // NOVO
├── sucelja/
│   ├── Opisivo.java
│   └── KapacitetObjekta.java
├── enumeracije/
│   └── Pozicija.java
├── zapisi/
│   └── Adresa.java
├── pomocne/
│   └── ExceptionHandler.java
└── iznimke/
    └── KlubNijePronadenException.java
```

Dodajte odgovarajuće deklaracije `package` i naredbe `import`. **Nemojte mijenjati postojeću hijerarhiju građevina** ni nepotrebno duplicirati atribute klase `Osoba`.

---

## 3. Nova klasa `Navijac`

U paketu `hr.tvz.nogomet.klase` izradite javnu klasu `Navijac` koja **nasljeđuje `Osoba`**. Navijač predstavlja osobu koja prati klub i bodri ga na utakmicama. Klub može imati proizvoljan broj navijača, uključujući i nijednog.

Klasa mora sadržavati vlastite atribute:

```java
private int brojPosjecenihUtakmica;
private int glasnocaNavijanja;
```

Atribut `glasnocaNavijanja` predstavlja ocjenu glasnoće od `1` (jedva čujno navijanje) do `10` (čuje se do susjednog stadiona).

**Zadaci:**

1. Izradite konstruktor koji prima podatke osobe (ime, prezime i godinu rođenja) te `brojPosjecenihUtakmica` i `glasnocaNavijanja`.
2. Za inicijalizaciju podataka naslijeđenih iz `Osoba` koristite poziv `super(...)`, prilagođen postojećem konstruktoru klase `Osoba`.
3. Izradite odgovarajuće get/set metode za vlastite atribute.
4. Ne duplicirajte atribute `ime`, `prezime` i `godinaRodjenja` ako već postoje u klasi `Osoba`.

### Pravila unosa navijača

Za svakog navijača korisnik unosi:

| Podatak | Pravilo |
| --- | --- |
| Ime | Neprazan tekst. |
| Prezime | Neprazan tekst. |
| Godina rođenja | Cijeli broj od `1` do trenutačne kalendarske godine. |
| Broj posjećenih utakmica | Cijeli broj `0` ili veći. |
| Glasnoća navijanja | Cijeli broj od `1` do `10`. |

**Sve navedene vrijednosti potrebno je unositi putem postojećih metoda klase `ExceptionHandler`.** Neispravan unos mora ponavljati samo trenutačno polje, kao u prethodnoj pripremi.

---

## 4. Nova generička klasa `KlupskaEvidencija<T extends Osoba>`

U paketu `hr.tvz.nogomet.genericki` izradite javnu generičku klasu:

```java
public class KlupskaEvidencija<T extends Osoba> {
    // Implementacija prema zahtjevima u nastavku.
}
```

`T` je **parametar tipa ograničen klasom `Osoba`**. Kao argument tipa dopuštena je klasa `Osoba` ili njezina podklasa (npr. `Navijac`), ali ne i nepovezani tipovi poput `String`. Konkretni tip određuje se pri stvaranju objekta, npr. `KlupskaEvidencija<Navijac>`.

### 4.1. Atribut i konstruktor

Klasa mora imati jedan atribut:

```java
private final List<T> elementi;
```

Koristite sučelje `List<T>` i implementaciju `ArrayList<T>` iz paketa `java.util`. Izradite **javni konstruktor bez parametara** koji inicijalizira praznu listu odgovarajućeg tipa.

**Ne koristiti** `List<Object>`, sirovi tip `List` ili obični niz `Object[]` kao zamjenu za `List<T>`.

### 4.2. Obvezne metode

Implementirajte sljedeće metode:

```java
public void dodaj(T element);
public T dohvati(int indeks);
public int brojElemenata();
public boolean jePrazna();
```

| Metoda | Zahtijevano ponašanje |
| --- | --- |
| `dodaj(T element)` | Dodaje element u evidenciju. Ako je `element == null`, baca `IllegalArgumentException` s razumljivom porukom. |
| `dohvati(int indeks)` | Vraća element na zadanom indeksu, počevši od `0`. Za indeks izvan raspona dopušteno je da `ArrayList` baci `IndexOutOfBoundsException`. |
| `brojElemenata()` | Vraća trenutačan broj pohranjenih elemenata. |
| `jePrazna()` | Vraća `true` ako u evidenciji nema elemenata, inače `false`. |

Za implementaciju koristite postojeće metode kolekcije `ArrayList` (`add`, `get`, `size`, `isEmpty`). **Metode klase pišu se koristeći tip `T`, bez provjera radi li se o navijaču.**

### 4.3. Primjer uporabe i sigurnosti tipova

```java
// Pretpostavka: objekt navijac već je stvoren.
KlupskaEvidencija<Navijac> evidencijaNavijaca = new KlupskaEvidencija<>();
evidencijaNavijaca.dodaj(navijac);

Navijac prviNavijac = evidencijaNavijaca.dohvati(0); // bez castinga

// Neispravno: String nije Navijac.
// evidencijaNavijaca.dodaj("Uljez"); // greška pri kompilaciji
```

Zakomentirana naredba služi samo demonstraciji sigurnosti tipova. Nije potrebno u program dodavati naredbu koja uzrokuje pogrešku pri kompilaciji.

---

## 5. Generička metoda `izNiza` – pretvorba niza navijača

Unutar klase `KlupskaEvidencija<T>` napravite **javnu statičku generičku metodu**:

```java
public static <U extends Osoba> KlupskaEvidencija<U> izNiza(U[] niz)
```

Metoda mora:

1. Primiti običan niz objekata tipa `U` koji je `Osoba` ili njezina podklasa.
2. Napraviti novu praznu `KlupskaEvidencija<U>`.
3. Proći kroz elemente primljenog niza i svaki dodati pozivom `dodaj`.
4. Vratiti stvorenu evidenciju.
5. Za `null` umjesto niza baciti `IllegalArgumentException`; za prazan niz vratiti praznu evidenciju.

### Primjer uporabe

```java
// Pretpostavka: niz Navijac[] navijaci već je popunjen tijekom unosa.
KlupskaEvidencija<Navijac> evidencijaNavijaca =
        KlupskaEvidencija.izNiza(navijaci);
```

U ovom se primjeru `U` automatski zaključuje iz tipa niza. **`<U extends Osoba>` pripada generičkoj metodi**, dok `<T extends Osoba>` pripada generičkoj klasi. Metoda `izNiza` treba biti generička iako se u ovoj aplikaciji poziva samo za `Navijac[]`. **Niz `Igrac[]` ne pretvara se u generičku evidenciju.**

**Napomena:** u Javi se zbog brisanja tipova (*type erasure*) ne može jednostavno napraviti `new T[10]`. U ovom zadatku to nije potrebno: podaci se pohranjuju u `ArrayList<T>`, a metoda `izNiza` prima već postojeći niz.

---

## 6. Prilagodba klasa `NogometniKlub` i `Main`

### 6.1. Izmjene u klasi `NogometniKlub`

**Postojeći atribut igrača ostaje nepromijenjen:**

```java
private Igrac[] igraci;
```

Dodajte novi atribut:

```java
private KlupskaEvidencija<Navijac> navijaci;
```

**Atribut `Gradjevina[] gradjevine` također ostaje nepromijenjen.** Zadržite i postojeći atribut trenera.

Prilagodite konstruktor i get/set metode kako bi klub mogao primiti i pohraniti evidenciju navijača. Evidencija mora postojati čak i kada klub ima `0` navijača: tada je prazna, ali **nije `null`**.

U postojećoj metodi `getOpis()`:

1. **Zadržite dosadašnji ispis igrača koristeći `Igrac[]`**, bez zamjene postojećeg načina prolaska kroz niz.
2. Dodajte dio ispisa za sve navijače: ime i prezime, godinu rođenja, broj posjećenih utakmica i glasnoću navijanja.
3. Za prolazak kroz evidenciju navijača koristite metode `brojElemenata()` i `dohvati(i)`.
4. Zadržite sve dosadašnje podatke o treneru, igračima i građevinama.

Primjer prolaska kroz novu generičku evidenciju:

```java
for (int i = 0; i < navijaci.brojElemenata(); i++) {
    Navijac navijac = navijaci.dohvati(i);
    // Ispisati podatke o navijaču.
}
```

**Ne mijenjajte** postojeću metodu `getUkupanBrojKreveta()`: ona i dalje prolazi kroz `Gradjevina[]` i zbraja krevete sportskih domova pomoću `instanceof`. Kapacitet građevina i dalje se dohvaća polimorfno pozivom `getKapacitetZaIspis()`.

### 6.2. Izmjene u klasi `Main`

Cjelokupan unos i njegova validacija ostaju u klasi `ExceptionHandler`, prema ranijim pripremama.

Prilikom unosa pojedinog kluba:

1. Unesite postojeće podatke o klubu, treneru i igračima kao do sada. **Igrači se i dalje pohranjuju u `Igrac[]`.**
2. Zatražite **broj navijača** (cijeli broj `0` ili veći).
3. Za svakog navijača unesite podatke iz poglavlja 3 i stvorite objekt klase `Navijac`.
4. Navijače privremeno pohranite u `Navijac[]` pa nakon unosa pozovite `KlupskaEvidencija.izNiza(...)` kako biste dobili `KlupskaEvidencija<Navijac>`.
5. Predajte postojeći `Igrac[]`, novu generičku evidenciju navijača i ostale podatke konstruktoru klase `NogometniKlub`.
6. Unos građevina i njihova pohrana u `Gradjevina[]` ostaju kao do sada.

**Bez promjena ostaju:** niz `NogometniKlub[]` sa svim klubovima, metoda `pronadiKlub(NogometniKlub[] klubovi, String naziv)` s deklaracijom `throws KlubNijePronadenException`, izbornik s opcijama `1` i `0`, obrada neuspješne pretrage, jedan `Scanner` te njegovo zatvaranje u `finally`.

---

## 7. Zamjenski tip `?` (*unbounded wildcard*)

U klasi `Main` napravite pomoćnu statičku metodu:

```java
private static void ispisiBrojElemenata(
        KlupskaEvidencija<?> evidencija)
```

Metoda mora ispisati tekst `Broj navijača: ` i broj elemenata evidencije, primjerice:

```text
Broj navijača: 2
```

Pozovite je **nakon ispisa pronađenog kluba** za njegovu evidenciju navijača, bez dodatnog parametra za naziv.

**Objašnjenje:** `KlupskaEvidencija<?>` označava evidenciju nepoznatog konkretnog tipa koji mora zadovoljavati ograničenje `extends Osoba` iz deklaracije klase. Metoda ne mora znati o kojoj je podklasi riječ jer koristi samo `brojElemenata()`. Njezin potpis nije vezan uz klasu `Navijac`, iako se ovdje poziva samo s evidencijom navijača.

---

## 8. Ograničeni tip `? extends Osoba` (*upper bounded wildcard*)

U klasi `Main` napravite još jednu pomoćnu statičku metodu:

```java
private static void ispisiImenaOsoba(
        KlupskaEvidencija<? extends Osoba> evidencija)
```

Metoda mora:

1. Ispisati naslov `Popis imena navijača:`.
2. Proći kroz sve elemente evidencije.
3. Svaki element dohvatiti kao objekt tipa `Osoba` i ispisati njegovo ime i prezime koristeći naslijeđene get metode.
4. Ako je evidencija prazna, ispisati `Nema unesenih osoba.`.

Pozovite metodu **nakon ispisa broja navijača**, za evidenciju `KlupskaEvidencija<Navijac>`, bez dodatnog parametra za naslov.

### Primjer očekivanog ispisa

```text
Popis imena navijača:
Luka Kovač
Marija Babić
```

`? extends Osoba` izričito naglašava da metoda čita elemente kao objekte tipa `Osoba`. Budući da sama klasa `KlupskaEvidencija<T extends Osoba>` već ima to ograničenje, i `KlupskaEvidencija<?>` ovdje bi prihvaćala iste dopuštene tipove evidencija. Ista bi metoda mogla raditi i s drugim generičkim evidencijama podklasa `Osoba`, ali **u ovoj pripremi nije potrebno stvarati takve evidencije**.

**Važno:** `KlupskaEvidencija<Navijac>` nije podtip klase `KlupskaEvidencija<Osoba>`. U potpisu metode `? extends Osoba` izričito navodi dopuštenu hijerarhiju tipova; u ovoj klasi isto ograničenje već proizlazi iz deklaracije `<T extends Osoba>`. Metoda služi **čitanju** elemenata; nemojte pokušavati dodavati nove objekte kroz taj parametar.

Ova metoda služi kao dodatni sažeti ispis i **ne zamjenjuje** detaljan ispis navijača u `NogometniKlub.getOpis()`.

---

## 9. Primjer rada programa nakon nadogradnje

Sljedeći primjer prikazuje skraćeni unos jednog kluba s dva igrača, dva navijača i jednim stadionom. Podaci o treneru, igračima, stadionu i adresi unose se **na potpuno isti način kao u prethodnim pripremama**; u primjeru su pojedini koraci izostavljeni samo radi preglednosti.

```text
Koliko klubova želite unijeti: 1

===== UNOS KLUBA 1 =====
Naziv kluba: Hajduk
Grad: Split
Godina osnivanja: 1911

[Unos trenera kao u prethodnoj pripremi]

Koliko igrača želite unijeti: 2
[Unos igrača Marko Marić i Ivan Perić kao u prethodnoj pripremi]

Koliko navijača želite unijeti: 2
--- NAVIJAČ 1 ---
Ime: Luka
Prezime: Kovač
Godina rođenja: 1995
Broj posjećenih utakmica: 24
Glasnoća navijanja (1-10): 10

--- NAVIJAČ 2 ---
Ime: Marija
Prezime: Babić
Godina rođenja: 2001
Broj posjećenih utakmica: 12
Glasnoća navijanja (1-10): 7

Koliko građevina želite unijeti: 1
[Unos stadiona kao u prethodnoj pripremi]

===== NOGOMETNI KLUBOVI =====
1 - Pretraži klub
0 - Završi program
Odabir: 1
Unesite naziv kluba: hajduk

===== HAJDUK =====
Grad: Split
Godina osnivanja: 1911
[Postojeći ispis trenera i oba igrača iz Igrac[]]

Navijači:
1. Luka Kovač, 1995., 24 posjećene utakmice, glasnoća 10/10
2. Marija Babić, 2001., 12 posjećenih utakmica, glasnoća 7/10

[Postojeći detaljan ispis stadiona i njegove adrese]
Ukupan broj kreveta: 0

Broj navijača: 2
Popis imena navijača:
Luka Kovač
Marija Babić

===== NOGOMETNI KLUBOVI =====
1 - Pretraži klub
0 - Završi program
Odabir: 0
Program je završio.
```

Redoslijed i oblikovanje **postojećeg** detaljnog ispisa mogu se razlikovati, ali svi podaci iz prethodnih priprema moraju ostati dostupni. Novi podaci o navijačima te dodatni ispisi (broj navijača i sažeti popis imena) moraju biti vidljivi nakon uspješne pretrage.

Prikazani tekstovi u uglatim zagradama označavaju **izostavljene, ali obvezne postojeće korake**, a ne doslovni ispis programa. Ako klub nema navijača, evidencija mora biti prazna, broj navijača mora biti `0`, a sažetak mora prikazati `Nema unesenih osoba.`.
