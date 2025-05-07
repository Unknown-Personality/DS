import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client{
    public static void main(String[] args){
        try{
            Registry reg=LocateRegistry.getRegistry("localhost");
            Services stub=(Services)reg.lookup("Services");
            System.out.println(stub.Add(10,20,30));
        }catch(Exception e){

        }
    }
}