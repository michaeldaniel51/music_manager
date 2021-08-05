package danny.musicmanager.dtos;

public enum ResponseStatus {


    Created(201,"Created Successfully"),
    Successful(200,"Request Successful"),
    Failed(400,"Unauthorized"),
    Unauthorized(401,"Resource Does Not Exist"),
    Not_Found(404,"Resource Does Not Exist");

    private final int code;
    private final String message;


    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode (){
        return code;
    }
    public String getMessage(){
        return message;
    }










}
