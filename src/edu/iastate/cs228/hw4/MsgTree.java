package edu.iastate.cs228.hw4;

public class MsgTree {

	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	private static int staticCharIdx = 0;

	public MsgTree(String encodingString) {
		if (staticCharIdx < encodingString.length()) {
			char c = encodingString.charAt(staticCharIdx++);
			if (c != '^') {
				payloadChar = c;
				left = null;
				right = null;
				return;
			}
			if (c == '^') {
				left = new MsgTree(encodingString);
				right = new MsgTree(encodingString);
			}
		}
	}

	public MsgTree(char payloadChar) {
		this.payloadChar = payloadChar;
		left = null;
		right = null;
	}

	public static void printCodes(MsgTree root, String code) {
		if (root.left == null && root.right == null) {
			if (root.payloadChar == '\n') {
				System.out.println("   \\n      " + code);
			} else if (root.payloadChar == ' ') {
				System.out.println("  \" \"      " + code);
			} else {
				System.out.println("   "+root.payloadChar + "       " + code);
			}
			return;
		}
		printCodes(root.left, code + "0");
		printCodes(root.right, code + "1");
	}

	public void decode(MsgTree root, String code) {
		int count = 0;
		MsgTree current = root;
		while (count < code.length()) {
			if (current.left == null && current.right == null) {
				System.out.print(current.payloadChar);
				current = root;
			} else if (code.charAt(count) == '0') {
				current = current.left;
				count++;
			} else if (code.charAt(count) == '1') {
				current = current.right;
				count++;
			}
		}
		System.out.print(current.payloadChar);
	}
}