package hr.tvz.java.tjedan02.nogomet.sucelja;

public interface KapacitetObjekta extends Opisivo {
    String getKapacitetZaIspis();

    @Override
    default String getOpis(){
       return (getKapacitetZaIspis());
    };
}
