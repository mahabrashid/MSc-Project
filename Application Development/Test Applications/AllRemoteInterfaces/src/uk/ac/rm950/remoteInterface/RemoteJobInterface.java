package uk.ac.rm950.remoteInterface;

import java.rmi.RemoteException;

public interface RemoteJobInterface extends java.rmi.Remote {
	<T> T retrieveJobReport(JobInterface<T> job) throws RemoteException;
}
