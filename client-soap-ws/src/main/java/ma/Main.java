package ma;

import proxy.BanqueService;
import proxy.BanqueWS;
import proxy.Compte;

public class Main {
    public static void main(String[] args) {
        BanqueService banqueService = new BanqueWS().getBanqueServicePort();
        System.out.println(banqueService.conversionEuroToDh(800));
        System.out.println("*******************");
        Compte cp = banqueService.getCompte(1);
        System.out.println(cp.getCode());
        System.out.println(cp.getSolde());
        System.out.println(cp.getDateCreation());
        System.out.println("Liste des comptes");
        banqueService.listComptes().forEach(c -> {
            System.out.println(c.getCode());
            System.out.println(c.getSolde());
            System.out.println(c.getDateCreation());
        });
    }
}