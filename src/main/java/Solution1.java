import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Solution1 implements Solution {
    @Override
    public int largestRectangleArea(int[] heights) {

        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] == 0) {
                zeros.add(i);
            }
        }
        zeros.add(heights.length);
        zeros.add(0, 0);

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < zeros.size()-1; i++) {
            int[] copy;
            if (heights[zeros.get(i)] != 0) {
                copy = Arrays.copyOfRange(heights, zeros.get(i), zeros.get(i + 1));
            } else if (zeros.get(i)+1 < heights.length) {
                copy = Arrays.copyOfRange(heights, zeros.get(i)+1, zeros.get(i + 1));
            }
            result = Math.max(result, largestRect(copy));
        }

        return result == Integer.MIN_VALUE ? 0 : result;
    }

    private int largestRect(int[] heights) {
        List<Integer> indices = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < heights.length; i++) {
            if (indices.isEmpty() || heights[i] < heights[i-1]) {
                indices.add(i);
            }
            min = Math.min(min, heights[i]);
        }
        indices.add(heights.length);

        int result = min*heights.length;

        for (int i = 0; i < indices.size()-1; i++) {
            int last = indices.get(i+1);
            int j = indices.get(i);
            while (j < last) {
                result = Math.max(result, (last - j) * heights[j]);
                j++;
            }
        }

        List<Integer> rindices = new ArrayList<>();
        for (int i = heights.length-1; i >= 0; i--) {
            if (rindices.isEmpty() || heights[i] < heights[i+1]) {
                rindices.add(i);
            }
        }
        rindices.add(-1);

        for (int i = 0; i < rindices.size()-1; i++) {
            int left = rindices.get(i+1);
            int j = rindices.get(i);
            while (j > left) {
                result = Math.max(result, (j-left)*heights[j]);
                j--;
            }
        }

        return result;
    }
}
