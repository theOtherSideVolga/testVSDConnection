package test;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import net.nuagenetworks.bambou.RestSession;
import net.nuagenetworks.bambou.Impersonator;
import net.nuagenetworks.bambou.RestException;
import net.nuagenetworks.bambou.BulkResponse;
import net.nuagenetworks.vspk.v6.Enterprise;
import net.nuagenetworks.vspk.v6.Group;
import net.nuagenetworks.vspk.v6.WebCategory;
import net.nuagenetworks.vspk.v6.WebDomainName;
import net.nuagenetworks.vspk.v6.User;
import net.nuagenetworks.vspk.v6.DomainTemplate;
import net.nuagenetworks.vspk.v6.VSDSession;
import net.nuagenetworks.vspk.v6.fetchers.EnterprisesFetcher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import net.nuagenetworks.bambou.operation.RestSessionOperations;


@Configuration
@ComponentScan("net.nuagenetworks")
public class FetchAllEnterprises {// implements CommandLineRunner {
    private static final String MY_VSD_SERVER_PORT = "https://10.213.92.5:8443";
    private static VSDSession session;

    public static void main(String[] args) {
        try {
            runthis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void runthis() throws Exception {
        System.out.println("Fetching All Enterprises");
        session = new VSDSession("app07409robotic", "btmpls123", "csp", MY_VSD_SERVER_PORT);
        session.getClientTemplate().disableCertificateValidation();
        session.start();

        EnterprisesFetcher fetcher = session.getMe().getEnterprises();
        List<Enterprise> enterprises = fetcher.get();
        System.out.println("Number of Enterprises found : " + enterprises.size());
        for (Enterprise enterprise : enterprises) {
            System.out.println("Found Enterprise " + enterprise.getName() + " " + enterprise.getDescription());
        }

    }
}

