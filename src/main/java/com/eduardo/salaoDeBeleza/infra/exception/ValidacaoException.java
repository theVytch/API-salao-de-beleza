package com.eduardo.salaoDeBeleza.infra.exception;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String msg){
        super(msg);
    }
}
