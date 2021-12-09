import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;
public class HuffmanConverter {
// The # of chars in the ASCII table dictates
// the size of the count[] & code[] arrays.
	public static final int NUMBER_OF_CHARACTERS = 256;
// the contents of our message...
	private String contents;
// the tree created from the msg
	private HuffmanTree huffmanTree;
// tracks how often each character occurs
	private int count[];
// the huffman code for each character
	private String code[];
// stores the # of unique chars in contents
	//private int uniqueChars = 0; // (optional)

	/** Constructor taking input String to be converted */
	public HuffmanConverter(String input) {
		this.contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
	}

	/**
	 * Records the frequencies that each character of our message occurs... I.e., we
	 * use 'contents' to fill up the count[] list...
	 */
	public void recordFrequencies() {
		int length = contents.length();
		for (int i=0; i<length; i++) {
			count[(int)contents.charAt(i)] += 1;
		}
	}

	/**
	 * Converts our frequency list into a Huffman Tree. We do this by taking our
	 * count[] list of frequencies, and creating a binary heap in a manner similar
	 * to how a heap was made in HuffmanTree's fileToHeap method. Then, we print the
	 * heap, and make a call to HuffmanTree.heapToTree() method to get our much
	 * desired HuffmanTree object, which we store as huffmanTree.
	 */
	

	public void frequenciesToTree() {
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>();
		for (int i=0; i<256; i++) {
			if (count[i] != 0) {
				String s = ""+(char)i;
				Double d = (double) count[i];
				HuffmanNode node = new HuffmanNode(s, d);
				heap.insert(node);
			}
		}
		heap.printHeap();
		huffmanTree = HuffmanTree.createFromHeap(heap);
	}

	/**
	 * Iterates over the huffmanTree to get the code for each letter. The code for
	 * letter i gets stored as code[i]... This method behaves similarly to
	 * HuffmanTree's printLegend() method... Warning: Don't forget to initialize
	 * each code[i] to "" BEFORE calling the recursive version of treeToCode...
	 */
	public void treeToCode() {
		for(int i=0; i<256; i++) {
			code[i] = "";
		}
		treeToCode(huffmanTree.getRoot(), "");
	}

	/*
	 * A private method to iterate over a HuffmanNode t using s, which contains what
	 * we know of the HuffmanCode up to node t. This is called by treeToCode(), and
	 * resembles the recursive printLegend method in the HuffmanTree class. Note
	 * that when t is a leaf node, t's letter tells us which index i to access in
	 * code[], and tells us what to set code[i] to...
	 */
	private void treeToCode(HuffmanNode t, String s) {
		if (t.letter.length() > 1) {
			treeToCode(t.left, s + "0");
			treeToCode(t.right, s + "1");
		} else if (t.letter.length() == 1) {
			code[(int)t.letter.charAt(0)] = s;
		}
	}

	/**
	 * Using the message stored in contents, and the huffman conversions stored in
	 * code[], we create the Huffman encoding for our message (a String of 0's and
	 * 1's), and return it...
	 */
	public String encodeMessage() {
		String s = "";
		int length = contents.length();
		for (int i=0; i<length; i++) {
			s += code[(int)contents.charAt(i)];
		}
		return s;
	}

	/**
	 * Reads in the contents of the file named filename and returns it as a String.
	 * The main method calls this method on args[0]...
	 */
	public static String readContents(String filename) {
		String temp = "";
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				temp+= sc.nextLine();
				temp += "\n";
			}
			sc.close();
			return temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Using the encoded String argument, and the huffman codings, re-create the
	 * original message from our huffman encoding and return it...
	 */
	public String decodeMessage(String encodedStr) {
		String toCheck = "";
		String decoded = "";
		while (!encodedStr.isEmpty()) {
			toCheck += ""+encodedStr.charAt(0);
			encodedStr = encodedStr.substring(1);
			if (Arrays.asList(code).contains(toCheck)) {
				decoded += ""+(char)findIndexOfCode(toCheck);
				toCheck = "";
			}
		}
		return decoded;
	}
	
	private int findIndexOfCode(String encoded) {
		for (int i=0; i<code.length; i++) {
			if (code[i].equals(encoded)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Uses args[0] as the filename, and reads in its contents. Then instantiates a
	 * HuffmanConverter object, using its methods to obtain our results and print
	 * the necessary output. Finally, decode the message and compare it to the input
	 * file.
	 * <p>
	 * NOTE: Example method provided below...
	 *
	 */
	public static void main(String args[]) {
		String s = HuffmanConverter.readContents(args[0]);
		HuffmanConverter h = new HuffmanConverter(s);
		h.recordFrequencies();
		h.frequenciesToTree();
		System.out.println();
		h.huffmanTree.printLegend();
		System.out.println();
		System.out.println("Huffman Encoding:");
		h.treeToCode();
		String encoded = h.encodeMessage();
		System.out.println(encoded+"\n");
		System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
		System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
		System.out.println(h.decodeMessage(encoded));
	}

}
