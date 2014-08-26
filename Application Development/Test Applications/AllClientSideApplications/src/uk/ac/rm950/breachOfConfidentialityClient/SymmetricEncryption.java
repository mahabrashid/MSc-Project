package uk.ac.rm950.breachOfConfidentialityClient;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import uk.ac.rm950.remoteInterface.JobInterface;

public class SymmetricEncryption implements JobInterface<String>, Serializable {

	private static final long serialVersionUID = 1L;

	private StringBuffer messageBuffer;
	private SymEncryptionMethod encryptionMethod;
	private int key;

	public SymmetricEncryption(String message,
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
				+ "\n\nThe job was executed in machine: %s", originalMessage,
				encryptedMessage, getIPAddress());
	}

	/**
	 * Routes the encryption process based on chosen encryption method
	 * 
	 * @return encrypted text
	 */
	private String performEncryption() {
		String cipherText = null;
		switch (encryptionMethod) {
		case SUBSTITUTION:
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
}
