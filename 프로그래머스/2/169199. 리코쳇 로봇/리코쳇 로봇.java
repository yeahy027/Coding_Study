import java.util.*;

class Solution {
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        // 시작 위치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    q.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }
        
        while (!q.isEmpty()) {
            
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            // 목표 도달
            if (board[x].charAt(y) == 'G') {
                return dist;
            }
            
            // 4방향 이동
            for (int d = 0; d < 4; d++) {
                int nx = x;
                int ny = y;
                
                while (true) {
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];
                    
                    if (tx < 0 || ty < 0 || tx >= n || ty >= m || board[tx].charAt(ty) == 'D') {
                        break;
                    }
                    
                    nx = tx;
                    ny = ty;
                }
                
                if (visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }
        
        return -1;
    }
}