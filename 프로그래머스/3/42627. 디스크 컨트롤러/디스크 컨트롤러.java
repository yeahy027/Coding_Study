import java.util.*;

class Solution {
    
    class Job {
        int start;
        int length;
        
        Job(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }
    
    public int solution(int[][] jobs) {
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> a.length - b.length);
        
        int time = 0;
        int idx = 0;
        int totalTime = 0;
        int count = 0;
        
        while (count < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.offer(new Job(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if (!pq.isEmpty()) {
                Job cur = pq.poll();
                time += cur.length;
                totalTime += time - cur.start;
                count++;
            } else {
                time = jobs[idx][0];
            }
        }
        
        return totalTime / jobs.length;
    }
}