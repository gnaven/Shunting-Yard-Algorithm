
public class QueueM <AnyType> implements Queue <AnyType>{
	DLinkedList<AnyType> list = new DLinkedList<AnyType>();
	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}
	@Override
	public void enqueue(AnyType x) {
	list.insert(x);
	}

	@Override
	public AnyType dequeue() {
		
		return (AnyType) list.look();
	}

	@Override
	public AnyType peek() {
		return (AnyType) list.peek();
	}
	public void  print(){
		 list.printList();
		
	}
}
