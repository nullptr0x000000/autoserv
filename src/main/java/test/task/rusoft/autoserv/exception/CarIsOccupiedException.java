package test.task.rusoft.autoserv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Car is occupied")
public class CarIsOccupiedException extends RuntimeException {}
