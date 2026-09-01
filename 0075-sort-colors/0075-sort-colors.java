class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap nums[low] and nums[mid], then advance both pointers
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // 1 is in the right place, just advance the mid pointer
                mid++;
            } else {
                // Swap nums[mid] and nums[high], then decrement high pointer
                // (Do not increment mid, as the swapped element needs to be inspected)
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}