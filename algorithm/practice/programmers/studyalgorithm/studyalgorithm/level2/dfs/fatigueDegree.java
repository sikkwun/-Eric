package programmers.level2.dfs;
import java.util.*;

public class fatigueDegree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int k = 100;
		int[][] dungeons = {{80,20},{50,40},{30,10}};
		
		int result = solve(k, dungeons);
		System.out.println(result);
		
	}

	private static int solve(int k, int[][] dungeons) {
		
		// ds
		int answer = -1;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		dfs(dungeons, list, k, 0, 0, 0);
		
		answer = max;
		
		return answer;
	}
	
	public static int max = -1;

	private static void dfs(int[][] dungeons, ArrayList<Integer> list, int curgaze, int minr, int reduce, int cnt) {
		
		if(curgaze < minr) {
			cnt--;
		} else {
			curgaze -= reduce;
		}
		
		if(list.size() == dungeons.length) {
			max = Math.max(max, cnt);
			return;
		}
		
		for(int i=0; i<dungeons.length; i++) {
			if(max > dungeons.length - list.size() + cnt) {
				break;
			}
			
			if(list.contains(i)) continue;
			list.add(i);
			dfs(dungeons, list, curgaze, dungeons[i][0], dungeons[i][1], cnt+1);
			list.remove(list.size()-1);
		}
		
	}

}
