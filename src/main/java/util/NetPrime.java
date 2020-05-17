package util;

import java.util.Arrays;

/**
 * 范围 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23
 * 100 次 耗时 141084 ms
 */
public class NetPrime {
    public static int[] calculateNumber(int Nmax) {
        boolean[] isPrime = new boolean[Nmax + 1];
        int[] prime = new int[Nmax / 10];
        int totalPrimes = 1;
        for (int i = 3; i <= Nmax; i += 2)
            isPrime[i] = true;
        isPrime[2] = true;
        prime[0] = 2;
        for (int i = 3; i <= Nmax; i += 2) {
            if (isPrime[i])
                prime[totalPrimes++] = i;
            for (int j = 1; i * prime[j] <= Nmax && j < totalPrimes; j++) {
                isPrime[i * prime[j]] = false;
                if (i % prime[j] == 0)
                    break;
            }
        }
        return Arrays.copyOf(prime, totalPrimes);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            long l = System.currentTimeMillis();
//            int[] primes = PrimeProducer.init(2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23);
            int[] ints = NetPrime.calculateNumber(2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23);
//        System.out.println(Arrays.equals(ints, primes));
            System.out.println(l - System.currentTimeMillis());
        }
    }
}