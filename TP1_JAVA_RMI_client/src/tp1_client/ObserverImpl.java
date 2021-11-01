package tp1_client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import tp1_common.IObserver;

public class ObserverImpl extends UnicastRemoteObject implements IObserver {
	
//    private static final long serialVersionUID = 1L;

	protected ObserverImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Object o) throws RemoteException {
		// TODO Auto-generated method stub
        System.out.println("\nFranchir le seuil de " + o + " patients!\n");
	}

}
