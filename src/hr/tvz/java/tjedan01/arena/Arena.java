package hr.tvz.java.tjedan01.arena;

public class Arena {
    public static void main(String[] args) {
        Lik borna = new Lik("Borna", 30);
        Lik grga = new Lik("Grga", 45);

        System.out.println(borna);
        System.out.println(grga);

        borna.napadni(grga);
        grga.napadni(borna);

        borna.napadni(grga);
        borna.napadni(grga);
        borna.napadni(grga);


        System.out.println(borna);
        System.out.println(grga);
        grga.napadni(borna);

        System.out.println("Je li " + grga.getIme() + " ziv? " + grga.jeZiv());

    }
}
