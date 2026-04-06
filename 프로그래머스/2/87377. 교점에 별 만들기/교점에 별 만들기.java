import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        
        List<long[]> points = new ArrayList<>();
        int n = line.length;
        
        for (int i = 0; i < n; i++) {
            long A = line[i][0];
            long B = line[i][1];
            long C = line[i][2];
            
            for (int j = i + 1; j < n; j++) {
                long D = line[j][0];
                long E = line[j][1];
                long F = line[j][2];
                
                long bm = A * E - B * D;
                
                if (bm == 0) continue;
                
                long xbj = B * F - C * E;
                long ybj = C * D - A * F;
                
                if (xbj % bm != 0 || ybj % bm != 0) continue;
                
                long x = xbj / bm;
                long y = ybj / bm;
                
                points.add(new long[]{x, y});
            }
        }
        
        long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;
        
        for (long[] p : points) {
            minX = Math.min(minX, p[0]);
            maxX = Math.max(maxX, p[0]);
            minY = Math.min(minY, p[1]);
            maxY = Math.max(maxY, p[1]);
        }
        
        int width = (int)(maxX - minX + 1);
        int height = (int)(maxY - minY + 1);
        
        char[][] grid = new char[height][width];
        
        for (int i = 0; i < height; i++) {
            Arrays.fill(grid[i], '.');
        }
        
        for (long[] p : points) {
            int x = (int)(p[0] - minX);
            int y = (int)(maxY - p[1]); // y축 뒤집기
            
            grid[y][x] = '*';
        }
        
        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(grid[i]);
        }
        
        return answer;
    }
}