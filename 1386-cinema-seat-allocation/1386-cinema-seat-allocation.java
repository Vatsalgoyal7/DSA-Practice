import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reservedMap = new HashMap<>();

        // Record reserved seats using bitwise OR
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                reservedMap.put(row, reservedMap.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Default: every row with no reservations can seat 2 groups
        int totalGroups = (n - reservedMap.size()) * 2;

        // Bitwise masks for checking 4 contiguous seats
        int leftMask   = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5); // 0b000011110 -> seats 2, 3, 4, 5
        int rightMask  = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9); // 0b011110000 -> seats 6, 7, 8, 9
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); // 0b001111000 -> seats 4, 5, 6, 7

        for (int mask : reservedMap.values()) {
            boolean leftAvailable = (mask & leftMask) == 0;
            boolean rightAvailable = (mask & rightMask) == 0;

            if (leftAvailable && rightAvailable) {
                totalGroups += 2;
            } else if (leftAvailable || rightAvailable || (mask & middleMask) == 0) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}