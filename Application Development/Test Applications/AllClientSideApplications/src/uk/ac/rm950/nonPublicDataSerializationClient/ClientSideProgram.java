package uk.ac.rm950.nonPublicDataSerializationClient;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import uk.ac.rm950.remoteInterface.RemoteEmployeeInterface;

public class ClientSideProgram {

	public static void main(String[] args) {
		System.out.println("Client side (" + getIPAddress() + "):");
		System.out.println("=========================================");

		System.out
				.println("I am a client application enquiring some publicly disclosable data about employee.");

		System.setProperty("java.security.policy", "security/client.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			Registry registry = LocateRegistry.getRegistry();
			RemoteEmployeeInterface transportedEmployee1 = (RemoteEmployeeInterface) registry
					.lookup("employee1");
			System.out
					.println("\n\nReceived first employee information pack, upacking wrapper...");
			System.out.println(transportedEmployee1.retrieveEmployee());
			System.out
					.println("I got more that the information I need!"
							+ " At least the personalIdentificationNumber isn't anything meaningful.");

			RemoteEmployeeInterface transportedEmployee2 = (RemoteEmployeeInterface) registry
					.lookup("employee2");
			System.out
					.println("\n\nReceived second employee information pack, upacking wrapper...");
			System.out.println(transportedEmployee2.retrieveEmployee());
			System.out
					.println("Why do I see personalIdentificationNumber? Isn't this supposed to be"
							+ " a protected info?");
			
			System.out.println(transportedEmployee2.retrieveEmployeeRole());
			System.out
					.println("Whoa!!! that's a lot of private data you've disclosed there!");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	private static String getIPAddress() {
		String ipAddrss = null;
		try {
			ipAddrss = Inet4Address.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ipAddrss;
	}
}
