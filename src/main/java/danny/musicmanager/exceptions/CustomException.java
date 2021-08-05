package danny.musicmanager.exceptions;


import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class CustomException extends RuntimeException {



    private static  final long serialVersionUID = 1L;
    private final String message;

    public CustomException (String message,Class<?> clazz){

        this.message = message;
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(message);
    }
}
