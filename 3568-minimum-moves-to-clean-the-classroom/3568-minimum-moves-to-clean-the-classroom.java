import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int initialEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) Arrays.fill(row, -1);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        
        int targetMask = (1 << litterCount) - 1;
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(maxEnergy[r][c], -1);
            }
        }
        
        // Queue stores: {r, c, mask, energy}
        Queue<int[]> queue = new LinkedList<>();
        
        int initialMask = 0;
        if (classroom[startR].charAt(startC) == 'L') {
            initialMask |= (1 << litterId[startR][startC]);
        }
        
        queue.offer(new int[]{startR, startC, initialMask, initialEnergy});
        maxEnergy[startR][startC][initialMask] = initialEnergy;
        
        int steps = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int e = curr[3];
                
                if (mask == targetMask) {
                    return steps;
                }
                
                if (e == 0) continue; // Cannot move further without energy
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }
                    
                    int nextMask = mask;
                    char cell = classroom[nr].charAt(nc);
                    
                    if (cell == 'L') {
                        nextMask |= (1 << litterId[nr][nc]);
                    }
                    
                    int nextEnergy = e - 1;
                    if (cell == 'R') {
                        nextEnergy = initialEnergy; // Energy reset
                    }
                    
                    // Only visit if we reach this state with strictly more energy
                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextMask, nextEnergy});
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}