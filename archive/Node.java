package archive;

public class Node<T> {
	T value;
	Node<T> next;
	
	public Node() {
		this(null, null);
	}
	
	public Node(T value, Node<T> next) {
		this.value = value;
		this.next = next;
	}
	
	public Node(T value) {
		this(value, null);
	}
	
}