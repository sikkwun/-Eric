package studyalgorithm.level3.twopointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class gemsShopping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] arr = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		
		int[] result = solution(arr);
		System.out.println(result[0] + " " + result[1]);
		
	}

	public static int[] solution(String[] gems) {
		int[] answer = new int[2];
		// ds
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int lf = 0; // 왼쪽 포인터
		int rt = 0; // 오른쪽 포인터
		int min = -1;
		
		List<String> list = Arrays.asList(gems);
		Set<String> set = new HashSet<>(list);
		Iterator<String> itr = set.iterator();
		while(itr.hasNext()) {	// 보석 종류 셋팅
			map.put(itr.next(), 1);
		}
		
		while(true){
			if(!map.containsValue(0)) {
				System.out.println("testffff");
				if(min == -1) {
					answer[0] = lf;
					answer[1] = rt;
				} else {
					if(rt-lf < min) {
						answer[0] = lf;
						answer[1] = rt;
						min = rt-lf;
					}
				}
			}
			break;
		}
		
		
		
//		while (st != ed) { // 첫째 값과 마지막 값이 같으면 종료
//			if (map.get(gems[ed]) != 1) {
//				map.put(gems[ed], map.get(gems[ed]) - 1);
//				ed--;
//			} else if (map.get(gems[st]) != 1) {
//				map.put(gems[st], map.get(gems[st]) - 1);
//				st++;
//			} else {
//				break;
//			}
//		}

//		answer = new int[] { st + 1, ed + 1 };
		return answer;
	}

}
