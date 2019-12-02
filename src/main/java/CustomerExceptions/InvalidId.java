package CustomerExceptions;

public class InvalidId extends RuntimeException {

    public InvalidId(){
        super("The id entered should be != 0.");
    }
}
