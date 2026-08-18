package edu.mum.cs.cs425.arrayreversor;

import java.util.Arrays;

public class ArrayReversor {
    private final ArrayFlattenerService arrayFlattenerService;

    public ArrayReversor(ArrayFlattenerService arrayFlattenerService) {
        this.arrayFlattenerService = arrayFlattenerService;
    }

    public int[] reverseArray(int[][] array) {
        int[] flattened = arrayFlattenerService.flattenArray(array);

        int[] reversed = new int[flattened.length];
        for (int i = 0; i < flattened.length; i++) {
            reversed[i] = flattened[flattened.length - 1 - i];
        }
        return reversed;
    }

    public static void main(String[] args) {
        ArrayReversor reversor = new ArrayReversor(new ArrayFlattenerServiceImpl());
        int[][] dimArray = {{1, 3}, {0}, {4, 5, 9}};

        System.out.println("Flattened + reversed: " + Arrays.toString(reversor.reverseArray(dimArray)));
    }
}
