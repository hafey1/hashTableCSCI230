public interface Dictionary<Key, E> {

	public void clear();

	public int insert(Key k, E e);

	public Object remove(Key k);

	public Object removeAny();

	public E find(Key k);

	public int size();

}
