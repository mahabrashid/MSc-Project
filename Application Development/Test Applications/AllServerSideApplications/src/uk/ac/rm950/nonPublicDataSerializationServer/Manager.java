package uk.ac.rm950.nonPublicDataSerializationServer;

import java.rmi.RemoteException;
import java.util.Random;

public class Manager extends Employee {
	private String secretInfo = null;

	public Manager(String name) {
		super(name);
		this.role = "Management";
	}

	public void generatePersonalIdentificationNumber(String dob) {
		if (dob.matches("\\d{2}[-/]\\d{2}[-/]\\d{4}")) {
			dob = dob.substring(0, 2) + dob.substring(3, 5) + dob.substring(6);
			String reverseDob = new StringBuilder(dob).reverse().toString();
			try {
				this.personalIdentificationNumber = Long.valueOf(reverseDob);
				return;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// if regex unmatched or there's a NumberFormat exception, then generate 8-digit long number
		this.personalIdentificationNumber = 10000000L + (long) (new Random()
				.nextDouble() * (99999999 - 10000000));
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
	public String retrieveEmployeeRole() throws RemoteException {
		String employee = "{\nRole: " + this.role
				+ "\nSecret info: " + this.secretInfo + "\n}";
		return employee;
	}
}