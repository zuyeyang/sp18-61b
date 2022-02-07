/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
import java.util.Arrays;
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max_index = 0;
        char blankspace = '_';
        String[] sorted = new String[asciis.length];
        String[] asciis_pad = new String[asciis.length];

        for ( int i = 0; i < asciis.length; i++){

            String ascii = asciis[i];

            if (ascii.length() > max_index) max_index = ascii.length();
            sorted[i] = ascii;
            asciis_pad[i] = ascii;
        }
        for (int i = 0; i<asciis_pad.length;i++){
            String currentascii = asciis_pad[i];
            while (currentascii.length() < max_index){
                currentascii += blankspace;
            }
            asciis_pad[i] = currentascii;
        }

        for (int i = max_index - 1; i >= 0; i--){
            sortHelperLSD(asciis_pad, sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis_pad, String[] sorted, int index) {
        // Optional LSD helper method for required LSD radix sort
        int lengths = asciis_pad.length;
        int[] intValsArray = new int[lengths];

        for (int i = 0; i < lengths; i++){
            int charArCurrentIndex = asciis_pad[i].charAt(index);
            intValsArray[i] = charArCurrentIndex;
        }

        for (int i = 0; i < lengths; i++){
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int j = i; j<lengths; j++){
                if (intValsArray[j] < min){
                    min = intValsArray[j];
                    minIndex = j;
                }
            }
            swap (i,minIndex,intValsArray,asciis_pad,sorted);
        }

        return;
    }
    private static void swap (int i, int j, int[] intVlasArray, String[] asciis_pad, String[] sorted){
        int temp = intVlasArray[i];
        intVlasArray[i] = intVlasArray[j];
        intVlasArray[j] = temp;

        String temp_pad = asciis_pad[i];
        asciis_pad[i] = asciis_pad[j];
        asciis_pad[j] = temp_pad;

        String temp_sort = sorted[i];
        sorted[i] = sorted[j];
        sorted[j] = temp_sort;
    }


    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] blah = new String[3];
        blah[0] = "123d";
        blah[1] = "121t";
        blah[2] = "121d";

//        System.out.println(sort(blah));
        System.out.println(Arrays.toString(sort(blah)));
    }
}
