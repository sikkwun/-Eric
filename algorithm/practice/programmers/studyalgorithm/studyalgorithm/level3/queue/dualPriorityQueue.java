package studyalgorithm.level3.queue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class dualPriorityQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr = {"I 7","I 5","I -5","D -1"};
		
		int[] result = solution(arr);
		System.out.println(result[0] + " " + result[1]);

	}
	
	public static int[] solution(String[] operations) {
        int[] answer = new int[2];
        // ds
        Queue<Integer> minQ = new PriorityQueue<Integer>();
        Queue<Integer> maxQ = new PriorityQueue<Integer>(Collections.reverseOrder());
        String[] sp = new String[2];
        
        for(int i=0; i<operations.length; i++) {
        	sp = operations[i].split(" ");
        	int tmp = Integer.parseInt(sp[1]);
	        if(sp[0].equals("I")) {
	        	minQ.offer(tmp);
	        	maxQ.offer(tmp);
	        } else if(minQ.isEmpty()) {
	        	continue;
	        } else {
	        	if(tmp == 1) {
	        		minQ.remove(maxQ.poll());
	        	} else {
	        		maxQ.remove(minQ.poll());
	        	}
	        }
        }
        
        if(!minQ.isEmpty()) {
        	answer[0] = maxQ.poll();
        	answer[1] = minQ.poll();
        }
        return answer;
    }

}
