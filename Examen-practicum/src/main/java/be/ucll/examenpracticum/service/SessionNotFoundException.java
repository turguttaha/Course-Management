package be.ucll.examenpracticum.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class SessionNotFoundException extends RuntimeException{
}
