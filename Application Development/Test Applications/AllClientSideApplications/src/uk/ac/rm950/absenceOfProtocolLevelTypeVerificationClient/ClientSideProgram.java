package uk.ac.rm950.absenceOfProtocolLevelTypeVerificationClient;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import uk.ac.rm950.remoteInterface.RemoteJobInterface;

public class ClientSideProgram {

	public static void main(String[] args) {
		System.out.println("Client side (" + getIPAddress() + "):");
		System.out.println("=========================================");

		System.setProperty("java.security.policy", "security/client.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		Throwable thrownException = null;
		try {
			Registry registry = LocateRegistry.getRegistry();
			thrownException = (Throwable) ((RemoteJobInterface) registry
					.lookup("employee")); // the variable thrownException will not be set as the
											// code execution will abort as soon as the exception
											// occurs in:
											// (RemoteJobInterface) registry.lookup("employee").
											// Hence, the variable will have to be set in the catch
											// block.

		} catch (Exception e) {
			thrownException = e;
			if (thrownException instanceof ClassCastException) {
				System.out.println("RMI call successful.");
			} else {
				System.out.println("RMI call unsuccessful.");
			}
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
