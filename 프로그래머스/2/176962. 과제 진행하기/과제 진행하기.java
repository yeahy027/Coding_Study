import java.util.*;

class Solution {
    static class Task {
        String name;
        int start;
        int playtime;
        
        Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    public String[] solution(String[][] plans) {
        
        int n = plans.length;
        
        // start 기준으로 plans 정렬
        Task[] tasks = new Task[n];
        
        for (int i = 0; i < n; i++) {
            String name = plans[i][0];
            int start = toMinutes(plans[i][1]);
            int playtime = Integer.parseInt(plans[i][2]);
            
            tasks[i] = new Task(name, start, playtime);
        }
        
        Arrays.sort(tasks, (a, b) -> a.start - b.start);
        
        Stack<Task> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        
        // 총 진행 시간과 실제로 진행한 시간을 뺐을 때 0이 되는 순서대로 answer에 저장
        // 진행 도중 새로운 과제를 실행하는 경우 기존 진행중인 과제를 LIFO 형태로 저장
        for (int i = 0; i < n - 1; i++) {
            Task cur = tasks[i];
            Task next = tasks[i + 1];
            
            int timeGap = next.start - cur.start;
            
            if (cur.playtime <= timeGap) {
                result.add(cur.name);
                
                int remainTime = timeGap - cur.playtime;
                
                while (!stack.isEmpty() && remainTime > 0) {
                    Task prev = stack.pop();
                    
                    // 중단했던 과제를 남은 시간에 끝낸다면
                    if (prev.playtime <= remainTime) {
                        remainTime -= prev.playtime;
                        result.add(prev.name);
                    } else { // 다시 중단하는 경우
                        prev.playtime -= remainTime;
                        stack.push(prev);
                        break;
                    }
                }
            } else {
                cur.playtime -= timeGap;
                stack.push(cur);
            }
        }
        
        result.add(tasks[n - 1].name);
        
        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }
        
        return result.toArray(new String[0]);
    }
    
    private int toMinutes(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}