package uk.ac.rm950.breachOfConfidentialityServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uk.ac.rm950.remoteInterface.RemoteJobInterface;

public class ServerSideProgram {

	public static void main(String[] args) {
		System.out.println("Sever side:\n==============");
		
		System.setProperty("java.security.policy", "security/server.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			RemoteJobInterface jobExecutor = new RemoteJobExecutor();

			RemoteJobInterface stub = (RemoteJobInterface) UnicastRemoteObject
					.exportObject(jobExecutor, 0);
//			System.out.println("Stub: " + stub.toString());

			Registry registry = LocateRegistry.createRegistry(1099);
//			Registry registry = LocateRegistry.getRegistry(); //use this method if RMIregistry started externally
//			System.out.println("Registry: " + registry.toString());

			registry.rebind("RemoteJobExecutor", stub);
			System.out.println("RemoteJobExecutor bound");
		} catch (Exception e) {
			System.err.println("RemoteJobExecutor exception:");
			e.printStackTrace();
		}

	}

}
