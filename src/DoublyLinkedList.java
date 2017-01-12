
public interface DoublyLinkedList <AnyType> {
	public void delete(AnyType x);
	public boolean contains(AnyType x);
	public AnyType lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
	void insert(AnyType x);
}
