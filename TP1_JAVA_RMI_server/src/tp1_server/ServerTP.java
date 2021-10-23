package tp1_server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("java.security.policy", "server.policy/");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
//		System.setProperty("java.rmi.server.codebase", "../../../TP1_JAVA_RMI_client/bin/");
		System.setProperty("java.rmi.server.codebase", "file:///Users/rongsunying/TP1_JAVA_RMI-workspace/TP1_JAVA_RMI_client/bin/");

		try {
//			AnimalImpl objAnimal = new AnimalImpl();
			CabinetImpl objCabinet = new CabinetImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			if (registry == null) {
				System.err.println("RMIRegistry not found !");
			} else {
//				registry.bind("animal", objAnimal);
				registry.bind("cabinet", objCabinet);
				System.err.println("ServerCV ready !");
			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
