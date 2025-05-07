import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Services extends Remote{

    public int Add(int a,int b,int c)throws RemoteException;
}