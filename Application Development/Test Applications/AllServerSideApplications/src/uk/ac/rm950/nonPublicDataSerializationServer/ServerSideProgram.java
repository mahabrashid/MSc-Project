package uk.ac.rm950.nonPublicDataSerializationServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uk.ac.rm950.remoteInterface.RemoteEmployeeInterface;

public class ServerSideProgram {

	public static void main(String[] args) {
		System.out
		.println("I am a server application serving some locally instantiated objects with noble "
				+ "intention to make necessary information available to enquiring clients to satisfy "
				+ "the objective of AVAILABILITY in CIA principle.");
		System.out
		.println("Hopefully I'm not breaching CONFIDENTIALITY by giving away non-public information.");
		
		System.setProperty("java.security.policy", "security/server.policy");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			Registry registry = LocateRegistry.createRegistry(1099);

			RemoteEmployeeInterface transportingEmployee = new Employee(
					"Neeeeemo");
			RemoteEmployeeInterface employeeStub = (RemoteEmployeeInterface) UnicastRemoteObject
					.exportObject(transportingEmployee, 0);
			registry.rebind("employee1", employeeStub);
			System.out.println("\nExported an employee object in RemoteEmployee wrapper...");

			Manager aManager = new Manager("George Loukas");
			aManager.generatePersonalIdentificationNumber("");
			aManager.setSecretInfo("George's secret info.");
			Employee employeeWrapper = aManager; // wrapped Manager object into a Employee wrapper
													// to avoid exposing the type of the object to
													// outside world before sending over network
			RemoteEmployeeInterface managerStub = (RemoteEmployeeInterface) UnicastRemoteObject
					.exportObject(employeeWrapper, 0);

			registry.rebind("employee2", managerStub);
			System.out.println("Exported a manager object in RemoteEmployee wrapper...");

		} catch (RemoteException e) {
			System.out.println("Exception occurred on server side.");
			e.printStackTrace();
		}

	}

}
