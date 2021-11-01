package tp1_common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
	
	void update(Object o) throws RemoteException;

}
