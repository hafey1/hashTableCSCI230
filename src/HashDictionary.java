import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.HashMap;

public class HashDictionary<Key extends Comparable<? super Key>, E> implements Dictionary<Key,E> {

	private static final int defaultSize = 10;
	private HashTable<Key, E> Storage;
	private int count;  //current amount of elements
	private int maxSize; //maxsize

	public HashDictionary() {this(defaultSize);}

	public HashDictionary(int size) {
		//we want to store the keys and the values
		count = 0;
		maxSize = size;
		Storage = new HashTable<>(size);


	}


	public void clear() {
		count = 0;
		Storage = new HashTable<>(maxSize);
	}


	public int insert(Key k, E e){
		assert count < maxSize : "Hash table is full";
		//T.hashInsert(k, e);

		count++;
		return Storage.insert(k, e);
	}

	public int quadraticInsert(Key k, E e) {
		assert count <maxSize : "Hash table is full";

		count++;
		return Storage.Quadraticinsert(k, e);
	}

	public Integer[] getRandomArray() {
		return Storage.getRandom();
	}

	public String printOutRandomArray() {
		String results = "";

		for (int i = 0; i < Storage.getRandom().length; i++) {
			results = results + Storage.getRandom()[i] + " ";
		}

		return results;
	}


	public int psuedoRandomInsert(Key k, E e) {
		assert count < maxSize : "Hash table is full";
		count++;
		return Storage.psuedoRandomInsert(k, e);
	}


	public Object remove(Key k){
		//remove the piece at k
		if (count <= 0)
		{	System.out.println("Can't remove from an empty Dictionary");
			return null;}
		Object resultOfRemove = Storage.remove(k);
		if (resultOfRemove == null)
			return null;
		count--;
		return resultOfRemove;
	}

	public Object removeAny(){
		//remove them in order
		if (count <= 0)
			{System.out.println("Can't remove from an empty Dictionary");
			return null;}
		Object resultOfRemoveAny = Storage.removeAny();
		if (resultOfRemoveAny == null)
			return null;
		count--;
		return resultOfRemoveAny;
	}

	public E find(Key k){

		return Storage.find(k);
	}

	public int howManyKeys() {
		return count;
	}

	public int size(){

		return maxSize;
	}

}
