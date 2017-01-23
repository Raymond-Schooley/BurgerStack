
public class StackMain {
	private StackMain(){}
	
	public static void main(final String[] theArgs) throws EmptyStackException {
		MyStack<Double> mySt= new MyStack<>();
		
		for (int i = 0; i < 10; i++) {
			mySt.push(new Double(i));
		}
		System.out.println(mySt);
	
		MyStack<Integer> mySt1 = new MyStack<>();
		System.out.println(mySt1);
		mySt1.pop();
	}
}
