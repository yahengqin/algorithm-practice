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
        System.out.println(findKthLargest(new int[] {1, 22, 3, 5, 6}, 2));
        System.out.println(findKthLargest(new int[] {1, 22, 3, 5, 6}, 2));
        System.out.println(findKthLargestOfFastSort(new int[] {2, 22, 2, 2, 2}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = Arrays.stream(nums).sorted().boxed().collect(Collectors.toList());
        return list.get(list.size() - k);
    }

    // TODO 堆排序
    public int findKthLargestOfHeap(int[] nums, int k) {

        return 0;
    }

    // 所见寻找速度，最好二分一下只处理目标分段
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

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}