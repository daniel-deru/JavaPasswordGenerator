import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import Interfaces.Response;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;


public class Password {
    final public static int[] NUMBERS = {48, 10};
    final public static int[] UPPER = {65, 26};
    final public static int[] LOWER = {97, 26};
    final public static char[] SPECIAL = {'!', '@', '#','$', '%', '&', '*', ':', ';', '?', '/', '.'};

    public static void main(String[] args){
        PasswordPair data = getParams();
        String password = generatePassword(data);
        CopyToClipBoard(password);
        System.out.println("Here is your password: " + password);
        System.out.println("Your password has already been copied to your clipboard");

    }

    public static void CopyToClipBoard(String text){
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static String generatePassword(PasswordPair data){
        StringBuilder builder = new StringBuilder(data.passwordLength);

        for(int i = 0; i < data.passwordLength; i++){
           int randomIndex = (int) Math.floor(Math.random() * data.charList.size());
           builder.append(data.charList.get(randomIndex));
        }

        return builder.toString();
    }

    private static PasswordPair getParams(){
        String Chars = getInput("Please enter the desired number of characters: ", Response.Type.number);
        String useNumbers = getInput("Do you want to use numbers? ", Response.Type.string);
        String useUpper = getInput("Do you want to use Uppercase letters? ", Response.Type.string);
        String useSpecial = getInput("Do you want to use special characters? ", Response.Type.string);

        int numChars = Integer.parseInt(Chars);
        ArrayList<Character> chars = getCharRange(useNumbers, useUpper, useSpecial);

        return new PasswordPair(numChars, chars);
    }

    public static ArrayList<Character> getCharRange(String numbers, String upper, String special){
        ArrayList<Character> chars = new ArrayList<>();

        fillChars(chars, Password.LOWER[0], Password.LOWER[1]);

        if(numbers.equals(Response.yes)){
            fillChars(chars, Password.NUMBERS[0], Password.NUMBERS[1]);
        }
        if(upper.equals(Response.yes)){
            fillChars(chars, Password.UPPER[0], Password.UPPER[1]);
        }
        if(special.equals(Response.yes)){
           for(int i = 0; i < Password.SPECIAL.length; i++){

               chars.add(Password.SPECIAL[i]);
           }
        }

        return chars;
    }

    private static void fillChars(ArrayList<Character> chars, int start, int count){
        for(int i = 0; i < count; i++){
            chars.add((char)(start + i));
        }
    }

    private static String getInput(String message, Response.Type value){
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.print(message);
            String result = sc.nextLine();

            if(value == Response.Type.number && !result.matches("\\d+")){
                System.out.println("Please enter a numeric value");
            }
            if(value == Response.Type.string && !Response.validResponse(result)){
                System.out.println("Please enter the either \"y\" or \"n\"");
            }
            else {
                return result;
            }
        }
    }
}
