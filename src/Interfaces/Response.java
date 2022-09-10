package Interfaces;

public class Response implements ResponseType {
    public enum Type {
        string,
        number
    };

    public static boolean validResponse(String response){
        return response.equals(yes) || response.equals(no);
    }

    private static boolean validNum(String response){
        try {
            Integer.parseInt(response);
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }
}
