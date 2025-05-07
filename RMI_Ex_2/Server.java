import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server{
    public static void main(String[] args){
        try{
            ServicesImpl ser=new ServicesImpl();
            Registry res=LocateRegistry.getRegistry();
            res.rebind("Services",ser);
            System.out.println("Server Started....!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}