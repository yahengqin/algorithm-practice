/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.tomato.algorithmpractice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author qinqixiang
 * @version $Id: FindKthLargest.java, v 0.1 2020年06月29日 9:39 PM qinqixiang Exp $
 */
@SuppressWarnings("LoopConditionNotUpdatedInsideLoop")
public class FindKthLargest {

    @Test
    public void test() {
        assert findKthLargest(new int[] {1, 22, 3, 5, 6}, 2) == 6;
        assert findKthLargestOfFastSort(new int[] {2, 22, 2, 2, 2}, 2) == 2;
        assert findKthLargestOfHeap(new int[] {1, 22, 3, 5, 6}, 2) == 6;
        assert findKthLargestOfHeap(new int[] {3, 2, 1, 5, 6, 4}, 2) == 5;
        assert findKthLargestOfHeap(new int[] {2, 22, 2, 2, 2}, 2) == 2;

    }

    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).sorted().boxed().collect(Collectors.toList());
        return list.get(list.size() - k);
    }

    // 堆排序
    public int findKthLargestOfHeap(int[] nums, int k) {
        int heap = nums.length - 1;
        int result = 0;

        for (int i = 0; i < k; i++) {
            nums = buildMaxHeap(nums, heap);
            result = nums[0];
            swap(nums, 0, heap);
            heap--;
        }

        return result;
    }

    private int[] buildMaxHeap(int[] nums, int endLength) {
        // 完全二叉树计算第一个非叶子节点公式：数组长度/2 -1
        int key = ((endLength + 1) / 2) - 1;

        while (key >= 0) {
            improveHeap(nums, key, endLength);
            key--;
        }

        return nums;
    }

    private void improveHeap(int[] nums, int point, int endLength) {
        int l = 2 * point + 1;
        int r = 2 * point + 2;
        int largest = point;

        if (l > 0 && l <= endLength && nums[largest] < nums[l]) {
            largest = l;
        }
        if (r > 0 && r <= endLength && nums[largest] < nums[r]) {
            largest = r;
        }
        if (largest != point) {
            swap(nums, largest, point);
            improveHeap(nums, largest, endLength);
        }
    }

    // 缩短寻找速度，最好二分一下只处理目标分段
    public int findKthLargestOfFastSort(int[] nums, int k) {
        int key = fastSort(nums, 0, nums.length - 1);

        while (key != nums.length - k) {
            key = (key > nums.length - k) ? fastSort(nums, 0, key - 1) : fastSort(nums, key + 1, nums.length - 1);
        }

        return nums[key];
    }

    public int fastSort(int[] nums, int left, int right) {
        int key = left;

        while (left < right) {
            while (nums[right] >= nums[key] && right > key) {
                right--;
            }
            swap(nums, key, right);
            key = right;

            while (nums[left] <= nums[key] && left < key) {
                left++;
            }
            swap(nums, key, left);
            key = left;
        }

        return key;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}