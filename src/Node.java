public class Node<KeyE, Value> {
	final int hash;
	final KeyE key;
	Value information;

	public Node(KeyE k, Value v, int h) {
		hash = h;
		key = k;
		information = v;
	}

	public KeyE getKey() {
		return key;
	}

	public Value getInformation() {
		return information;
	}
}
