public class HuffmanNode implements Comparable {
	public String letter;
	public Double frequency;
	public HuffmanNode left, right;

	public HuffmanNode(String letter, Double frequency) {
		this.letter = letter;
		this.frequency = frequency;
		left = null;
		right = null;
	}

	public HuffmanNode(HuffmanNode left, HuffmanNode right) {
		letter = left.letter + right.letter;
		frequency = left.frequency + right.frequency;
		this.left = left;
		this.right = right;
	}

	public int compareTo(Object o) {
		if (o instanceof HuffmanNode) {
			HuffmanNode huff = (HuffmanNode) o;
			return this.frequency.compareTo(huff.frequency);
		}
		return 0;
	}

	public String toString() {
		if (letter.equals("\n")) {
			return "<\\n, " + Math.round(frequency) + ">";
		}
		else{
			return "<" + letter + ", " + Math.round(frequency) + ">";
		}
	}
}
