import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        
        // Step 1: Calculate the sum of the longest sequential prefix
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        
        // Step 2: Store elements in a hash set for quick lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Step 3: Find the smallest missing integer >= sum
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}