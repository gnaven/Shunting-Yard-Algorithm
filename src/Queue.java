
public interface Queue <AnyType>{
	public boolean isEmpty();
	   
    public AnyType dequeue();
    public AnyType peek();
	public void enqueue(AnyType x);
}
