import java.util.*;

class Solution {
    
    class Job {
        int s;
        int l;
        
        Job(int s, int l) {
            this.s = s;
            this.l = l;
        }
    }
    
    public int solution(int[][] jobs) {
        
        // 요청 시간 기준으로 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        // 우선순위 큐 : 작업 소요 시간이 짧은 순
        PriorityQueue<Job> pq = new PriorityQueue<>((a, b) -> a.l - b.l);
        
        int time = 0;       // 현재 시간
        int idx = 0;        // jobs 배열 인덱스
        int totalTime = 0;  // 총 대기 시간
        int count = 0;      // 처리된 작업 수
        
        while (count < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.offer(new Job(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if (!pq.isEmpty()) {
                Job cur = pq.poll();
                time += cur.l;
                totalTime += time - cur.s;
                count++;
            } else {
                time = jobs[idx][0];
            }
        }
        
        return totalTime / jobs.length;
    }
}