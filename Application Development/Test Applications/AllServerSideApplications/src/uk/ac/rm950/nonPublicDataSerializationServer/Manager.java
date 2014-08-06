package uk.ac.rm950.nonPublicDataSerializationServer;

import java.rmi.RemoteException;

public class Manager extends Employee {
	private String secretInfo = null;

	public Manager(String name) {
		super(name);
		this.role = "Management";
	}

	public void generatePersonalIdentificationNumber(String dob) {
		// do some logical calculation here with the dob
		this.personalIdentificationNumber = 1234L;
	}

	void setSecretInfo(String secretInfo) { // only accessible from classes in the same package,
											// for example: a particular system in a particular
											// location
		this.secretInfo = secretInfo;
	}

	public String getSecretInfo() {
		// ideally should return a copy of SecretInfo object using copy constructor.
		// However, since secretInfo here is a String, which is immutable, it is safe to send
		// the string itself.
		return secretInfo;
	}

	/*
	 * Method signature in RemoteEmployeeInterface; implementation has already been provided in
	 * Employee class, which is overridden and further implemented here
	 * 
	 * @see uk.ac.rm950.nonPublicDataSerializationServer.Employee#retrieveEmployee()
	 */
	@Override
	public String retrieveEmployee() throws RemoteException {
		String employee = "{\nName: " + this.name + ";\nRole: " + this.role
				+ ";\nPID: " + this.personalIdentificationNumber
				+ "\nSecret info: " + this.secretInfo + "\n}";
		return employee;
	}
}