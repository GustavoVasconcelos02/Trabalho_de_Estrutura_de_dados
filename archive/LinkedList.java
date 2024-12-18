package archive;

public class LinkedList<T> {
	int size;
	Node<T> head;
	
	public LinkedList() {
		this.size = 0;
		this.head = null;
	}
	
	public LinkedList(Node<T> head){
		this.size = 1;
		this.head = head;
	}

	public void insert(T newValue) {
		Node<T> newNode = new Node<T>(newValue);
		newNode.next = head;
		head = newNode;
	}

	public boolean find(T value) {
		Node<T> node = this.head;
		while(node != null)
		{
			if(node.value == value)
			{
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	public void print() {
		Node<T> node = this.head;
		while(node != null)
		{
			System.out.println("- "+node.value);
			node = node.next;
		}
	}
}