package danny.musicmanager.exceptions;

public class SongNotOutDatedException extends RuntimeException {


    private String message;


    public SongNotOutDatedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
