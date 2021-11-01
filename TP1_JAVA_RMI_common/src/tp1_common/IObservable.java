package tp1_common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservable extends Remote {
	
	void addObserver(IObserver o) throws RemoteException;
	
}
