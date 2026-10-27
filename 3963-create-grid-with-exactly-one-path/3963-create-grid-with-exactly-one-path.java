import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        
        // Fill the grid with obstacles initially
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }
        
        // Open the top row completely (from column 0 to n - 1)
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        
        // Open the rightmost column completely (from row 0 to m - 1)
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Convert char[][] to String[]
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}