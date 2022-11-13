/*************************************************************************
 *  Compilation:  javac RunLengthEncoding.java
 *  Execution:    java RunLengthEncoding
 *
 *  @author : Prachiti Atigre, pka24
 *
 *************************************************************************/

public class RunLengthEncoding {
    /* 
     * Encode the original string by finding sequences in the string
     * where the same character repeats. Replace each such sequence
     * by a token consisting of: the number of characters in the sequence
     * followed by the repeating character.
     * Return the encoded string.
     */
    public static String encode (String original) {
        int n = original.length();
        String newString = "";

        for (int i = 0; i < n; i++) {
            int count = 1;
            while (i < n - 1 && original.charAt(i) == original.charAt(i + 1)) {
                count ++;
                i ++;
            }
            newString += "" + count + original.charAt(i); 
        }
        newString = newString.replaceAll("1" , "");
        return newString;
    }

    /*
     * Decodes the original string encoded with the encode method.
     * Returns the decoded string.
     *
     * YOUR decode METHOD MUST BE RECURSIVE, do not use while, do/while, 
     * or for loops
     */
    public static String decode (String original) {
        char c = original.charAt(0);
        int count = 0;
        String newString = "";

        if (original.length() == 1) {
            return newString += original;
        }
        else if (original.length() >= 2) {
            if (Character.isDigit(c)) {
                count = c - '0';
                newString += ("" + original.charAt(1)).repeat(count - 1);
            }
            else {
                newString += "" + c;
            }
            return newString + decode(original.substring(1 , original.length()));
        }
        return "";
    }

    public static void main (String[] args) {
        System.out.println(encode("qwwwwwwwwweeeeerrtyyyyyqqqqwEErTTT"));
        System.out.println(decode("q9w5e2rt5y4qw2Er3T"));
    }
}
