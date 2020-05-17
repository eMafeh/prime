package util;

import java.util.Arrays;

/**
 * 范围 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23
 * 100 次 耗时 90313 ms
 */
public class PrimeProducer {
//    private static final int[] primes = init(2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23);

    public static int[] init(int area) {
        if (area < 15) return new int[]{2, 3, 5, 7, 11, 13};
        boolean[] is = new boolean[area];
        is[0] = true;
        int length = 1;
        int nextP = 1;
        int[] result = new int[(int) (1.3 * is.length / Math.log(is.length))];
        int index = 0;
        while (length != is.length) {
            while (!is[nextP++ % length]) ;
            result[index++] = nextP;
            length = group(nextP, is, length);
            for (int i = length / nextP - 1; i >= 0; i--) {
                if (is[i]) is[(i + 1) * nextP - 1] = false;
            }
        }
        while (length > nextP * nextP) {
            while (!is[nextP++]) ;
            result[index++] = nextP;
            int temp = length / nextP - 1;
            if (temp % 2 != 0) temp--;
            for (int i = temp; i >= 0; i -= 2) {
                if (is[i]) is[(i + 1) * nextP - 1] = false;
            }
        }
        for (int i = nextP + 1; i < is.length; i += 2) {
            if (is[i])
                result[index++] = i + 1;
        }
        return Arrays.copyOf(result, index);
    }

    private static int group(int nextP, boolean[] is, int length) {
        int nowL = length;
        int i = nextP * length;
        length = Math.min(i < 0 ? Integer.MAX_VALUE : i, is.length);
        while (nowL * 2 < length) {
            System.arraycopy(is, 0, is, nowL, nowL);
            nowL *= 2;
        }
        System.arraycopy(is, 0, is, nowL, length - nowL);
        return length;
    }
}
