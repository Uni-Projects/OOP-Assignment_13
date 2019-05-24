/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filefindermergesort;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scatt
 */
public class ParallelMergeSort {

    /**
     * sort the given array in O(N log N) time The array is split in two parts
     * of equal size. These parts are sort recursively and merged.
     *
     * @param array
     */
    public static void sort(int[] array) {
        if (array.length > 1) {
            int[] firstHalf = Arrays.copyOf(array, array.length / 2);
            int[] secondHalf = Arrays.copyOfRange(array, array.length / 2, array.length);

            if (firstHalf.length > 10000) {
                Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        sort(firstHalf);
                    }
                });
                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        sort(secondHalf);
                    }
                });
                t1.start();
                t2.start();
                try {
                    t1.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ParallelMergeSort.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    t2.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ParallelMergeSort.class.getName()).log(Level.SEVERE, null, ex);
                }
                MergeSort.merge(firstHalf, secondHalf, array);
            } else {
                sort(firstHalf);
                sort(secondHalf);
                MergeSort.merge(firstHalf, secondHalf, array);

            }
        }
    }
}
