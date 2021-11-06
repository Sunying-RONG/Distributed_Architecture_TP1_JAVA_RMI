package tp1_server;

import java.util.ArrayList;

import tp1_common.IObserver;

public class ObserverUpdater {
	private static final ArrayList<IObserver> observerList = new ArrayList<>();
	
	public static synchronized void addIObserver(IObserver observer) {
		observerList.add(observer);
	}
	
	public static synchronized void numberChanged(Object o) {
			for (IObserver observer : observerList) {
				try {
					observer.update(o);
				} catch (Exception e) {
	                System.err.println("observer disconnected, unsubscribing... Veulliez ressayer les simulations dans Console de Client.");
	                observerList.remove(observer);
	            }
			}
		
	}
}
