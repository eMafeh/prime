package loop.circle;

import util.Prime;

import java.util.*;

public class Snapshot {
    private static final Map<Integer, Snapshot> CACHE = new HashMap<>();
    public final int length;
    private final List<Prime> primes;
    private final boolean[] values;

    private Snapshot(final int length, final List<Prime> primes, final boolean[] values) {
        this.length = length;
        this.primes = primes;
        this.values = values;
    }

    public List<Integer> toList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i]) list.add(i + 1);
        }
        return list;
    }

    static {
        CACHE.put(1, new Snapshot(1, Collections.emptyList(), new boolean[]{true}));
    }

    public static Snapshot getInstance(int length) {
        List<Prime> primes = Prime.getInstances(length);
        Snapshot result = CACHE.get(1);
        for (Prime prime : primes) {
            result = result.grow(prime);
        }
        return result;
    }

    public Snapshot grow(Prime prime) {
        int length = prime.multiplyExact(this.length);
        return CACHE.computeIfAbsent(length, l -> {
            List<Prime> primes = new ArrayList<>(this.primes);
            boolean[] booleans = new boolean[l];
            if (primes.contains(prime)) {
                for (int i = 0; i < prime.intValue(); i++) {
                    System.arraycopy(values, 0, booleans, i * values.length, values.length);
                }
            } else {
                primes.add(prime);
                for (int i = 0; i < values.length; i++) {
                    if (values[i]) {
                        int gi = prime.multiplyExact(i + 1);
                        for (int j = 1; j < prime.intValue(); j++) {
                            booleans[(this.length * j + gi) % l - 1] = true;
                        }
                    }
                }
            }
            return new Snapshot(l, primes, booleans);
        });
    }

    @Override
    public String toString() {
        List<Integer> list = toList();
        return length + ":" + list.size() + list.toString();
    }

    //包逆不包顺
    public List<Integer> toList(int b, int bd, int l, int ld, int times) {
        //扩增长度
        int tl = this.length * times;
        //校验端点是否是整数
        if (tl * b % bd != 0 || tl * l % ld != 0) throw new RuntimeException("扩增后端点必须为整点");
        List<Integer> result = new ArrayList<>();
        //开始端点
        int begin = tl * b / bd;
        //结束端点
        int end = tl * l / ld + tl * b / bd;
        end = end % times == 0 ? end / times - 1 : end / times;
        for (int i = begin % times == 0 ? begin / times : begin / times + 1; i <= end; i++) {
            if (values[(i - 1 + length) % length]) {
                int bi = i * times;
                result.add(bi - begin);
            }
        }
        return result;
    }

    public List<List<Integer>> spit(int times) {
        List<List<Integer>> result = new ArrayList<>(times);
        for (int i = 0; i < times; i++) {
            result.add(toList(i, times, 1, times, times));
        }
        return result;
    }

    public static void main(String[] args) {
        Snapshot sub = getInstance(9);
        System.out.println(sub);
    }

    private static void show(List<Integer> list) {
        System.out.println(list.size()
                + list.toString()
        );
    }

    private static boolean hit(int[] ints, int i) {
        for (int anInt : ints) {
            if (i % anInt == 0) return true;
        }
        return false;
    }
}
