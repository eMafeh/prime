package util;

import java.util.Arrays;

/**
 * 范围 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23
 * 100 次 耗时 90313 ms
 */
public class PrimeProducerUpdate {
    //    private static final int[] primes = init(2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23);
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int area = 3_0000_0000;
            long l = System.currentTimeMillis();
//        int[] number = NetPrime.calculateNumber(area);
//            int[] ints = PrimeProducer.init(area);
            int[] init = init(area);
            System.out.println(l - System.currentTimeMillis());
//            for (int j = 0; j < ints.length; j++) if (ints[j] != init[j]) break;
//        System.out.println(Arrays.toString(init));
//        System.out.println(Arrays.toString(ints));


        }
    }

    public static int[] init(int area) {
        if (area <= 30) return new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        byte[] is = new byte[(area - 1) / 6 + 1];

        int[] result = new int[(int) (1.3 * area / Math.log(area))];
        int resultIndex = 0;
        result[resultIndex++] = 2;
        result[resultIndex++] = 3;
        result[resultIndex++] = 5;
        //增长到p 5,l 30
        int[] nextP = {0, 0};
        int[] back = {0, 0};
        int length = 30;
        is[0] = 2;
        is[1] = 34;
        is[2] = 34;
        is[3] = 34;
        is[4] = 32;
        int p = 0;
        while (length > p * p) {
            p = move(nextP, is, 1);
            result[resultIndex++] = p;
            if (length != is.length * 6) length = group(p, is, length);
            clear(is, p, length, back);
        }
        long l = System.currentTimeMillis();
        while (true) {
            p = move(nextP, is, 1);
            if (p > 0) result[resultIndex++] = p;
            else break;
        }
        System.out.println(l - System.currentTimeMillis());
        return Arrays.copyOf(result, resultIndex);
    }

    private static void clear(final byte[] is, int p, int length, int[] back) {
        back[0] = (length / p - 1) / 6 + 1;
        back[1] = 0;
        int last = move(back, is, -1);
        if (back[1] * p >= length) back[1] = 0;
        if (last * p >= length) last = move(back, is, -1);
        while (last != -1) {
            setFalse(is, last * p);
            last = move(back, is, -1);
        }
    }

    private static int move(final int[] nextP, final byte[] is, int flag) {
        if (nextP[1] != 0) {
            int i = nextP[1];
            nextP[1] = 0;
            return i;
        }
        int i = nextP[0] += flag;
        if (i < 0 || i >= is.length) {
            return -1;
        }
        switch (is[i]) {
            //空
            case 0: {
                return move(nextP, is, flag);
            }
            //1
            case 2: {
                return nextP[0] * 6 + 1;
            }
            //1 5
            case 34: {
                nextP[1] = nextP[0] * 6 + 5;
                return nextP[0] * 6 + 1;
            }
            // 5
            case 32: {
                return nextP[0] * 6 + 5;
            }
            default:
                throw new RuntimeException();
        }
    }

    private static int group(int nextP, byte[] is, int length) {
        int nowL = length / 6;
        int target = Math.min(length / 6 * nextP, is.length);
        while (nowL * 2 < target) {
            System.arraycopy(is, 0, is, nowL, nowL);
            nowL *= 2;
        }
        System.arraycopy(is, 0, is, nowL, target - nowL);
        return target * 6;
    }

    private static void setFalse(byte[] is, int index) {
        is[index / 6] &= (~(1 << (index % 6)));
    }
}
