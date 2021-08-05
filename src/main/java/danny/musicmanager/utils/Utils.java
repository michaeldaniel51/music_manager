package danny.musicmanager.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

public class Utils {


    public static URI generateCreateUri(String id){

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    public static String generatedId(String prefix){

        return prefix.toLowerCase() + "-" + UUID.randomUUID().toString()
                .substring(0,6)
                .replace("-","")
                .toLowerCase() + LocalDateTime.now().toString().replace("-","")
                .replace(":","")
                .replace(".","")
                .replace("T","");
    }

}
