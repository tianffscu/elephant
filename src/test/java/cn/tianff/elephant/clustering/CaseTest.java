package cn.tianff.elephant.clustering;

import org.junit.Assert;
import org.junit.Test;

public class CaseTest {

    private static String bigNumMul(String num1, String num2) {
        int pox = 0;
        if (num1.startsWith("-")) {
            pox += 1;
            num1 = num1.substring(1);
        }
        if (num2.startsWith("-")) {
            pox += 1;
            num2 = num2.substring(1);
        }

        char chars1[] = num1.toCharArray();
        char chars2[] = num2.toCharArray();

        int result[] = new int[chars1.length + chars2.length];
        int n1[] = new int[chars1.length];
        int n2[] = new int[chars2.length];

        for (int i = 0; i < chars1.length; i++)
            n1[i] = chars1[i] - '0';
        for (int i = 0; i < chars2.length; i++)
            n2[i] = chars2[i] - '0';

        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                result[i + j] += n1[i] * n2[j];
            }
        }

        for (int i = result.length - 1; i > 0; i--) {
            result[i - 1] += result[i] / 10;
            result[i] = result[i] % 10;
        }

        StringBuilder resultStr = new StringBuilder();
        if (pox % 2 != 0) {
            resultStr.append("-");
        }
        for (int i = 0; i < result.length - 1; i++) {
            resultStr.append(result[i]);
        }
        System.out.println(resultStr);
        return resultStr.toString();
    }

    @Test
    public void test1() {
        String num1 = "-12";
        String num2 = "12";
        Assert.assertEquals(bigNumMul(num1, num2), "-144");
    }

    private static int getLongestNumericChars(String var) {
        int lastLongestNum = 0;
        int currentLongestNum = 0;
        boolean leftIsNum = true;
        for (int i = 0; i < var.length(); i++) {
            char character = var.charAt(i);
            if (character >= 48
                    && character <= 57) {
                currentLongestNum++;
                if (!leftIsNum) {
                    leftIsNum = true;
                }
            } else {
                leftIsNum = false;
                if (currentLongestNum > lastLongestNum) {
                    lastLongestNum = currentLongestNum;
                    currentLongestNum = 0;
                }
            }
        }
        return currentLongestNum > lastLongestNum ? currentLongestNum : lastLongestNum;
    }

    @Test
    public void test2() {
        String var = "k223l23333s99999999999";
        int num = getLongestNumericChars(var);
        System.out.println(num);
        Assert.assertEquals(11, num);
    }

    /**
     * int lastLongestNum = 0;
     int currentLongestNum = 0;
     boolean leftIsNum = true;
     String longNum = "";
     StringBuilder currentNumStr = new StringBuilder();
     for (int i = 0; i < var.length(); i++) {
     char character = var.charAt(i);
     if (character >= 48
     && character <= 57) {
     currentLongestNum++;
     currentNumStr.append(character);
     if (!leftIsNum) {
     leftIsNum = true;
     }
     } else {
     leftIsNum = false;
     if (currentLongestNum > lastLongestNum) {
     lastLongestNum = currentLongestNum;
     if (currentNumStr.length() > longNum.length()) {
     longNum = currentNumStr.toString();
     }
     }
     currentLongestNum = 0;
     currentNumStr = new StringBuilder();
     }
     }


     int longest = currentLongestNum > lastLongestNum ? currentLongestNum : lastLongestNum;
     String result = currentNumStr.length() > longNum.length() ? currentNumStr.toString() : longNum;

     if (longest == 0) {
     System.out.println("");
     } else {
     System.out.println(result + "," + longest);
     }
     */
}
