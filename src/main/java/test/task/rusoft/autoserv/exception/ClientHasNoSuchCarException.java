package test.task.rusoft.autoserv.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Client has no such car")
public class ClientHasNoSuchCarException extends RuntimeException {}
