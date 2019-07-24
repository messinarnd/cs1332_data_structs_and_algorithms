import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Christopher Messina
 * @userid cmessina6
 * @GTID 903023165
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either the array and comparator"
                    + "given was null. You must provide non-null parameters");
        }

        boolean swapped;
        int lastSwap = -1;
        int firstIndex = 0;
        int lastIndex = arr.length - 1;
        do {
            swapped = false;
            for (int i = firstIndex; i < lastIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                    lastSwap = i;
                }
            }

            lastIndex = lastSwap;
            if (swapped) {
                swapped = false;
                for (int i = lastIndex; i > firstIndex; i--) {
                    if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                        T temp = arr[i];
                        arr[i] = arr[i - 1];
                        arr[i - 1] = temp;
                        swapped = true;
                        lastSwap = i;
                    }
                }
            }
            firstIndex = lastSwap;
        } while (swapped);
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either the array and comparator"
                    + "given was null. You must provide non-null parameters");
        }
        int j;
        T temp;
        for (int i = 0; i < arr.length - 1; i++) {
            j = i + 1;
            temp = arr[j];
            while (j > 0 && comparator.compare(arr[j - 1], temp) > 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either the array and comparator"
                    + "given was null. You must provide non-null parameters");
        }

        T temp;
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("The array, comparator, or"
                    + " random number generator given was null. You must "
                    + "provide non-null parameters");
        }
        quickSort(arr, comparator, rand, 0, arr.length - 1);
    }

    /**
     * The recursive helper method for quickSort that sorts the given array and
     * the calls itself on the subarrays to the left and right of the now sorted
     * pivot element.
     * @param arr the array that must be sorted
     * @param comparator the Comparator used to compare the dat in the array
     * @param rand the Random object used to randomly select pivots
     * @param low the lowest index of the subarray that will be sorted during
     *            this method call
     * @param high the highest index of the subarray that will be sorted during
     *             this method call
     * @param <T> data type to be sorted
     */
    private static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                      Random rand, int low, int high) {
        if (low < high) {
            int pivot = rand.nextInt(high - low + 1) + low;
            int i = low + 1;
            int j = high;

            if (high - low != 1) {
                T swapPivLow = arr[low];
                arr[low] = arr[pivot];
                arr[pivot] = swapPivLow;

                while (i <= j) {
                    while (i <= j
                            && comparator.compare(arr[i], arr[low]) <= 0) {
                        i++;
                    }
                    while (j >= i
                            && comparator.compare(arr[j], arr[low]) >= 0) {
                        j--;
                    }
                    if (i < j) {
                        T swapIJ = arr[i];
                        arr[i++] = arr[j];
                        arr[j--] = swapIJ;
                    }
                }

                T swapPivJ = arr[low];
                arr[low] = arr[j];
                arr[j] = swapPivJ;
            } else {
                if (comparator.compare(arr[low], arr[high]) > 0) {
                    T swapTwo = arr[high];
                    arr[high] = arr[low];
                    arr[low] = swapTwo;
                }
            }

            quickSort(arr, comparator, rand, low, j - 1);
            quickSort(arr, comparator, rand, j + 1, high);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Either the array and comparator"
                    + "given was null. You must provide non-null parameters");
        }

        if (arr.length == 1) {
            return;
        }
        mergeSort(arr, comparator,  0, arr.length - 1);
    }

    /**
     * The recursive helper method for mergeSort that divides the given array
     * into two subarrays at the median. The method then calls the merge
     * method on each subarray. This is method used for dividing while the
     * merge method is used for conquering.
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in the array
     * @param low the lowest index of the subarray that will be sorted during
     *            this method call
     * @param high the highest index of the subarray that will be sorted during
     *             this method call
     * @param <T> data type to be sorted
     */
    private static <T> void mergeSort(T[] arr, Comparator<T> comparator,
                                      int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, comparator, low, mid);
            mergeSort(arr, comparator, mid + 1, high);
            merge(arr, comparator, low, mid, high);
        }
    }

    /**
     * The method used to combine and sort the array between the passed in
     * indices. Places the next lowest number from either subarray into the
     * original array at the correct location. If one of the subarrays is empty,
     * then it puts the remaining elements of the other subarray into the
     * original array in order.
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in the array
     * @param low the lowest index of the subarray that will be sorted during
     *            this method call
     * @param mid the index at the middle of the highest and lowest indices
     * @param high the highest index of the subarray that will be sorted during
     *             this method call
     * @param <T> data type to be sorted
     */
    private static <T> void merge(T[] arr, Comparator<T> comparator,
                                  int low, int mid, int high) {
        int subLeftLen = mid - low + 1;
        int subRightLen = high - mid;

        T[] left = (T[]) new Object[subLeftLen];
        T[] right = (T[]) new Object[subRightLen];

        for (int i = 0; i < subLeftLen; i++) {
            left[i] = arr[low + i];
        }
        for (int j = 0; j < subRightLen; j++) {
            right[j] = arr[mid + 1 + j];
        }


        int x = 0;
        int y = 0;
        int z = low;
        while (x < subLeftLen && y < subRightLen) {
            if (comparator.compare(left[x], right[y]) <= 0) {
                arr[z] = left[x];
                x++;
            } else {
                arr[z] = right[y];
                y++;
            }
            z++;
        }

        // Purge
        while (x < subLeftLen) {
            arr[z] = left[x];
            x++;
            z++;
        }
        while (y < subRightLen) {
            arr[z] = right[y];
            y++;
            z++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The array given was null."
                    + " I can't be expected to sort a null array. "
                    + "That's too much.");
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) > Math.abs(max)) {
                max = arr[i];
            }
        }

        int div = Math.abs(max);
        int count = 1;
        while (div >= 10) {
            div = div / 10;
            count++;
        }

        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList();
        }

        int index;
        int denominator = 1;
        for (int i = 0; i < count; i++) {
            for (int x = 0; x < arr.length; x++) {
                index = (arr[x] / denominator) % 10;
                index = index + 9;
                buckets[index].add(arr[x]);
            }
            denominator = denominator * 10;
            int ndx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[ndx++] = bucket.remove();
                }
            }
        }
    }
}