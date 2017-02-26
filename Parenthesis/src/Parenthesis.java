import java.util.Scanner;
import java.util.Stack;


public class Parenthesis {

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
			int noOfOpenParenthesis = 0;
			//int noOfClosingParenthesis = 0;
			
			int i = 0 ;
			while(i < expression.length()){
				if(expression.charAt(i) == '(' && i == 0){
					i++;
					noOfOpenParenthesis++;
					
					char c = expression.charAt(i);
					
					while(noOfOpenParenthesis != 0){
						c = expression.charAt(i);
						if(c == '(')
							noOfOpenParenthesis++;
						else if(c == ')')
							noOfOpenParenthesis--;
						else
							answer += expression.charAt(i);
						i++;
						
					}
				}else if(expression.charAt(i) == '(' && i != 0){
					noOfOpenParenthesis = 0;
					answer += '(';
					noOfOpenParenthesis++;
					i++;
					
					char c = expression.charAt(i);
					
					while(noOfOpenParenthesis != 0){
						c = expression.charAt(i);
						if(c == '(')
							noOfOpenParenthesis++;
						else if(c == ')')
							noOfOpenParenthesis--;
						else
							answer += expression.charAt(i);
						i++;
					}
					
					answer += ')';
					
				}else{
					answer += expression.charAt(i);
					i++;
				}
			}	
			System.out.println(answer);
		return answer;
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
