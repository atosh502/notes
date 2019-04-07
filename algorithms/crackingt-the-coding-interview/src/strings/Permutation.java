package strings;

class Permutation {

    private static boolean checkPermutation(String s, String t){
        // two strings must be of same length
        if (s.length() != t.length()){
            return false;
        }

        int[] count = new int[128];
        for (char c : s.toCharArray()){
            count[c]++;
        }

        for (char c : t.toCharArray()){
            count[c]--;
            if (count[c] < 0){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        
        // assume all lowercase characters 
        String s = "dog", t = "god";
        String u = "ram", v = "sita";
        System.out.println(checkPermutation(s, t));
        System.out.println(checkPermutation(u, v));
    }
}
