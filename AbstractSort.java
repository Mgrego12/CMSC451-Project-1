/*
Matt Gregorek Project 1 CMSC451 June 13, 2023
Abstract Class AbstractSort will hold objects to be used
by both BubbleSort and MergeSort algorithm Classes, via "extend" .
 */

public abstract class AbstractSort {
    protected long startTime;
    protected int count;
    protected long endTime;

    public void startSort() {
        startTime = System.nanoTime();
        count = 0;
    }
    public void endSort() {
        this.endTime = System.nanoTime();
    }
    public void incrementCount() {
        count++;
    }
    public int getCount() {
        return count;
    }
    public long getTime() {
        return System.nanoTime() - startTime;
    }

    public abstract long sort(int[] arr);

    public void resetSort() {
        startTime = 0;
        count = 0;
        endTime = 0;
    }
    // boolean to check Array after bubble/merge sorts are called
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }}
        return true;
    }
}
class MergeSort extends AbstractSort {

    public long sort(int[] arr) {
        startSort();
        mergeSort(arr, 0, arr.length - 1);
        endSort();
        return getTime();
    }
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
            incrementCount();
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }
    }
    @Override
    public void resetSort() {
        this.startTime = 0;
        this.count = 0;
        this.endTime = 0;
    }

}
class BubbleSort extends AbstractSort {
    @Override
    public long sort(int[] arr) {
        // while swap
        startSort();
        int n = arr.length;
        boolean isSwapped = true;
        int i = 0;
        while (isSwapped) {
            isSwapped = false;
            i++; // for j less than length -1 , ++
            for (int j = 0; j < n - i; j++) {// for index of array in sort,
                if (arr[j] > arr[j + 1]) {// if > than next index of arr. swap
                    swap(arr, j, j + 1);
                    incrementCount();
                    isSwapped = true;
                }
            }
        }
        endSort();
        return getTime();
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    @Override
    public void resetSort() {
        this.startTime = 0;
        this.count = 0;
        this.endTime = 0;
    }
}