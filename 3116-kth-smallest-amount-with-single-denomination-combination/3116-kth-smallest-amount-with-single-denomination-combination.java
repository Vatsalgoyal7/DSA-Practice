class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) coins[0] * k;
        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countAmounts(coins, mid) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long countAmounts(int[] coins, long target) {
        int n = coins.length;
        long count = 0;

        // Iterate through all non-empty subsets using bitmasking
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    bitCount++;
                    lcm = getLCM(lcm, coins[i]);
                    if (lcm > target) break; // Avoid unnecessary calculations
                }
            }

            if (bitCount % 2 == 1) {
                count += target / lcm;
            } else {
                count -= target / lcm;
            }
        }

        return count;
    }

    private long getGCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long getLCM(long a, long b) {
        return (a / getGCD(a, b)) * b;
    }
}