package tp1_server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		}
		
		try {
			AnimalImpl objAnimal = new AnimalImpl();
			CollectionAnimalImpl objCollection = new CollectionAnimalImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			if (registry == null) {
				System.err.println("RMIRegistry not found !");
			} else {
				registry.bind("animal", objAnimal);
				registry.bind("collection", objCollection);
				System.err.println("ServerCV ready !");
			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
