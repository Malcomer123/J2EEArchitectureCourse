package ma.enset.presentation;

import ma.enset.DAO.IDao;
import ma.enset.services.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Dynamic Dependancy Injection
 */

public class Presentation2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/config.txt"));
        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getConstructor().newInstance();

        String metierClassName = scanner.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getConstructor().newInstance();
        Method m = cMetier.getMethod("setDao", IDao.class);
        m.invoke(metier, dao);

        System.out.println("data: " + metier.calcul());
    }
}
