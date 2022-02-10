package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter filename to decode: ");
		String filename = scan.next();
		File f = new File(filename);
		Scanner sc = new Scanner(f);
		boolean nlchar = false;
		String encoding = "";
		while (sc.hasNextLine()) {
			if(nlchar) {
				encoding+= '\n';
			}
			String nl = sc.nextLine();
			if(Character.isDigit(nl.charAt(0))) {
				break;
		}
		encoding +=nl;
		nlchar = true;
		}
		
		
		String decoding = "";
		String dLine = "";
		Scanner decodeScanner = new Scanner(f);
		while (decodeScanner.hasNextLine()) {
			dLine = decodeScanner.nextLine();
			dLine = dLine.replace("\n", "");
			if(!Character.isDigit(dLine.charAt(0))) {
				continue;
			}
			decoding += dLine;
		}
		MsgTree m = new MsgTree(encoding);
		System.out.println("character code");
		System.out.println("-------------------------");
		MsgTree.printCodes(m, "");
		System.out.println("MESSAGE:");
		m.decode(m, decoding);
		scan.close();
		sc.close();
		decodeScanner.close();
	}
}
