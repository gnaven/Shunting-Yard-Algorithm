import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

public class Shunting_Yard<AnyType> {
static ArrayList<String> storage= new ArrayList<String>();

//Method that reads the files and then stores each line as a element in the list
	public static void sorting (String filename) throws IOException{
		BufferedReader b= new BufferedReader(new FileReader (filename));
		String line;
		while ((line= b.readLine())!=null){ 
			line=line.replace(" ", "");
			storage.add(line);
		}
		
	}
	
// method that does the postfixing
	public static QueueM PostFix (ArrayList<String> stored){
		//ArrayList<QueueM> postfix= new ArrayList<QueueM>(); 
		
		StackMethods sm = new StackMethods();
		QueueM queued = new QueueM();
		// for loop so that it iterates over the whole list, where each element is 
		//is an operator or operand and are enqueued/ stack respectively
		for (int i=0; i<stored.size();i ++){
			// if statement for distinguishing operator and operand
		if (Operator_Indentifier_String(stored.get(i))){
			//operation for taking care parenthesis
			
			if (stored.get(i).equals("(")){
				sm.push(stored.get(i));
			}else if (stored.get(i).equals(")")){
				//sm.push(stored.get(i));
				while((sm.peek().toString()).equals("(")==false){
					queued.enqueue(sm.pop());
				}
				
				sm.pop();
			} else{
			//method called which will compare precedence of the first operator and the one which is about to enter

				int decide =Shunter(sm,stored.get(i), queued,i);
			
			// if statement for taking care of the parenthesis
				if (decide==0){
					sm.push(stored.get(i));
				}else if (decide ==1){
					queued.enqueue(sm.pop());
					sm.push(stored.get(i));
				
				}
		}
					}else {
				//int numC= Character.getNumericValue( stored.get(j).charAt(i));
				String numC= stored.get(i);
				queued.enqueue(numC);
			}
		   
		}
			while (sm.isEmpty()==false){
				queued.enqueue(sm.pop());
			}

	  //  queued.print();

		
		//postfix.add(queued);
		
	return queued;
	}
	// method to evaluate and find the answer for the postfix 
	public static StackMethods PostFix_Evaluation(QueueM post){
		
		StackMethods stack= new StackMethods ();
		boolean b = true;
		String comparison ="";
		while (b){
			
			 comparison= post.peek().toString();
			if (Operator_Indentifier_String(comparison)){
				//removes the operator first
				post.dequeue();
			//	stack.pop();
//						if (Operator_Indentifier_String(stack.peek().toString())){
//							stack.pop();
//							}
				double first= Double.parseDouble(stack.peek().toString());
				stack.pop();
				double second;
				if (stack.isEmpty()){
				second= 0;	
				}else{
				second= Double.parseDouble(stack.peek().toString());
				stack.pop();
				}
				double result = Calculation_Method(comparison, second, first);
				stack.push(result);
			}else {
				stack.push(post.peek());
				post.dequeue();
			}
					if (post.isEmpty()==true){
								break;
						}
		}
		//System.out.println("print"+ stack.peek());
		return stack;
		
	}
	public static double Calculation_Method(String operator, double first, double second){
		double total=0;
		switch (operator){
		case "+" :
			total = first + second;
			break;
		case "-" :
			total = first - second;
			break;
		case "*" :
			total = first * second;
			break;
		case "/" :
			total = first / second;
			break;
		case "^" :
			total = Math.pow(first,second);
			break;

		case "<" :
			if (first<second){
				total = 1;
			}else {
				total =0;
			}
			break;
		case ">" :
			if (first>second){
				total=1;
			}else {
				total =0;
			}
			break;
		case "=" :
			if (first==second){
				total= 1;
			}else{
				total=0;
			}
			break;
		case "&" :
		if (first ==1 && second ==1){
			total = 1;
		}else {
			total =0;
		}
			break;
		case "|" :
		if (first==1||second ==1){
			total = 1;
		}else {
			total=0;
		}
			break;
		case "!" :
			System.out.println("numb "+ second);
			if (second ==1){
				total =0;
			}else {
				total=1;
			}
			break;
		case "%" :
			total = first%second;
			break;
//		case "sin" :
//			b= true;
//			break;
//		case "cos" :
//			b= true;
//			break;
//		case "tan" :
//			b= true;
//			break;
			
		
		}
		
		return total;
	}
	public static int Shunter(StackMethods stack, String operator, QueueM q, int i){
		// if the int retruned is 0 then operator is to be stacked if the int is 1 then the operator is popped from the stack to queue
		int decider;
		// when the stack is empty it simply adds the operator
		if (stack.isEmpty()){
			decider =0;
		}else {
			
			// brings the precedence of both the operator in the top of stack and the one being compared
			String [] precedence = Operator_Precedence(operator);

			String [] InStack = Operator_Precedence(stack.peek().toString());
			
			int NumP= Integer.parseInt(precedence[0]);
			int OnTop= Integer.parseInt(InStack[0]);
			// checks for the left precedence operators
			if (precedence [1].equals("L")){
				// 
				if (OnTop==15){
					decider=0;
				}
				else if (NumP<= OnTop){
					// the operator on top will be enqued
					decider= 1;
				}else{
					// operator will be stacked
					decider =0;
				}
				// 15 assigned for only parenthesis
		} //else if (NumP==15){
//				
//			}
			
			else {
				if (NumP< OnTop){
					// the operator on top will be enqued
					decider= 1;
				}else{
					// operator will be stacked
					decider =0;
				}
			}
		}
		return decider;
		
	}
	
	// converts the characters to String for each number and operator and then adds it to a arraylist
	public static ArrayList<String> Seperated (String p){
		 ArrayList<String> sep = new ArrayList<String>();
		 boolean b= true;
    int j=0;
    int test=0;
		while (b){
			test++;
			String added= "";
			//for added double numbers or decimal nums together
		 while (Operator_Indentifier(p.charAt(j))==false){
			 added+=p.charAt(j);
			 j++;
			 if (j==p.length()){
		 			break;
		 		} 
		 }
		 if (!added.equals("")){
		 sep.add(added);
		 }
		
		 if (j==p.length()){
	 			break;
	 		}
		 
		 			 		 
		 sep.add(""+p.charAt(j));
		 //checks only 
		 if (j+1== p.length()&& p.endsWith(")")){
	 			break;
		 }
		 j++;
		 
		}

		return sep;
	
	}
	
	 public static String[] Operator_Precedence(String c){
		   String [] prec = new  String [2];
		switch (c){
		case "+" :
			prec[0]= "2";
			prec[1]="L";
			break;
		case "-" :
			prec[0]= "2";
			prec[1]="L";
			break;
		case "*" :
			prec[0]= "4";
			prec[1]="L";
			break;
		case "/" :
			prec[0]= "4";
			prec[1]="L";
			break;
		case "^" :
			prec[0]= "5";
			prec[1]="R";
			break;
		case "(" :
			prec[0]= "15";
			prec[1]="L";
			break;
		case ")" :
			prec[0]= "15";
			prec[1]="L";
			break;
		case "<" :
			prec[0]= "7";
			prec[1]="L";
			break;
		case ">" :
			prec[0]= "7";
			prec[1]="L";
			break;
		case "=" :
			prec[0]= "0";
			prec[1]="L";
			break;
		case "&" :
			prec[0]= "9";
			prec[1]="L";
			break;
		case "|" :
			prec[0]= "11";
			prec[1]="L";
			break;
		case "!" :
			prec[0]= "8";
			prec[1]="L";
			break;
		case "%" :
			prec[0]= "4";
			prec[1]="L";
			break;
//		case "sin" :
//			b= true;
//			break;
//		case "cos" :
//			b= true;
//			break;
//		case "tan" :
//			b= true;
//			break;
//			
		
		}
		
		return prec;
		
	   }
	
	// recognizer for if it is an operator
   public static boolean Operator_Indentifier (char c){
	   boolean b= false;
	switch (c){
	case '+' :
		b= true;
		break;
	case '-' :
		b= true;
		break;
	case '*' :
		b= true;
		break;
	case '/' :
		b= true;
		break;
	case '^' :
		b= true;
		break;
	case '(' :
		b= true;
		break;
	case ')' :
		b= true;
		break;
	case '<' :
		b= true;
		break;
	case '>' :
		b= true;
		break;
	case '=' :
		b= true;
		break;
	case '&' :
		b= true;
		break;
	case '|' :
		b= true;
		break;
	case '!' :
		b= true;
		break;
	case '%' :
		b= true;
		break;
//	case 'sin' :
//		b= true;
//		break;
//	case '+' :
//		b= true;
//		break;
//	case '+' :
//		b= true;
//		break;
		
	
	}
	
	return b;
	
   }
   public static boolean Operator_Indentifier_String(String c){
	   boolean b= false;
	switch (c){
	case "+" :
		b= true;
		break;
	case "-" :
		b= true;
		break;
	case "*" :
		b= true;
		break;
	case "/" :
		b= true;
		break;
	case "^" :
		b= true;
		break;
	case "(" :
		b= true;
		break;
	case ")" :
		b= true;
		break;
	case "<" :
		b= true;
		break;
	case ">" :
		b= true;
		break;
	case "=" :
		b= true;
		break;
	case "&" :
		b= true;
		break;
	case "|" :
		b= true;
		break;
	case "!" :
		b= true;
		break;
	case "%" :
		b= true;
		break;
	case "sin" :
		b= true;
		break;
	case "cos" :
		b= true;
		break;
	case "tan" :
		b= true;
		break;
		
	
	}
	
	return b;
	
   }
   public static void Shunting (String infix, String postfix_eval) throws IOException{
	   System.out.println("start");
		sorting(infix);

		ArrayList<QueueM> postfix= new ArrayList<QueueM>(); 
		for (int i= 0; i< storage.size(); i++){
			
				postfix.add(PostFix(Seperated(storage.get(i))));
		}
		FileWriter f = new FileWriter(postfix_eval);
		for (int v=0; v<postfix.size();v++){
			StackMethods q = new StackMethods();
			System.out.println("postfix ");
			postfix.get(v).print();
			q=PostFix_Evaluation(postfix.get(v));
			
			// writes everytime with a new line 
			f.write(q.peek().toString() + "\n");
			
		}
		f.close();

	}

   
	public static void main(String[] args) throws IOException {
		Shunting("infix_expr_short.txt", "Ans.txt");
		
		
	}
}
