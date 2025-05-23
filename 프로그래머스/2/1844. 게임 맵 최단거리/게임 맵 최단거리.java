import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        
        // 이동 방향
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        
        // 시작점 : 좌표(0, 0), 이동한 칸의 개수를 1로 기본 설정
        queue.offer(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            // 현재 노드
            int[] cur = queue.poll();
            
            // 현재 좌표와 이동한 칸의 개수
            int r = cur[0], c = cur[1], dist = cur[2];
            
            // 목적지 도착 : (0, 0)부터 시작해서 (n - 1, m - 1)가 끝
            if (r == n - 1 && c == m - 1) return dist;
            
            // 다음 노드 : 네 가지 방향이기 때문에 i < 4로 범위 설정
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 이동할 좌표가 맵 내에 존재하고, 벽이 없는 자리일 때
                if (0 <= nr && nr < n && 0 <= nc && nc < m && maps[nr][nc] == 1) {
                    if (!visited[nr][nc]) {
                        queue.offer(new int[] {nr, nc, dist + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        
        return -1;
    }
}