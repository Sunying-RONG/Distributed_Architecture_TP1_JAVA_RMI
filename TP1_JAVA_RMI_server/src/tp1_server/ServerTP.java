package tp1_server;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cp = System.getProperty("user.dir");
		Path currentPath = Paths.get(cp);
		Path parentPath = currentPath.getParent();
		String CODEBASE_PATH = "file://"+parentPath.toString()+"/TP1_JAVA_RMI_client/codebaseSharedClasses/";
		System.out.println("codebase path is : \n"+CODEBASE_PATH+"\nPlease verify your local path of 'codebaseSharedClasses' folder. Should be the same as above.");
		
		System.setProperty("java.security.policy", "server.policy/");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
//		System.setProperty("java.rmi.server.codebase", "file:///Users/rongsunying/TP1_JAVA_RMI-workspace/TP1_JAVA_RMI_client/bin/");
//		System.setProperty("java.rmi.server.codebase", "file:///Users/rongsunying/TP1_JAVA_RMI-workspace/TP1_JAVA_RMI_client/codebaseSharedClasses/");
		System.setProperty("java.rmi.server.codebase", CODEBASE_PATH);

		try {
//			AnimalImpl objAnimal = new AnimalImpl();
			CabinetImpl objCabinet = new CabinetImpl();
			ObservableImpl objObservable = new ObservableImpl();
			Registry registry = LocateRegistry.createRegistry(1099);
			if (registry == null) {
				System.err.println("RMIRegistry not found !");
			} else {
//				registry.bind("animal", objAnimal);
				registry.bind("cabinet", objCabinet);
				registry.bind("observable", objObservable);
				System.err.println("ServerCV ready !");
			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	
	}
}
