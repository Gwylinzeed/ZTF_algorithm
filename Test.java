package com.cl;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    private static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("输入(以end结束输入):");

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        scan.nextLine();
        int[] m = new int[n];
        int index = 0;
        while (!scan.hasNext("end")) {
            m[index] = scan.nextInt();
            index++;
        }
        scan.close();

        //创建1到n的数组
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr, i, i + 1, i + 1);
        }

        perm(arr, 0, n - 1);
        System.out.println("输出:");
        System.out.println(Arrays.toString(find(k, m)));
    }

    // 全排序
    private static void perm(int[] arr, int start, int end) {
        if (start == end) {
            list.add(Arrays.copyOf(arr,arr.length));
        } else {
            for (int i = start; i <= end; i++) {
                swap(arr, start, i);
                perm(arr, start + 1, end);
                swap(arr, start, i);
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // 找出m后面的第k个数
    private static int[] find(int k, int[] m) {
        //对所有的数组从小到大进行排序
        List<int[]> sortedList = list.stream().sorted(Comparator.comparing(arr -> {
            StringBuffer sb = new StringBuffer();
            for (int value : arr) {
                sb.append(value);
            }
            return new BigInteger(sb.toString());
        })).collect(Collectors.toList());


        int index = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            if (Arrays.equals(sortedList.get(i), m)) {
                index = i;
            }
        }

        if (index + k + 1 > sortedList.size()) {
            index = index + k - sortedList.size();
        } else {
            index = index + k;
        }

        return sortedList.get(index);
    }
}

