package CustomerExceptions;

public class InvalidName extends RuntimeException {
    public InvalidName(){
        super("The name is invalid");
    }
}
