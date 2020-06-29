import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class KNN {
// the data
    static double[][] instances = {
            {0.35,0.91,0.86,0.42,0.71},
            {0.21,0.12,0.76,0.22,0.92},
            {0.41,0.58,0.73,0.21,0.09},
            {0.71,0.34,0.55,0.19,0.80},
            {0.79,0.45,0.79,0.21,0.44},
            {0.61,0.37,0.34,0.81,0.42},
            {0.78,0.12,0.31,0.83,0.87},
            {0.52,0.23,0.73,0.45,0.78},
            {0.53,0.17,0.63,0.29,0.72},
            };

    /**
     * Returns the majority value in an array of strings
     * majority value is the most frequent value (the mode)
     * handles multiple majority values (ties broken at random)
     *
     * @param  array an array of strings
     * @return  the most frequent string in the array
     */
    public static String findMajorityClass(String[] array)
            {
            //add the String array to a HashSet to get unique String values
            Set<String> h = new HashSet<String>(Arrays.asList(array));
            //convert the HashSet back to array
            String[] uniqueValues = h.toArray(new String[0]);
            //counts for unique strings
            int[] counts = new int[uniqueValues.length];
            // loop thru unique strings and count how many times they appear in origianl array
            for (int i = 0; i < uniqueValues.length; i++) {
            for (int j = 0; j < array.length; j++) {
            if(array[j].equals(uniqueValues[i])){
            counts[i]++;
            }
            }
            }

                for (String uniqueValue : uniqueValues) System.out.println(uniqueValue);
                for (int count : counts) System.out.println(count);


            int max = counts[0];
            for (int counter = 1; counter < counts.length; counter++) {
            if (counts[counter] > max) {
            max = counts[counter];
            }
            }
            System.out.println("max # of occurences: "+max);

            // how many times max appears
            //we know that max will appear at least once in counts
            //so the value of freq will be 1 at minimum after this loop
            int freq = 0;
            for (int counter = 0; counter < counts.length; counter++) {
                if (counts[counter] == max) {
                    freq++;
                }
            }

            //index of most freq value if we have only one mode
            int index = -1;
            if(freq==1){
            for (int counter = 0; counter < counts.length; counter++) {
            if (counts[counter] == max) {
            index = counter;
            break;
            }
            }
            //System.out.println("one majority class, index is: "+index);
            return uniqueValues[index];
            } else{//we have multiple modes
            int[] ix = new int[freq];//array of indices of modes
            System.out.println("multiple majority classes: "+freq+" classes");
            int ixi = 0;
            for (int counter = 0; counter < counts.length; counter++) {
            if (counts[counter] == max) {
            ix[ixi] = counter;//save index of each max count value
            ixi++; // increase index of ix array
            }
            }

            for (int counter = 0; counter < ix.length; counter++) {
                System.out.println("class index: " + ix[counter]);
            }
            //now choose one at random
            Random generator = new Random();
            //get random number 0 <= rIndex < size of ix
            int rIndex = generator.nextInt(ix.length);
            System.out.println("random index: "+rIndex);
            int nIndex = ix[rIndex];
            //return unique value at that index
            return uniqueValues[nIndex];
            }

            }


    /**
     * Returns the mean (average) of values in an array of doubless
     * sums elements and then divides the sum by num of elements
     *
     */
    private static double meanOfArray(double[] m) {
        double sum = 0.0;
        for (double v : m) {
            sum += v;
        }
        return sum/m.length;
    }
}
