import AdderApp.Adder;
import AdderApp.AdderHelper;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;

public class Server {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            AdderImpl adderImpl = new AdderImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(adderImpl);
            Adder href = AdderHelper.narrow(ref);

            // Naming service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            ncRef.rebind(ncRef.to_name("Adder"), href);

            System.out.println("CORBA Server ready...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
