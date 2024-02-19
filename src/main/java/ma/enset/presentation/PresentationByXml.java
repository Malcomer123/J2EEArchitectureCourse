package ma.enset.presentation;

import ma.enset.services.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * work in progress: Achraf HAMMI
 */

public class PresentationByXml {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-ioc.xml");
        IMetier metier= (IMetier) context.getBean("Metier");
        System.out.println(metier.calcul());
    }
}
