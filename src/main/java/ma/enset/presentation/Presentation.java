package ma.enset.presentation;

import ma.enset.DAO.DaoImpl;
import ma.enset.services.MetierImpl;

/**
 *  Static Dependancy Injection methode <br/>
 *  Achraf HAMMI
 */

public class Presentation {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();

        metier.setDao(dao);
        System.out.println(metier.calcul());
    }
}
