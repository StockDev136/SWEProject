package edu.mum.cs.cs425.flattenarray;

import java.util.Arrays;

public class ArrayFlattener {
    public int[] flattenArray(int[][] arrray) {
        if (arrray == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        int totalLength = 0;
        for (int[] row : arrray) {
            if (row != null) {
                totalLength += row.length;
            }
        }

        int[] result = new int[totalLength];
        int index = 0;
        for (int[] row : arrray) {
            if (row == null) {
                continue;
            }
            for (int value : row) {
                result[index++] = value;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayFlattener flattener = new ArrayFlattener();
        int[][] dimArray = {{1, 3}, {0}, {4, 5, 9}};

        System.out.println("Flattened: " + Arrays.toString(flattener.flattenArray(dimArray)));
    }
}
