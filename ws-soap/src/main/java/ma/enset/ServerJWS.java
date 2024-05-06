package ma.enset;

import jakarta.xml.ws.Endpoint;
import ma.enset.ws.BanqueService;

public class ServerJWS {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9090/banqueWS", new BanqueService());
        System.out.println("web is deployed");
    }
}
