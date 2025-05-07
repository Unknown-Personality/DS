import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServicesImpl extends UnicastRemoteObject implements Services{
    protected ServicesImpl()throws RemoteException{
        super();
    }

    public int Add(int a,int b,int c)throws RemoteException{
        return a+b+c;
    }
}
