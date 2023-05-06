package ExceptionHandlers;

public class BadRequest extends Exception{
    public BadRequest(String msg){
        super(msg);
    }
}
