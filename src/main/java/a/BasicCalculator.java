package a;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * <p>
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 * <p>
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 */
public class BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> stk = new Stack<Integer>();
        // 记录算式中的数字
        int num = 0;
        // 记录 num 前的符号，初始化为 +
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是数字，连续读取到 num
            if (Character.isDigit(c))
                num = 10 * num + (c - '0');
            // 如果不是数字，就是遇到了下一个符号，
            // 之前的数字和符号就要存进栈中
            if (!Character.isDigit(c) || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stk.push(num);
                        break;
                    case '-':
                        stk.push(-num);
                        break;
                }
                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
        }
        // 将栈中所有结果求和就是答案
        int res = 0;
        while (!stk.empty()) {
            res += stk.pop();
            stk.pop();
        }
        return res;
    }
}
