import java.util.ArrayList;
import java.util.Collections;

public class HashTable<Key extends Comparable<? super Key>, E> implements Dictionary<Key,E> {

	private Node<Key, E>[] nodes;
	private int count;  //current amount of elements
	private int maxSize; //maxsize
	private Integer random[] = new Integer[1];

	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		nodes = new Node[size];
		maxSize = size;

		ArrayList<Integer> startingValues = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			startingValues.add(i+1);
		}
		Collections.shuffle(startingValues);
		random =  startingValues.toArray(random);

		//this places 0 at the 0 index
		for (int i = 0; i < size; i++) {
			if (random[i] == size) {
				int temp = random[0];
				random[0] = 0;
				random[i] = temp;
			}

		}
	}


	public int size() {
		return count;
	}

	private int hashItUpMyDude(Key k) {
		return Math.abs(k.hashCode() % maxSize);
	}

	public int insert(Key k, E e) {
		int home;
		int stepCounter = 1;
		int pos = home = hashItUpMyDude(k);
		System.out.println("Looking to add " + e + " to position " + pos);
		for (int i = 0; nodes[pos] != null; i++){
			stepCounter = i+1;
			pos = (home + linearProbe(k, i)) % maxSize;
			System.out.println("looking at position " + pos + " for an opening");
		}


		count++;
		nodes[pos] = new Node<Key, E>(k, e, k.hashCode());
		System.out.println("Finally found a space at " + pos + "!" );
		System.out.println("Ready to add another value\n");
		return stepCounter;
	}

	private int linearProbe(Key k, int i) {
		return i;
	}

	public int Quadraticinsert(Key k, E e) {
		int home;
		int stepCounter = 1;
		int pos = home = hashItUpMyDude(k);
		System.out.println("Looking to add " + e + " to position " + pos);
		for (int i = 0; nodes[pos] != null && i < maxSize * 100000; i++){
			if (i >= maxSize) {
				System.out.println("Can not insert the key due to its quadratic jumping pattern try another probing method");
				return 0;}

			stepCounter = i + 1;
			pos = (home + quadraticProbe(k, i)) % maxSize;
			System.out.println("looking at position " + pos + " for an opening");
		}

		if (nodes[pos] != null && nodes[pos].getKey().hashCode() == k.hashCode()) {
			System.out.println("Can not use duplicate key");
			return 0;
		}

		count++;
		nodes[pos] = new Node<Key, E>(k, e, k.hashCode());
		System.out.println("Finally found a space at " + pos + "!" );
		System.out.println("Ready to add another value\n");
		return stepCounter;
	}



	private int quadraticProbe(Key k, int i) {
		return (int) Math.pow(i, 2) % maxSize;
	}

	public Integer[] getRandom() {
		return random;
	}

	public int psuedoRandomInsert(Key k, E e) {
		int home;
		int stepCounter = 1;
		int pos = home = hashItUpMyDude(k);
		System.out.println("Looking to add " + e + " to position " + pos);
		for (int i = 1; nodes[pos] != null; i++){
			stepCounter = i + 1;
			pos = (home + psuedoRandomProbe(k, i)) % maxSize;
			System.out.println("looking at position " + pos + " for an opening");
		}

		if (nodes[pos] != null && nodes[pos].getKey().hashCode() == k.hashCode()) {
			System.out.println("Can not use duplicate key");
			return 0;
		}

		count++;
		nodes[pos] = new Node<Key, E>(k, e, k.hashCode());
		System.out.println("Finally found a space at " + pos + "!" );
		System.out.println("Ready to add another value\n");
		return stepCounter;
	}


	private int psuedoRandomProbe(Key k, int i) {
		return random[i % maxSize];
	}



	public E find(Key k) {
		int home;
		int pos = home = hashItUpMyDude(k);
		for (int i = 1; i < maxSize + 1 && nodes[pos] != null && (nodes[pos].getKey().compareTo(k) != 0); i++) {
			pos = ((home + linearProbe(k, i)) % maxSize);
		}

		if (nodes[pos] == null) {
			return null;
		}
		else {
			return nodes[pos].getInformation();
		}
	}

	public Object remove(Key k) {
		int home;
		int pos = home = hashItUpMyDude(k);
		for (int i = 1; i < maxSize && nodes[pos] != null && (nodes[pos].getKey().compareTo(k) != 0); i++) {
			pos = ((home + linearProbe(k, i)) % maxSize);
		}

		if (nodes[pos] == null) {
			System.out.println("Can not find it");
			return null;
		}
		else {
			System.out.println("This is your value that I removed" + nodes[pos].getInformation());
			Node temp = nodes[pos];
			nodes[pos] = null;
			return temp.getInformation();
		}
	}

	public Object removeAny() {
		int home = 0;
		int pos = home;
		Node temp;
		for (int i = 0; nodes[pos] == null && i < maxSize; i++) {
			pos = (home + i);
		}
		temp = nodes[pos];
		nodes[pos] = null;
		if (temp == null)
			return null;

		return temp.getInformation();
	}

	public void clear() {

	}
}
