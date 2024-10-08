package mariapiabaldoin.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'" + id + " non è stato trovato");
    }
}
