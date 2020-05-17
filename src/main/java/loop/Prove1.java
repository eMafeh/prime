package loop;

import loop.circle.Snapshot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prove1 {
    public static void main(String[] args) {
        Snapshot.getInstance(2 * 3 * 5 *7)
                .spit(8)
                .stream()
                .map(c -> c.stream()
                        .map(a -> {
                            if(a %2!=0)throw new RuntimeException();
                            return a/2;
                        })
                        .collect(Collectors.toList()))
                .forEach(System.out::println);
        for (int i = 1; i < 12; i++) {
            int l = i *2* 3 * 5 ;
            Snapshot instance = Snapshot.getInstance(l);
//            System.out.println(instance);
            int[] collect = loopNum(instance);
            List<Integer> result = maxGcd(collect);
            System.out.println(instance.length + "\t" + Arrays.toString(collect) + "\t" + result);
        }
    }

    private static int[] loopNum(Snapshot instance) {
        return IntStream.range(1, instance.length - 1)
                .map(a -> {
                    try {
                        List<List<Integer>> spit = instance.spit(a);
//                        System.out.println(spit);
                        Set<Integer> set = new HashSet<>();
                        for (List<Integer> integers : spit) set.add(integers.size());
                        if (set.size() == 1) return a;
                    } catch (Exception e) {//
                    }
                    return 0;
                })
                .filter(a -> a != 0)
                .toArray();
    }

    private static List<Integer> maxGcd(int[] ints) {
        Arrays.sort(ints);
        List<Integer> result = new ArrayList<>();
        m:
        for (int i = ints.length - 1; i >= 0; i--) {
            Integer n = ints[i];
            for (Integer integer : result) if (integer % n == 0) continue m;
            result.add(n);
        }
        return result;
    }
}
