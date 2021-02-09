package hangman;

public class GuessAlreadyMadeException extends Exception {
    String errorMsg;

    public GuessAlreadyMadeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String toString() {
        return errorMsg;
    }
}
