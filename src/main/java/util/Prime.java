package util;

import java.util.ArrayList;
import java.util.List;

public class Prime extends Number implements Comparable<Prime> {
    private final static Prime[] CACHE = new Prime[1000];
    private final static int[] primes = PrimeProducer.init(CACHE.length);
    private final static List<Prime> list = new ArrayList<>();

    static {
        for (int p : primes) {
            Prime prime = new Prime(p);
            CACHE[p - 1] = prime;
            list.add(prime);
        }
    }

    private final int i;

    private Prime(int i) {
        this.i = i;
    }

    public static Prime getInstance(int i) {
        return CACHE[i - 1];
    }

    public static List<Prime> getInstances(int length) {
        ArrayList<Prime> result = new ArrayList<>();
        for (Prime prime : Prime.list) {
            if (length == 1) break;
            int p = prime.intValue();
            while (length % p == 0) {
                length /= p;
                result.add(prime);
            }
        }
        return result;
    }

    @Override
    public int compareTo(final Prime o) {
        return Integer.compare(this.i, o.i);
    }

    public int multiplyExact(int l) {
        return Math.multiplyExact(i, l);
    }

    @Override
    public int intValue() {
        return i;
    }

    @Override
    public long longValue() {
        return i;
    }

    @Override
    public float floatValue() {
        return i;
    }

    @Override
    public double doubleValue() {
        return i;
    }

    @Override
    public String toString() {
        return i + "";
    }
}
