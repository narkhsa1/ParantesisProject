import java.util.Scanner;
import java.util.Stack;


public class ParenthesisNew {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputStr = "";
		String expression = "", operators = "";
		String[] tokens;
		
		while(sc.hasNext()){
			inputStr = sc.nextLine();
			tokens = inputStr.split("/");
			expression = tokens[0].replaceAll(" ", "");
			operators = tokens[1].replaceAll(" ", "");
			operators = operators.replaceAll("RR", "");
			
			for(int i = 0; i< operators.length();i++){
				if(operators.charAt(i) == 'R')
					expression = reverseString(expression);
				else
					expression = simplifyString(expression);
			}
			
			System.out.println(expression);
		}
	}

	private static String simplifyString(String expression) {
			Stack<Character> st = new Stack<Character>();
			String answer = "";
			int open = 0;
			int close = 0;
			String result = "";
			int i = 0;
			String intermediate = "";
			boolean start = false;
			
			while(i < expression.length()){
				if(expression.charAt(i) == '('){
					if(i == 0)
						start = true;
					else
						start = false;
					intermediate += '(';
					open++;
				}else if(expression.charAt(i) == ')'){
					intermediate += ')';
					close++;
				}else{
					if(open == 0){
						answer += expression.charAt(i);
					}else{
						intermediate += expression.charAt(i);
					}
				}
				
				if(open == close && open > 0){
					//System.out.println(intermediate);
					String temp = recursiveSimplify(intermediate);
					if(start == false){
						answer += '(' + temp + ')';
					}else{
						answer += temp;
					}
					start = false;
					//System.out.println(answer);
					//break;
					open = 0;
					close = 0;
					intermediate = "";
				}
				
				i++;
			}
			
		return answer;
	}

	private static String recursiveSimplify(String intermediate) {
		
		System.out.println("in recursive simplify" + intermediate);
		
		/*if(intermediate.length() == 2)
			return intermediate;*/
		
		
		int open = 0;
		int close = 0;
		String str = "";
		int i = 1;
		
		if(intermediate.charAt(i) == '('){
			while(i < intermediate.length()){
				if(intermediate.charAt(i) == '('){
					str += '(';
					open++;
				}else if(intermediate.charAt(i) == ')'){
					str += ')';
					close++;
				}else{
					str += intermediate.charAt(i);
				}
				
				if(open == close){
					break;
				}
				i++;
			}
			i++;
		}else{
			return intermediate.substring(1, intermediate.length() - 1);
		}
		
		
		return recursiveSimplify(str) + intermediate.substring(i, intermediate.length() - 1);
	}

	private static String reverseString(String expression) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = expression.length() - 1; i >=0; i--){
			if(expression.charAt(i) == ')')
				sb.append('(');
			else if(expression.charAt(i) == '(')
				sb.append(')');
			else
				sb.append(expression.charAt(i));
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}
}
