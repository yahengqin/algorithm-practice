/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package org.tomato.algorithmpractice;

import org.junit.jupiter.api.Test;

/**
 * 1020. 飞地的数量
 *
 * @author qinqixiang
 * @version $Id: FlyLand.java, v 0.1 2020年07月02日 9:42 PM qinqixiang Exp $
 */
public class FlyLand {

    @Test
    public void test() {
        int[][] array = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}};
        int[][] arrayOther = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        assert numEnclaves(array) == 0;
        assert numEnclaves(arrayOther) == 3;
    }

    public int numEnclaves(int[][] sourceArray) {

        for (int i = 0; i < sourceArray[0].length; i++) {
            findLive(sourceArray, 0, i);
        }
        for (int i = 0; i < sourceArray[0].length; i++) {
            findLive(sourceArray, sourceArray.length - 1, i);
        }

        for (int i = 0; i < sourceArray.length; i++) {
            findLive(sourceArray, i, 0);
        }

        for (int i = 0; i < sourceArray.length; i++) {
            findLive(sourceArray, i, sourceArray[0].length - 1);
        }

        int result = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            for (int j = 0; j < sourceArray[0].length; j++) {
                if (sourceArray[i][j] == 1) {
                    result++;
                }
            }
        }

        return result;
    }

    private void findLive(int[][] source, int i, int j) {
        if (source[i][j] == 1 && (i == 0 || j == 0 || i == source.length - 1 || j == source[0].length - 1)) {
            findRelateLive(source, i, j);
        }
    }

    private void findRelateLive(int[][] source, int i, int j) {
        if (i >= 0 && j >= 0 && i < source.length && j < source[0].length && source[i][j] == 1) {
            source[i][j] = 2;
            findRelateLive(source, i - 1, j);
            findRelateLive(source, i + 1, j);
            findRelateLive(source, i, j - 1);
            findRelateLive(source, i, j + 1);
        }
    }

}