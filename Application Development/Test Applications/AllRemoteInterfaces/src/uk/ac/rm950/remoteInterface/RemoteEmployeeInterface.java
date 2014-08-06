package uk.ac.rm950.remoteInterface;

import java.rmi.RemoteException;

public interface RemoteEmployeeInterface extends java.rmi.Remote {

	public String retrieveEmployeeName() throws RemoteException;

	public String retrieveEmployeeRole() throws RemoteException;

	public String retrievePersonalIdentificationNumber() throws RemoteException;
	
	public String retrieveEmployee() throws RemoteException;
}
