import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static void main (String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		long k = Long.parseLong(br.readLine());
		k=k%26;
		// Encryption
		System.out.println("Encryption in Process...");
		StringTokenizer in = new StringTokenizer(s.replaceAll(""," "));
		String cipher="";
		while(in.hasMoreTokens()) {
			cipher+=(char)(((in.nextToken().toUpperCase().charAt(0)-'A'+k)%26)+'A')+"";
		}
		System.out.println("Encryption Completed.");
		System.out.println("Cipher generated is - " + cipher);
		
		// Decryption
		System.out.println("Decryption in Process...");
		in = new StringTokenizer(cipher.replaceAll(""," "));
		String plaintext="";
		while(in.hasMoreTokens()) {
			long z=((in.nextToken().toLowerCase().charAt(0)-'a'-k+26));
			if(z>=25) {
				z%=26;
			} 
			plaintext+=(char)(z+'a');
		}
		System.out.println("Decryption Completed.");
		System.out.println("Decrypted Message - " + plaintext);
	}
}

/*
Input - 
qwertyuiopasdfghjklzxcvbnm
25

Output - 
Encryption in Process...
Encryption Completed.
Cipher generated is - PVDQSXTHNOZRCEFGIJKYWBUAML
Decryption in Process...
Decryption Completed.
Decrypted Message - qwertyuiopasdfghjklzxcvbnm
*/