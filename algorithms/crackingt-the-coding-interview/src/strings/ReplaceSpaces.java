package strings;

public class ReplaceSpaces {


    /**
     * Example input:  "Mr John Smith    ", 13
     * Example output: "Mr%20John%20Smith"
     * @param str
     * @param trueLength
     */
    private static void replaceSpaces(char[] str, int trueLength){

        int spaceCount = 0, index, i = 0;

        // trueLength = 13 here
        for (i = 0; i < trueLength; ++i){
            if (str[i] == ' '){
                spaceCount++;
            }
        }

        index = trueLength + spaceCount * 2;

        // why ?
        if (trueLength < str.length)
            str[trueLength] = '\0';

        for (i = trueLength - 1; i >= 0; --i){
            if (str[i] == ' '){
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index -= 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }

    public static void main(String[] args){
        char[] str = "Mr John Smith    ".toCharArray();
        replaceSpaces(str, 13);
        System.out.println(str);
    }
}
