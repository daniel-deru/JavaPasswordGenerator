import java.util.ArrayList;

class PasswordPair {
    int passwordLength;
    ArrayList<Character> charList;

    PasswordPair(int numChars, ArrayList<Character> chars){
        this.passwordLength = numChars;
        this.charList = chars;
    }
}