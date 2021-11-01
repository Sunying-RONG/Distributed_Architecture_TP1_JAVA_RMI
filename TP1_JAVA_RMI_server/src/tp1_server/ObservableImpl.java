package tp1_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import tp1_common.IObservable;
import tp1_common.IObserver;

public class ObservableImpl extends UnicastRemoteObject implements IObservable {
	
//	private static final long serialVersionUID = 1L;

    protected ObservableImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addObserver(IObserver o) throws RemoteException {
		ObserverUpdater.addIObserver(o);
	}

}
