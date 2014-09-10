package uk.ac.rm950.absenceOfProtocolLevelTypeVerification;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uk.ac.rm950.nonPublicDataSerializationServer.Employee;
import uk.ac.rm950.remoteInterface.RemoteEmployeeInterface;

public class ServerSideProgram {

	public static void main(String[] args) {
		System.out.println("Sever side (" + getIPAddress() + "):");
		System.out.println("=========================================");

		System.setProperty("java.security.policy", "security/server.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			Registry registry = LocateRegistry.createRegistry(1099);

			Employee transportingEmployee = new Employee("Michael");
			RemoteEmployeeInterface employeeStub = (RemoteEmployeeInterface) UnicastRemoteObject
					.exportObject(transportingEmployee, 0);
			registry.rebind("employee", employeeStub);
			System.out
					.println("Exported an employee object in RemoteEmployeeInterface wrapper...");
		} catch (RemoteException e) {
			System.out.println("Exception occurred on server side.");
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
