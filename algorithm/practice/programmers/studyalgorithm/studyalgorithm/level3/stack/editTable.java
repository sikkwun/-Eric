package studyalgorithm.level3.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class editTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		String result = solution(n,k,cmd);
		
		System.out.println(result);
		
	}

	public static String solution(int n, int k, String[] cmd) {
		String answer = "";
		Stack<Integer> st = new Stack<Integer>();
		boolean[] list = new boolean[n];
		int idx = k;
		int move = 0;
		
		for (int i = 0; i < n; i++)
			list[i] = true;	// 초기화
		
		for (String str : cmd) {
			if (str.length() != 1) {
				String[] sp = str.split(" ");
				if (sp[0].equals("U")) {
					move -= Integer.parseInt(sp[1]);
				} else {
					move += Integer.parseInt(sp[1]);
				}
			} else {
				if (str.equals("C")) {
					idx = getIdx(idx, move, list);			// idx 이동
					list[idx] = false;
					st.push(idx);
					move = 0;
					
					boolean dirCheck = true;
					
					for(int i=idx+1; i< list.length; i++) {
						if(list[i]) {
							dirCheck = false;
							idx = i;
							break;
						}
					}
					
					if(dirCheck) {
						for(int i=idx-1; i>=0; i--) {
							if(list[i]) {
								idx = i;
								break;
							}
						}
					}

				} else {
					idx = getIdx(idx, move, list);			// idx 이동
					move = 0;
					int tmp = st.pop();
					list[tmp] = true;
				}

			}
		}

		for (boolean str : list)
			answer += str == true ? "O" : "X";
		return answer;
	}

	static int getIdx(int idx, int move, boolean[] list) {
		int rtnIdx = idx;
		int i = move > 0 ? 1 : -1;
		while (move != 0) {
			rtnIdx += i;
			if (list[rtnIdx] == false)
				continue;
			move -= i;
		}
		return rtnIdx;
	} 

}
