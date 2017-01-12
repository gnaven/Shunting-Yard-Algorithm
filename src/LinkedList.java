public class LinkedList<AnyType>implements SimpleLinkedListPr {
	public Node <AnyType> first;
	public LinkedList (){
		first= null;
	}
	
	@Override
	public void insert(Object x) {
		Node newL= new Node();
		newL.data= x;
		newL.next= first;
		first = newL;
	}


	public void delete(Object x) {
		Node <AnyType>now = first;
		Node <AnyType> before = first;
		if (x.equals(now.data)){
			first= first.next;
			
		}
		while (now!=null){
		
			if (now.data.equals(x)){
				//makes the pointer point to the next node, skipping the one to be deleted
				before.next=now.next;
	
			}
			
			before=now;
			now=now.next;
		
		}
		
	}

//Question 5
	@Override
	public boolean contains(Object x) {
		Node find = first;
		while (find!=null){
			if ((find.data).equals(x)){
				return true;
			}
			find=find.next;
		}
		return false;
	}


	@Override
	//Question 6
	public Object lookup(Object x) {
		Node look = first;
		while (look!=null){
			if ((look.data).equals(x)){
				return "Yes "+x+ " is in this list";
			}
			look=look.next;
		}
		return null;
	}


	@Override
	public boolean isEmpty() {
		return (first==null);
	}

//Question4 
	@Override
	public void printList() {
		Node front = first;
		while (front!=null){
			System.out.println((front.data).toString());
			front=front.next;
		}
		
	}
	public Object top()
	{
		Object temp = first.data;
		first = first.next;
		
		return temp;
	
	}
	public Object first()
	{
		return first.data;
	
	}
}
