package edu.mum.cs.cs425.arrayreversor;

import edu.mum.cs.cs425.flattenarray.ArrayFlattener;

public class ArrayFlattenerServiceImpl implements ArrayFlattenerService {
    private final ArrayFlattener arrayFlattener = new ArrayFlattener();

    @Override
    public int[] flattenArray(int[][] input) {
        return arrayFlattener.flattenArray(input);
    }
}
