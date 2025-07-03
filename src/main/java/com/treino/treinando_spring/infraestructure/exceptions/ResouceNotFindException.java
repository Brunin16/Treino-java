package com.treino.treinando_spring.infraestructure.exceptions;

public class ResouceNotFindException extends RuntimeException{
    public ResouceNotFindException(String mensagem){
        super(mensagem);
    }
    public ResouceNotFindException(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
