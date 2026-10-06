class Solution {
    public int lastStoneWeightII(int[] stones) {
        int totalSum = 0;
        for (int stone : stones) {
            totalSum += stone;
        }

        // We want to find a subset sum as close to totalSum / 2 as possible
        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        
        // Base case: a subset sum of 0 is always achievable with an empty set
        dp[0] = true;

        for (int stone : stones) {
            for (int j = target; j >= stone; j--) {
                dp[j] = dp[j] || dp[j - stone];
            }
        }

        // Find the largest achievable subset sum <= totalSum / 2
        int maxSubsetSum = 0;
        for (int j = target; j >= 0; j--) {
            if (dp[j]) {
                maxSubsetSum = j;
                break;
            }
        }

        // The remaining stone weight is the difference between the two subsets
        return totalSum - 2 * maxSubsetSum;
    }
}