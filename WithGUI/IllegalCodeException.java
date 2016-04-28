package bonusassignment;

public class IllegalCodeException extends RuntimeException {

    public IllegalCodeException(){
        super();
    }

    public IllegalCodeException(String message){
        super(message);
    }
}