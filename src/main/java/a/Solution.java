package a;

import java.util.*;

/**
 *
 */
public class Solution {
    public int openLock(String[] deadends, String target) {
        //死亡笔记
        HashSet<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }
        //访问过的路径
        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String up = add(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = sub(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    String add(String s, int i) {
        char[] source = s.toCharArray();
        if (source[i] == '9') {
            source[i] = '0';
        } else {
            source[i]++;
        }
        return new String(source);
    }

    String sub(String s, int i) {
        char[] source = s.toCharArray();
        if (source[i] == '0') {
            source[i] = '9';
        } else {
            source[i]--;
        }
        return new String(source);
    }

    public static void main(String[] args) {
        String[] deadends = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        System.out.println(new Solution().openLock(deadends, target));
    }
}
