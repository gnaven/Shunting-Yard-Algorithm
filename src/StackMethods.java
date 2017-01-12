
public class StackMethods<AnyType>implements Stack {
	LinkedList l = new LinkedList ();

	@Override
	public boolean isEmpty() {
		return l.isEmpty();
	}
	@Override
	public void push(Object x) {
		l.insert(x);
	}

	@Override
	public Object pop() {
		if (isEmpty()){
			return null;
		}else
		return l.top();
	}

	@Override
	public Object peek() {
		return l.first();
	}
	

}
