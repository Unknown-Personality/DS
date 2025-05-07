import AdderApp.Adder;
import AdderApp.AdderHelper;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class Client {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            Adder adder = AdderHelper.narrow(ncRef.resolve_str("Adder"));
            long result = adder.add(50, 70);

            System.out.println("Result from CORBA Server: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
