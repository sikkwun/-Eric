package programmers.level3.twopoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class gemShopping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] gems = {"A", "B", "C", "B", "F", "D", "A", "F", "B", "D", "B"}; //{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};//{"AA", "AB", "AC", "AA", "AC"}; //{"A", "B" ,"B", "C", "A", "B", "C", "A","B","C"};
		int[] result = solution(gems);
		
		System.out.println(result[0] + " " + result[1]);
		
	}
	
	public static int[] solution(String[] gems) {
        int[] answer = new int[2];
		// ds
		HashMap<String, Integer> gemsCnt = new HashMap<String, Integer>();
		HashSet<String> set = new HashSet<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int lf = 0; // 왼쪽 포인터
		int rt = 0; // 오른쪽 포인터
		int len = gems.length;
		int min = -1;
		
		for(int i=0; i<len; i++) {
			set.add(gems[i]);
			gemsCnt.put(gems[i], gemsCnt.getOrDefault(gems[i], 0) + 1);
		}
			
		if(set.size() == len) {
			return new int[] {1, len};
		}
		
		map.put(gems[rt], 1);
        
		while(lf < len){

			if(set.size() == map.size()) {
				if(min == -1) {
					answer[0] = lf;
					answer[1] = rt;
					min = rt - lf;
				} else {
					
					if(rt-lf < min) {
						answer[0] = lf;
						answer[1] = rt;
						min = rt-lf;
					}
				}
				if(map.get(gems[lf]) == 1) {
					map.remove(gems[lf]);
				} else {
					map.put(gems[lf], map.getOrDefault(gems[lf], 0) - 1);
					gemsCnt.put(gems[lf], gemsCnt.getOrDefault(gems[lf], 0) - 1);
				}
				if(gemsCnt.get(gems[lf]) == 0) break;
				lf++;
			} else {
				if(rt+1 == len) break;
				rt++;
				map.put(gems[rt], map.getOrDefault(gems[rt], 0) + 1);
			}
			
		}

		answer = new int[] { answer[0]+1, answer[1]+1};
		return answer;
        
    }

}
