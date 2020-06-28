public class IsLand {
    public static void main(String[] args) {
        int[][] map =
                {
                        {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                        {0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0}
                };

        System.out.println(count(map[0], map[1]));
    }

    private static int count(int[] top, int[] now) {
        boolean start = false;
        boolean tstart = false;
        int result = 0;
        for (int i = 0; i < now.length; i++) {
            if (!start) {
                if (now[i] == 1) {
                    start = true;
                    result++;
                }
            } else {
                if (now[i] != 1) {
                    start = false;
                }
            }
            if (start||tstart) {
                if (!tstart) {
                    if (top[i] == 1) {
                        tstart = true;
                        result--;
                    }
                } else {
                    if (top[i] != 1) {
                        tstart = false;
                    }
                }
            }
        }
        return result;
    }
}

for(let sub of document.getElementsByClassName("task-item-ctn"))console.log(sub.childNodes[1].innerText,sub.childNodes[2].innerText);
如何解决资源限制类题目 2020-06-07 周日 14:00-16:00
        index.html:27 单调栈和窗口及其更新结构 2020-05-23 周六 09:00-11:00
        index.html:27 类似斐波那契数列的递归 2020-05-23 周六 14:00-16:00
        index.html:27 bfprt算法与蓄水池算法 2020-05-24 周日 09:00-11:00
        index.html:27 KMP算法及其扩展 2020-05-24 周日 14:00-16:00
        index.html:27 Manacher算法及其扩展 2020-05-30 周六 09:00-11:00
        index.html:27 Morris遍历及其相关扩展 2020-05-30 周六 14:00-16:00
        index.html:27 线段树 2020-05-31 周日 09:00-11:00
        index.html:27 AC自动机 2020-05-31 周日 14:00-16:00
        index.html:27 打表技巧和矩阵处理技巧 2020-06-06 周六 09:00-11:00
        index.html:27 数组累加和问题三连 2020-06-06 周六 14:00-16:00
        index.html:27 哈希函数有关的结构和岛问题 2020-06-07 周日 09:00-11:00
        index.html:27 如何解决资源限制类题目 2020-06-07 周日 14:00-16:00
        index.html:27 有序表的原理、应用、扩展（一） 2020-06-13 周六 09:00-11:00
        index.html:27 有序表的原理、应用、扩展（二） 2020-06-13 周六 14:00-16:00
        index.html:27 有序表的原理、应用、扩展（三） 2020-06-14 周日 09:00-11:00
        index.html:27 AC自动机、卡特兰数 2020-06-14 周日 14:00-16:00