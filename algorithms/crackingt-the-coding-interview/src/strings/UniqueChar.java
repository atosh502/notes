package strings;

class UniqueChar {

    private static boolean isUniqueChars(String s){
        int checker = 0;
        for (int i = 0; i < s.length(); ++i){
            int val = s.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0)
                return false;
            checker = checker | (1 << val);
        }
        return true;
    }
    // TODO: add .gitignore file
    public static void main(String[] args){
        
        // assume all lowercase characters 
        String s = "stringr";
        String t = "hola";
        System.out.println("String: " + s + " " + isUniqueChars(s));
        System.out.println("String: " + t + " " + isUniqueChars(t));
    }
}
