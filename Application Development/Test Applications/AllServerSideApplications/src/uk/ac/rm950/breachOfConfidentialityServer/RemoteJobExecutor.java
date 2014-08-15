package uk.ac.rm950.breachOfConfidentialityServer;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

import uk.ac.rm950.remoteInterface.JobInterface;
import uk.ac.rm950.remoteInterface.RemoteJobInterface;

public class RemoteJobExecutor implements RemoteJobInterface {

	@Override
	public <T> T retrieveJobReport(JobInterface<T> job) {
		try {
			System.out.println("Server side received a remote job from client machine: " + RemoteServer.getClientHost());
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
		System.out
				.println("The job is being carried out on server machine: " + getIPAddress());
		return job.commenceJob();
	}
	
	private String getIPAddress() {
		String ipAddrss = null;
		try {
			ipAddrss = Inet4Address.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ipAddrss;
	}
}
