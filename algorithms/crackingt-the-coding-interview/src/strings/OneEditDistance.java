package strings;

public class OneEditDistance {

    private static boolean oneEditReplace(String left, String right){
        boolean foundDiff = false;
        for (int i = 0; i < left.length(); ++i){
            if (left.charAt(i) != right.charAt(i)){
                if (foundDiff == true){
                    // a difference was previously found
                    return false;
                }

                foundDiff = true;
            }
        }
        return true;
    }

    private static boolean oneEditInsert(String left, String right){

        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < left.length() && idx2 < right.length()){
            if (left.charAt(idx1) != right.charAt(idx2)){
                if (idx1 != idx2){
                    return false;
                }
                idx2++;
            } else {
                idx1++;
                idx2++;
            }
        }

        return true;
    }

    private static boolean oneEditAway(String left, String right){
        if (left.length() == right.length()){
            return oneEditReplace(left, right);
        } else if (left.length() + 1 == right.length()){ // insert into left
            return oneEditInsert(left, right);
        } else if (left.length() - 1 == right.length()){ // insert into right
            return oneEditInsert(right, left);
        } else {
            return false;
        }
    }

    public static void main(String[] args){
        String s1 = "pale", s2 = "ple";
        String s3 = "pales", s4 = "pale";
        String s5 = "pale", s6 = "bale";
        String s7 = "pale", s8 = "bae";
        System.out.println(s1 + " " + s2 + " " + oneEditAway(s1, s2));
        System.out.println(s3 + " " + s4 + " " + oneEditAway(s3, s4));
        System.out.println(s5 + " " + s6 + " " + oneEditAway(s5, s6));
        System.out.println(s7 + " " + s8 + " " + oneEditAway(s7, s8));
    }
}
