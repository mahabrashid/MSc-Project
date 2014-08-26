package uk.ac.rm950.breachOfConfidentialityClient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import uk.ac.rm950.remoteInterface.RemoteJobInterface;

public class ClientSideProgram {

	public static void main(String[] args) {
		System.out.println("Client side:\n================");
		
		System.setProperty("java.security.policy", "security/client.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			Registry registry = LocateRegistry.getRegistry();
//			System.out.println("Registry: " + registry.toString());

			RemoteJobInterface jobExecutor = (RemoteJobInterface) registry
					.lookup("RemoteJobExecutor");

			SymmetricEncryption symetricEncryp = new SymmetricEncryption("Hello RMI application.",
					SymEncryptionMethod.SUBSTITUTION, 5);
			String jobReport = jobExecutor.retrieveJobReport(symetricEncryp);

			System.out.println(jobReport);
		} catch (Exception e) {
			System.out.println("Remote job client exception.");
			e.printStackTrace();
		}
	}
}
