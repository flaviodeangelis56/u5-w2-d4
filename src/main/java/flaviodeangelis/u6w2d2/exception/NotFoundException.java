package flaviodeangelis.u6w2d2.exception;

public class NotFoundException extends Exception {

    public NotFoundException(long id) {
        super("Elemento con id " + id + " non trovato!");
    }
}
