package hr.tvz.java.tjedan01.arena2;

public class Arena {
    public static void main(String[] args) {

        Lik[] likovi = new Lik[2];
        likovi[0] = new Ratnik("Borna", 30, 10);
        likovi[1] = new Carobnjak("Grga", 25, 50);

        for (Lik lik : likovi) {
            System.out.println(lik);
        }

        likovi[1].napadni(likovi[0]);
        likovi[0].napadni(likovi[1]);
        likovi[1].napadni(likovi[0]);
        likovi[1].napadni(likovi[0]);
        likovi[0].napadni(likovi[1]);

        System.out.println("--- Stanje nakon borbe ---");
        for (Lik lik : likovi) {
            System.out.println(lik);
            if (lik instanceof Carobnjak c) {
                System.out.println(c.getIme() + " ima jos " + c.getMana() + " mane.");
            }
        }


    }
}