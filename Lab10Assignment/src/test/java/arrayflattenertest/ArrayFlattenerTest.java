package arrayflattenertest;

import edu.mum.cs.cs425.flattenarray.ArrayFlattener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayFlattenerTest {
    private final ArrayFlattener arrayFlattener = new ArrayFlattener();

    @Test
    void flattenArrayTest() {
        int[][] dimArray = {{1, 3}, {0}, {4, 5, 9}};
        int[] expected = {1, 3, 0, 4, 5, 9};

        int[] actual = arrayFlattener.flattenArray(dimArray);

        assertArrayEquals(expected, actual);
    }
}
