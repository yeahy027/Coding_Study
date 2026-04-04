import java.util.*;

class Solution {
    
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    
    public int solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        
        char[][] grid = new char[n][m];
        int startX = 0, startY = 0;
        int levelX = 0, levelY = 0;
        
        for (int i = 0; i < n; i++) {
           for (int j = 0; j < m; j++) {
               grid[i][j] = maps[i].charAt(j);
               
               if (grid[i][j] == 'S') {
                   startX = i;
                   startY = j;
               }
               
               if (grid[i][j] == 'L') {
                   levelX = i;
                   levelY = j;
               }
           }
        }
        
        int toLevel = bfs(grid, startX, startY, 'L');
        if (toLevel == -1) return -1;
        
        int toExit = bfs(grid, levelX, levelY, 'E');
        if (toExit == -1) return -1;
        
        return toLevel + toExit;
    }
    
    public int bfs(char[][] grid, int sr, int sc, char target) {
        int n = grid.length;
        int m = grid[0].length;
        
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            
            if (grid[r][c] == target) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    if (!visited[nr][nc] && grid[nr][nc] != 'X') {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, dist + 1});
                    }
                }
            }
        }
        
        return -1;
        
    }
}