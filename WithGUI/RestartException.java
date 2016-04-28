package bonusassignment;

public class RestartException extends RuntimeException {

    public RestartException(){
        super();
    }

    public RestartException(String message){
        super(message);
    }
}