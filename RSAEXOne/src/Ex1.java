import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.NoSuchPaddingException;

public class Ex1 {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException {

		KeyPairGenerator myKey = KeyPairGenerator.getInstance("RSA");
		myKey.initialize(1024);

//		KeyPair KeyPair = myKey.generateKeyPair();
//		PublicKey publicKey = KeyPair.getPublic();
//		PrivateKey privateKey = KeyPair.getPrivate();

		int var = 5;
		while (var < 2000) {

			long start = System.currentTimeMillis();
			for (int i = 0; i < var; i++) {
				KeyPair KeyPair = myKey.generateKeyPair();
				PublicKey publicKey = KeyPair.getPublic();
				PrivateKey privateKey = KeyPair.getPrivate();
			}
			long end = System.currentTimeMillis();
			long res = ((end - start));
			System.out.println("Duration of " + var + " is " + res);
			var += var;
		}

	}
}
