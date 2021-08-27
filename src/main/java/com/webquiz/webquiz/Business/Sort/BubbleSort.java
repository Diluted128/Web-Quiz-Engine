package com.webquiz.webquiz.Business.Sort;

import java.util.List;

public class BubbleSort {

    public static List<Integer> bubbleSort(List<Integer> arr) {
        if(arr.size() != 0){
            int n = arr.size();
            int temp;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < (n - i); j++) {
                    if (arr.get(j - 1) > arr.get(j)) {
                        temp = arr.get(j - 1);
                        arr.add(j - 1, arr.get(j));
                        arr.add(j, temp);
                    }
                }
            }
        }
        return arr;
    }
}
