import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        Map<String, Set<String>> re_list = new HashMap<>();
        for (String id : id_list) {
            re_list.put(id, new HashSet<>());
        }
        
        for (String rep : report) {
            String[] parts = rep.split(" ");
            String u_id = parts[0];
            String c_id = parts[1];
            
            re_list.get(c_id).add(u_id);
        }
        
        Map<String, Integer> reportCount = new HashMap<>();
        for (String id : id_list) {
            reportCount.put(id, re_list.get(id).size());
        }
        
        int[] answer = new int[id_list.length];
        Map<String, Integer> res = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            res.put(id_list[i], i);
        }
        
        for (String id : id_list) {
            if(reportCount.get(id) >= k) {
                for (String reporter : re_list.get(id)) {
                    int idx = res.get(reporter);
                    answer[idx]++;
                }
            }
        }
        
        return answer;
    }
}