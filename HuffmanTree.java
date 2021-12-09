public class HuffmanTree {
	private HuffmanNode root;

	public HuffmanTree(HuffmanNode huff) {
		this.root = huff;
	}

	public void printLegend() {
		printLegend(root, "");
	}

	private void printLegend(HuffmanNode t, String s) {
		if (t.letter.length() > 1) {
			printLegend(t.left, s + "0");
			printLegend(t.right, s + "1");
		} else if (t.letter.length() == 1) {
			if (t.letter.equals("\n")) {
				System.out.println("'\\n'="+s);
			}
			else{
				System.out.println("'"+t.letter + "'=" + s);
			}
		}
	}

	public static BinaryHeap<HuffmanNode> legendToHeap(String legend) {
		String s = legend;
		String letter = "";
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>();
		while (!(s.length() == 0)) {
			char c = s.charAt(0);
			if (Character.isLetter(c)) {
				letter = "" + c;
				s = s.substring(1);
			} else if (Character.isDigit(c)) {
				String number = "";
				while (!(s.isEmpty() || s.substring(0, 1).equals(" "))) {
					number += s.charAt(0);
					s = s.substring(1);
				}
				Double d = Double.valueOf(number);
				HuffmanNode node = new HuffmanNode(letter, d);
				heap.insert(node);
			} else {
				s = s.substring(1);
			}
		}
		return heap;
	}

	public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b) {
		BinaryHeap<HuffmanNode> heap = b;
		while (heap.getSize() > 1) {
			HuffmanNode left = heap.deleteMin();
			HuffmanNode right = heap.deleteMin();
			HuffmanNode parent = new HuffmanNode(right, left);
			heap.insert(parent);
		}
		return new HuffmanTree(heap.deleteMin());
	}
	
	public HuffmanNode getRoot() {
		return root;
	}

	public static void main(String[] args) {
		String legend = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
		BinaryHeap<HuffmanNode> bheap = legendToHeap(legend);
		bheap.printHeap();
		HuffmanTree htree = createFromHeap(bheap);
		htree.printLegend();
	}
}
