package test.task.rusoft.autoserv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid request: fields should not be null or empty")
public class InvalidPostRequestException extends RuntimeException {}
