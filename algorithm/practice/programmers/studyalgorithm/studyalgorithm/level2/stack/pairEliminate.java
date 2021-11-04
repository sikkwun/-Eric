package programmers.level2.stack;
import java.util.*;

public class pairEliminate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "cbcb";
		
		int result = solve(str);
		System.out.println(result);
	}

	private static int solve(String str) {
		
		// ds
		int answer = 0;
		Stack<Character> st = new Stack<Character>();
		char[] cArr = str.toCharArray();
		
		for(char c : cArr) {
			
			if(st.isEmpty()) {
				st.push(c);
			} else {
				if(st.peek().equals(c)) {
					st.pop();
				} else {
					st.push(c);
				}
			}
			
		}
		
		answer = st.size() == 0 ? 1 : 0;  

		return answer;
	}

}
