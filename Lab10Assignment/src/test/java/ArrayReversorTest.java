import edu.mum.cs.cs425.arrayreversor.ArrayFlattenerService;
import edu.mum.cs.cs425.arrayreversor.ArrayReversor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArrayReversorTest {

    @Mock
    private ArrayFlattenerService arrayFlattenerService;

    @Test
    @DisplayName("reverseArray() with a legit 2-D nested array reverses the flattened result from the service")
    void reverseArray_withLegitNestedArray_returnsReversedFlattenedArray() {
        int[][] dimArray = {{1, 3}, {0}, {4, 5, 9}};
        int[] flattenedFromService = {1, 3, 0, 4, 5, 9};
        int[] expectedReversed = {9, 5, 4, 0, 3, 1};

        when(arrayFlattenerService.flattenArray(dimArray)).thenReturn(flattenedFromService);

        ArrayReversor arrayReversor = new ArrayReversor(arrayFlattenerService);
        int[] actual = arrayReversor.reverseArray(dimArray);

        assertArrayEquals(expectedReversed, actual);
        verify(arrayFlattenerService).flattenArray(dimArray);
    }
}