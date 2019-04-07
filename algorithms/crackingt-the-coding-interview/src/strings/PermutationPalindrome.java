package strings;

public class PermutationPalindrome {

    // converts a character to corresponding index in the frequency table
    private static int getCharacterValue(char ch){
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int c = Character.getNumericValue(ch);
        if (c >= a && c <= z){
            return c - a;
        }
        return -1;
    }

    private static int[] frequencyTable(String str){

        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        for (int i = 0; i < str.length(); ++i){
            int val = getCharacterValue(str.charAt(i));
            if (val != -1)
                table[val]++;
        }
        return table;
    }

    private static boolean checkExactlyOneBitSet(int bitVector){
        return (bitVector & (bitVector - 1)) == 0;
    }

    private static int toggle(int bitVector, int shift){

        if (shift < 0)
            return bitVector;

        int mask = 1 << shift;
        if ((bitVector & mask) == 0){
            bitVector |= mask;
        } else {
            // the bitVector was already set in that position
            bitVector &= ~mask; // clear the set bit
        }

        return bitVector;
    }

    private static int constructBitVector(String str){

        int bitVector = 0;
        for (char c : str.toCharArray()){
            int val = getCharacterValue(c);
            if (val != -1){
                bitVector = toggle(bitVector, val);
            }
        }
        return bitVector;
    }

    private static boolean isPermutationOfPalindrome3(String str){

        // using bit vectors
        int bitVector = constructBitVector(str);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    private static boolean isPermutationOfPalindrome2(String str){

        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        int countOdd = 0;

        for (char c : str.toCharArray()){
            int idx = getCharacterValue(c);
            if (idx != -1){
                table[idx]++;
                if (table[idx] % 2 == 1){
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }

        return countOdd <= 1;
    }

    private static boolean isPermutationOfPalindrome(String string){

        // check that no more than one char is odd
        int[] table = frequencyTable(string);
        boolean foundOdd = false;
        for (int i = 0; i < table.length; ++i){
            if (table[i] % 2 == 1){
                if (foundOdd){  // an odd numbered character was previously found
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(isPermutationOfPalindrome3("tactcoapapa"));
        System.out.println(isPermutationOfPalindrome3("hola"));
    }
}
