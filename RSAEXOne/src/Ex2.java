import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class Ex2 {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

		
		KeyPairGenerator myKey = KeyPairGenerator.getInstance("RSA");
		myKey.initialize(1024);
		KeyPair KeyPair = myKey.generateKeyPair();
		PublicKey publicKey = KeyPair.getPublic();
		PrivateKey privateKey = KeyPair.getPrivate();
		
		String message = "HI";
		System.out.println(message);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] messageEncrypted = cipher.doFinal(message.getBytes());
		
		byte[] privateKeyBytes = privateKey.getEncoded();
		byte[] publicKeyBytes = publicKey.getEncoded();
		
		//write to the file
		 FileInputStream privatein = null;
		 FileInputStream publicin = null;
	     FileOutputStream privateout = null;
	     FileOutputStream publicout = null;
	     FileOutputStream messageOut = null;
	     FileInputStream messagein = null;
	     try {
		
	    	 
	    	 privateout = new FileOutputStream("src/PrivateKeyFile");
	    	 publicout = new FileOutputStream("src/PublicKeyFile");
	    	 messageOut = new FileOutputStream("src/message");
		     privateout.write(privateKeyBytes);  
		     publicout.write(publicKeyBytes);  
		     messageOut.write(messageEncrypted);  
		     System.out.println(messageEncrypted);
		     
		     privatein = new FileInputStream("src/PrivateKeyFile");
	    	 publicin = new FileInputStream("src/PublicKeyFile");
	    	 messagein = new FileInputStream("src/message");
	    	 privatein.read(privateKeyBytes); 
	    	 publicin.read(publicKeyBytes);
	    	 messagein.read(messageEncrypted);  
	    	 
	    	 KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever

	 		PrivateKey privateKey1 = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
	 		PublicKey publicKey1 = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
	 		
	 		cipher.init(Cipher.DECRYPT_MODE, publicKey1);
			byte[] decryptedText = cipher.doFinal(messageEncrypted);
	 		
	 		//System.out.println(privateKey1);
	 		//System.out.println(publicKey1);
			System.out.println(new String(decryptedText));

		} catch (IOException e) {
			e.printStackTrace();
		
	    publicout.close();
	    privateout.close();
		privatein.close();
		publicin.close();
		messagein.close();
		messageOut.close();
		
		}	
	}
}


//read from the file
		//try{
		
		
//		Cipher c = Cipher.getInstance("RSA");
//		Cipher c2 = Cipher.getInstance("RSA");
//		System.out.println("public key from file: " + c.toString());
//		System.out.println("private key from file: " + c2.toString());	
		
		//encode
//		String message = "Hello im am kim jun un";
//		 c.init(Cipher.ENCRYPT_MODE, privateKey);
//		 new BASE64Encoder().encode(message.getBytes());
//		// encode private key bites into private key
//		 
//		 //decode
//		 byte[] data = new BASE64Decoder().decodeBuffer(message);
//	        Cipher RSACipher = Cipher.getInstance("RSA");
//	        RSACipher.init(Cipher.DECRYPT_MODE, privateKey);
//	        byte[] plainData = RSACipher.doFinal(data);
//	        new String(message);

// open the files
// creat instance of type cipher and initalize it
// use encrypty
// use decrypt
// for the public and private keys

// write encoder and decoder
// String encrypt(){
//
// Cipher RESCipher = Cipher.getInstance("RES");
// RESCipher.init(Cipher.ENCRYPT_MODE, privateKey);
// byte[] byteDataToEncrypt = message.getBytes();
// byte[] byteCipherText = RESCipher.doFinal(byteDataToEncrypt);
// return new BASE64Encoder().encode(byteCipherText);
// }
