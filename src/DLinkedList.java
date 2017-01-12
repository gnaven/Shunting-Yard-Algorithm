
public class DLinkedList<AnyType> implements DoublyLinkedList{
	private MyDoubleNode<AnyType> head;
	private MyDoubleNode<AnyType> tail;
	
	public DLinkedList (){
		head=null;
		tail=null;
	}
	@Override
	public void insert(Object x) {
		MyDoubleNode<AnyType> b = head;
		MyDoubleNode<AnyType>NewNode= new MyDoubleNode();
		//Question 2
		if (head==null){
			NewNode.data=(AnyType) x;
			head= NewNode;
			tail=NewNode;
			}else{
				NewNode.data= (AnyType) x;
				NewNode.next= head;
				NewNode.previous=null;
				head.previous=NewNode;
				head=NewNode;
			}
		
	}
	//isEmpty implemented
	@Override
	public boolean isEmpty() {
		
		return (head==null);
	}

	//Question 7
	@Override
	public void delete(Object x) {
	MyDoubleNode <AnyType> now = head;
	if(x.equals(now.data)){
		head=head.next;
		head.previous=null;
	}
	
	while (now.next.next!= null){
		if (now.data.equals(x)){
			now.previous.next= now.next;
			now.next.previous= now.previous;
		}
		now=now.next;
	}
		
	}
	//Question 5
	@Override
	public boolean contains(Object x) {
		MyDoubleNode <AnyType> now = head;
		while (now != null){
			if (now.data.equals(x)){
				return true;
			}
		}
		return false;
	}
	
	//Question 6
	@Override
	public Object lookup(Object x) {
		MyDoubleNode<AnyType> now= head;
		while (now!= null){
			if (now.data.equals(x)){
				return "Yes "+x+" is in the list";
			}
		}
		return null;
	}

	//Question 4
	@Override
	public void printList() {
		MyDoubleNode<AnyType> NewN = head;
		while (NewN!= null){
			System.out.println(NewN.data.toString());
			NewN=NewN.next;
		}
		
	}

	@Override
	public void printListRev() {
		MyDoubleNode<AnyType> NewN=tail;
		while (NewN!= null){
			System.out.println(NewN.data.toString());
			NewN=NewN.previous;
		}
		
	}
	
	public Object look()
	{
		Object temp = tail.data;
		
		if(isEmpty())
		return null;
		else{
			if(tail.previous == null)
			{
				head = null;
				tail = null;
				
				return temp;
			}
		tail = tail.previous;
		tail.next = null;
		return tail.data;
		}
		
		
	}
	public Object peek()
	{
		return tail.data;
		
	}
}
