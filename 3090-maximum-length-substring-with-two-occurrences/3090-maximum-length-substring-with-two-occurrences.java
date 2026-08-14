class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);
            count[curr - 'a']++;

            // Shrink the window if any character count exceeds 2
            while (count[curr - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            // Update maximum valid substring length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}