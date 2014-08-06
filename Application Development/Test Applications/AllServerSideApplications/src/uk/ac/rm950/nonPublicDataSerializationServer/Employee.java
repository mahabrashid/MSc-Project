package uk.ac.rm950.nonPublicDataSerializationServer;

import java.rmi.RemoteException;

import uk.ac.rm950.remoteInterface.RemoteEmployeeInterface;

public class Employee implements RemoteEmployeeInterface {

	public String name;
	public String role = "Not assigned";
	protected long personalIdentificationNumber = 0L;

	public Employee(String name) {
		this.name = name;
	}

	@Override
	public String retrieveEmployeeName() throws RemoteException {
		return this.name;
	}

	@Override
	public String retrieveEmployeeRole() throws RemoteException {
		return this.role;
	}

	@Override
	public String retrievePersonalIdentificationNumber() throws RemoteException { // a much better
																					// way is to
																					// retrieve a
																					// hint
																					// associating
																					// with security
																					// question
		return String.valueOf(personalIdentificationNumber);
	}

	@Override
	public String retrieveEmployee() throws RemoteException {
		String employee = "{\nName: " + this.name + ";\nRole: " + this.role
				+ ";\nPID: " + this.personalIdentificationNumber + "\n}";
		return employee;
	}
}
