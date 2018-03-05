import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



public class Ex3 {

	public static void main(String[] args)
			throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {

		KeyPairGenerator myKey = KeyPairGenerator.getInstance("RSA");
		myKey.initialize(1024);

		KeyPair KeyPair = myKey.generateKeyPair();
		PublicKey publicKey = KeyPair.getPublic();
		PrivateKey privateKey = KeyPair.getPrivate();
		
		String message = "hello";
		System.out.println("Original Message: "+ message);
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(message.getBytes());
		byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] messageEncrypted = cipher.doFinal(hash);
		
		
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			
			out = new FileOutputStream("src/File");
			out.write(hash);
			out.write(messageEncrypted);
			System.out.print("Hash: ");
			System.out.println(new String(hash));
			System.out.print("Message hashed and Encrypted: ");
			System.out.println(new String(messageEncrypted));

			in = new FileInputStream("src/File");
			in.read(hash);
			in.read(messageEncrypted);

			//System.out.println("public key" + publicKey);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] decryptedText = cipher.doFinal(messageEncrypted);

			
			System.out.print("Original Message decrypted But hashed: ");
			System.out.println(new String(decryptedText));
			
			if(java.util.Arrays.equals(hash, decryptedText)){
				System.out.println("True: Message hasnt changed, Hashes are equal");
			}

		} catch (IOException e) {
			e.printStackTrace();

			in.close();
			out.close();
		}
	}
}

//String msg1="Message from bob 1"; // message
//MSGBOB = cipher.doFinal(msg1.getBytes("ISO-8859-1")); // encryption
//msg.setContent(Base64.encode(MSGBOB)); // conversion to string
//This is how I decrypt it :
//
//mm = Base64.decode(msg.getContent());// received message 
//m = new String(cipher.doFinal(mm),"ISO-8859-1"); // decryption

//MessageDigest md = MessageDigest.getInstance("SHA-1");
//md.update(hash);
//md.hashCode();
//byte[] unHasedMessage = md.digest();
//System.out.println("Decrypted Message: "+decryptedText);
//System.out.println("Unhashed Message: "+unHasedMessage);

//String messageHashResult = hash.toString();
//System.out.println("Result of hash: " + messageHashResult);



