# Priprema za laboratorijsku vježbu 3 – Nogometni klub

## Cilj pripreme

Nadogradite **postojeću Java konzolnu aplikaciju za evidenciju nogometnih klubova** iz prve i druge pripreme. Izrađujete samo tražene promjene; **sve prethodne funkcionalnosti i pravila koja ovdje nisu izmijenjena i dalje vrijede**.

Naglasak je na obradi iznimki (*exceptions*) i sigurnom korisničkom unosu:

- uporaba blokova `try`, `catch` i `finally`
- prepoznavanje i obrada iznimki `NumberFormatException` i `IllegalArgumentException`
- bacanje iznimki naredbom `throw` i prosljeđivanje pomoću `throws`
- izrada i korištenje vlastite (*custom*) iznimke
- ponavljanje **samo neispravnog unosa**, bez prekidanja programa
- izdvajanje cjelokupnog unosa i njegove provjere u klasu `ExceptionHandler`

**Preduvjet:** funkcionalna aplikacija iz druge pripreme. I dalje koristite **obične nizove** (`NogometniKlub[]`, `Igrac[]`, `Gradjevina[]`), bez `ArrayList` i drugih kolekcija. Za izradu i pokretanje aplikacije koristi se **Java 25**.

Za pitanja vezana uz pripremu javiti se na **mario.seider@tvz.hr**, uz **marko.tomic@tvz.hr** u CC-u.

---

## 1. Polazno stanje i zadatak

U drugoj pripremi izrađen je program koji omogućuje unos više nogometnih klubova, njihovih trenera i igrača te građevina (`Stadion`, `MedicinskaOrdinacija`, `SportskiDom`). Program nakon unosa prikazuje izbornik i omogućuje pretraživanje kluba po nazivu.

Trenutačno neispravan unos, primjerice slova umjesto godine rođenja ili broj koji ne predstavlja postojeću poziciju igrača, može prouzročiti pogrešku ili neispravno stanje programa. **U ovoj pripremi potrebno je osigurati svaki pojedini korisnički unos.**

Nakon nadogradnje vrijedi sljedeće:

1. Za svaki podatak program provjerava je li unos dopušten.
2. Ako nije, ispisuje razumljivu poruku i ponavlja unos **tog istog podatka**.
3. Prethodno ispravno uneseni podaci ostaju sačuvani.
4. Neuspješna pretraga kluba obrađuje se vlastitom iznimkom.
5. Program se nakon pogreške vraća u normalan tijek rada; ne završava zbog običnog pogrešnog unosa.

## 2. Organizacija projekta

Zadržite postojeće pakete i klase iz druge pripreme. Dodajte sljedeće dvije datoteke:

```text
src/hr/tvz/nogomet/
├── aplikacija/
│   └── Main.java
├── pomocne/
│   └── ExceptionHandler.java          // NOVO
└── iznimke/
    └── KlubNijePronadenException.java // NOVO
```

Svi dosadašnji paketi (`apstraktne`, `klase`, `sucelja`, `enumeracije`, `zapisi`) i njihove klase ostaju u projektu. **Naziv klase mora biti `ExceptionHandler`** (s ispravnim redoslijedom slova).

Klasa `Main` upravlja redoslijedom unosa, stvaranjem objekata, pretragom i izbornikom. Klasa `ExceptionHandler` zadužena je za **čitanje, provjeru i ponavljanje svih korisničkih unosa**.

## 3. Klasa `ExceptionHandler` – središnje mjesto za unos

U paketu `hr.tvz.nogomet.pomocne` izradite klasu `ExceptionHandler`.

Klasa mora koristiti **jedan postojeći objekt `Scanner`**, koji se stvara u klasi `Main` i predaje konstruktoru. Nemojte stvarati novi `Scanner` za svaki podatak ili unutar pojedinih metoda.

```java
public ExceptionHandler(Scanner scanner)
```

### 3.1. Obvezne metode

U klasi implementirajte sljedeće metode (moguće su dodatne pomoćne privatne metode):

```java
public String unesiObavezanTekst(String poruka);
public int unesiCijeliBroj(String poruka, int min, int max);
public Integer unesiOpcionalniCijeliBroj(String poruka, int min, int max);
public boolean unesiBoolean(String poruka);
public Boolean unesiOpcionalniBoolean(String poruka);
public Pozicija unesiPoziciju(String poruka);
public int unesiOdabir(String poruka, int min, int max);
```

**Ponašanje metoda:**

- `unesiObavezanTekst`: prihvaća tekst koji nakon uklanjanja vodećih i završnih razmaka nije prazan; vraća očišćeni tekst.
- `unesiCijeliBroj`: pokušava pretvoriti korisnički unos u `int` i dopušta samo brojeve u zadanom uključivom rasponu (`min`–`max`).
- `unesiOpcionalniCijeliBroj`: praznim unosom vraća `null`; za neprazan unos vrijede ista pravila kao kod obveznog cijelog broja.
- `unesiBoolean`: prihvaća isključivo `true` ili `false`, bez obzira na veličinu slova.
- `unesiOpcionalniBoolean`: prazan unos vraća `null`; inače prihvaća samo `true` ili `false`.
- `unesiPoziciju`: prikazuje mogućnosti `1 – VRATAR`, `2 – BRANIC`, `3 – VEZNJAK`, `4 – NAPADAC` i vraća odgovarajuću vrijednost tipa `Pozicija`, a ne `String` ili `int`.
- `unesiOdabir`: prima samo cijeli broj u zadanom rasponu; koristi se za izbornik i odabir vrste građevine.

**Zahtjev:** svaka metoda mora ponavljati unos dok ne dobije valjanu vrijednost. Metode moraju **vratiti rezultat**, a ne samo ispisati poruku. Kod neispravnog unosa ne smiju se vratiti proizvoljne zamjenske vrijednosti poput `0` ili `false`.

### 3.2. Kako obraditi iznimke

Za brojčane unose koristite `Integer.parseInt()` nad tekstom dohvaćenim metodom `scanner.nextLine()`. Pretvorbu i provjeru raspona smjestite u `try` blok, a nepravilnosti obradite u `catch` blokovima.

**Primjer obrasca za brojčani unos** (prikazuje način rada; ostale metode izradite samostalno):

```java
while (true) {
    System.out.print(poruka);
    try {
        int broj = Integer.parseInt(scanner.nextLine().trim());
        if (broj < min || broj > max) {
            throw new IllegalArgumentException(
                "Broj mora biti između " + min + " i " + max + "."
            );
        }
        return broj;
    } catch (NumberFormatException e) {
        System.out.println("Pogreška: potrebno je unijeti cijeli broj.");
    } catch (IllegalArgumentException e) {
        System.out.println("Pogreška: " + e.getMessage());
    }
}
```

`NumberFormatException` nasljeđuje `IllegalArgumentException`, pa se **specifičniji `catch` mora nalaziti prvi**. Samo hvatanje iznimke nije dovoljno: nakon pogreške mora se omogućiti ponovni unos.

Za `boolean` vrijednosti **nemojte bez provjere koristiti `Boolean.parseBoolean()`**: svaki tekst različit od `true` ta metoda pretvara u `false`, iako korisnik možda nije unio valjanu vrijednost. Najprije provjerite tekst, a zatim ga pretvorite.

## 4. Validacija svih podataka u aplikaciji

Sve podatke koje korisnik upisuje u `Main` potrebno je dohvatiti pozivanjem metoda iz `ExceptionHandler`. Pravila u nastavku obvezna su i za **svaki ponovljeni unos** kluba, trenera, igrača ili građevine.

### 4.1. Klub i upravljanje unosom

| Podatak | Pravilo |
| --- | --- |
| Broj klubova | Cijeli broj, najmanje `1`. |
| Naziv kluba | Neprazan tekst. |
| Grad kluba | Neprazan tekst. |
| Godina osnivanja | Cijeli broj od `1` do trenutačne kalendarske godine. |
| Broj igrača | Cijeli broj `0` ili veći. |
| Broj građevina | Cijeli broj `0` ili veći; nema ograničenja broja pojedine vrste. |

### 4.2. Trener i igrači

| Podatak | Pravilo |
| --- | --- |
| Ime i prezime trenera | Svako polje zasebno: neprazan tekst. |
| Godina rođenja trenera | Cijeli broj od `1` do trenutačne godine. |
| Godine iskustva trenera | Cijeli broj od `0` do izračunate starosti trenera (`trenutačna godina – godina rođenja`). |
| Ime i prezime igrača | Svako polje zasebno: neprazan tekst. |
| Godina rođenja igrača | Cijeli broj od `1` do trenutačne godine. |
| Broj dresa | Cijeli broj od `1` do `99`. |
| Pozicija igrača | Samo ponuđeni odabiri `1`, `2`, `3`, `4`; rezultat je `Pozicija` (`enum`). |

### 4.3. Sve građevine i adrese

| Podatak | Pravilo |
| --- | --- |
| Vrsta građevine | Samo `1 – Stadion`, `2 – MedicinskaOrdinacija`, `3 – SportskiDom`. |
| Naziv građevine | Neprazan tekst. |
| Ulica, kućni broj i grad (`Adresa`) | Svaka komponenta zasebno: neprazan tekst. Kućni broj ostaje `String` (npr. `12A`). |
| `Stadion.kapacitet` (`Integer`) | Prazno = `null`; inače cijeli broj veći od `0`. |
| `Stadion.godinaIzgradnje` (`Integer`) | Prazno = `null`; inače cijeli broj od `1` do trenutačne godine. |
| `Stadion.natkriven` (`Boolean`) | Prazno = `null`; inače samo `true` ili `false`. |
| `MedicinskaOrdinacija.brojAmbulanti` | Cijeli broj veći od `0`. |
| `MedicinskaOrdinacija.imaHitnuSluzbu` | Obvezno `true` ili `false`. |
| `SportskiDom.brojKreveta` | Cijeli broj veći od `0`. |
| `SportskiDom.imaBlagovaonicu` | Obvezno `true` ili `false`. |

**Napomena:** tri navedena atributa stadiona imaju tipove `Integer` i `Boolean` iz prethodnih priprema. Prazan unos za njih **nije pogreška**, nego znači da podatak nije unesen (`null`). Ostali tekstualni atributi ne smiju biti prazni. Sve provjere trebaju ostati u `ExceptionHandler`, a ne se kopirati u više dijelova klase `Main`.

### 4.4. Izbornik i pretraga

- Glavni izbornik prihvaća samo `0` (završetak) i `1` (pretraga).
- Za pretragu se mora unijeti neprazan naziv kluba.
- Ako je odabir izbornika neispravan (slova, negativan broj, `2`, prazno), program ponavlja samo odabir.
- Ako traženi klub ne postoji, **ne traži se ponovni unos u beskonačnoj petlji**: prikazuje se poruka i vraća glavni izbornik, kao u prethodnim pripremama.

## 5. Vlastita iznimka – `KlubNijePronadenException`

U paketu `hr.tvz.nogomet.iznimke` napravite klasu:

```java
public class KlubNijePronadenException extends Exception {
    public KlubNijePronadenException(String nazivKluba) {
        super("Klub '" + nazivKluba + "' nije pronađen.");
    }
}
```

Ovo je **provjeravana iznimka** (*checked exception*), jer nasljeđuje `Exception`. Metoda koja je može baciti mora je obraditi ili navesti u deklaraciji pomoću `throws`.

### 5.1. Izmjena pretraživanja klubova

U klasi `Main` izdvojite pretraživanje u metodu sljedećeg potpisa:

```java
private static NogometniKlub pronadiKlub(
    NogometniKlub[] klubovi, String naziv
) throws KlubNijePronadenException
```

Metoda mora:

1. Proći kroz postojeći niz `NogometniKlub[]`.
2. Uspoređivati nazive bez razlikovanja velikih i malih slova (`equalsIgnoreCase`).
3. Vratiti pronađeni objekt `NogometniKlub`.
4. Ako klub nije pronađen, **baciti** `KlubNijePronadenException` naredbom `throw`. Ne smije samo vratiti `null` i prepustiti pozivatelju da zaključuje što se dogodilo.

### 5.2. Obrada iznimke pri pozivanju

U dijelu izbornika za pretragu pozovite `pronadiKlub(...)` unutar bloka `try`. Ako je klub pronađen, ispišite `getOpis()`. Iznimku `KlubNijePronadenException` uhvatite zasebnim `catch` blokom i ispišite poruku iz `getMessage()`. **Nakon toga ponovno prikažite glavni izbornik.**

Nije potrebno mijenjati metodu `NogometniKlub.getOpis()` ni način prikazivanja građevina i ukupnog broja kreveta iz druge pripreme.

## 6. Obvezna uporaba bloka `finally`

U klasi `Main` napravite **jedan** `Scanner` za `System.in` na početku izvođenja programa. Glavni unos podataka i petlju izbornika smjestite unutar vanjskog bloka `try`, a u pripadajućem bloku `finally` zatvorite taj `Scanner`.

```java
Scanner scanner = new Scanner(System.in);
try {
    ExceptionHandler unos = new ExceptionHandler(scanner);
    // Unos svih klubova i prikaz glavnog izbornika.
    // Program završava kada korisnik odabere 0.
} finally {
    scanner.close();
    System.out.println("Program je završio.");
}
```

Blok `finally` izvršava se pri izlasku iz `try` bloka, uključujući i situaciju kada iznimka prekine njegovo izvođenje. **Nemojte zatvarati `Scanner` u metodama klase `ExceptionHandler`** ni nakon svakog pojedinačnog unosa, jer će se isti objekt koristiti do završetka programa. Za završetak izbornika koristite uobičajenu kontrolu petlje, a ne `System.exit()`.

Za uobičajene pogreške pri unosu `finally` nije zamjena za `catch`: korisnik i dalje mora imati mogućnost ispraviti nevaljanu vrijednost.

## 7. Primjer očekivanog ponašanja

Sljedeći primjeri prikazuju samo dijelove rada programa. Ispis može biti drukčije oblikovan, ali pogreška mora biti jasna, a ponovni unos usmjeren na isti podatak.

### 7.1. Ponovljeni unos podataka o treneru i igraču

```text
Ime trenera: 
Pogreška: tekst ne smije biti prazan.
Ime trenera: Ivan
Prezime trenera: Horvat
Godina rođenja trenera: abc
Pogreška: potrebno je unijeti cijeli broj.
Godina rođenja trenera: 1975
Godine iskustva: -2
Pogreška: vrijednost nije u dopuštenom rasponu.
Godine iskustva: 15

--- IGRAČ 1 ---
Broj dresa: deset
Pogreška: potrebno je unijeti cijeli broj.
Broj dresa: 10
Pozicija (1 - VRATAR, 2 - BRANIC, 3 - VEZNJAK, 4 - NAPADAC): 7
Pogreška: odaberite jednu od ponuđenih vrijednosti.
Pozicija (1 - VRATAR, 2 - BRANIC, 3 - VEZNJAK, 4 - NAPADAC): 3
```

### 7.2. Opcionalni podaci stadiona

```text
Kapacitet (Enter = nije unesen): xyz
Pogreška: potrebno je unijeti cijeli broj.
Kapacitet (Enter = nije unesen):
Godina izgradnje (Enter = nije unesena): 1979
Natkriven (true/false, Enter = nije uneseno): možda
Pogreška: unesite true ili false.
Natkriven (true/false, Enter = nije uneseno): false
```

U ovom primjeru `kapacitet` ostaje `null`, dok `godinaIzgradnje` iznosi `1979`, a `natkriven` je `false`. Postojeći `getKapacitetZaIspis()` zato za stadion mora i dalje vratiti `Kapacitet: nije unesen`.

### 7.3. Neuspješna pretraga i povratak u izbornik

```text
===== NOGOMETNI KLUBOVI =====
1 - Pretraži klub
0 - Završi program
Odabir: 5
Pogreška: odaberite 0 ili 1.
Odabir: 1
Unesite naziv kluba: Barcelona
Klub 'Barcelona' nije pronađen.

===== NOGOMETNI KLUBOVI =====
1 - Pretraži klub
0 - Završi program
Odabir: 0
Program je završio.
```

## 8. Završni zahtjevi

- Zadržane su klase, sučelja, `enum`, `record`, nasljeđivanje, polimorfizam i postojeći ispisi iz druge pripreme.
- **Svaki korisnički unos** obrađuje se preko metoda klase `ExceptionHandler`, uključujući glavni izbornik.
- Neispravne vrijednosti uzrokuju jasnu poruku i ponovni unos istog podatka.
- Pravilno se koriste `try`, `catch`, `throw`, `throws` i `finally`.
- Vlastita `KlubNijePronadenException` baca se pri neuspješnoj pretrazi i obrađuje bez prekida programa.
- Sve tri opcionalne vrijednosti stadiona podržavaju `null`.
- Koriste se isključivo obični nizovi, bez kolekcija.
- Program se uspješno kompajlira, pokreće i može demonstrirati na laboratorijskoj vježbi.

**Na laboratorijsku vježbu potrebno je doći s dovršenom nadogradnjom aplikacije iz druge pripreme.**
