package com.edgardcurso.algaapi.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

    /**
     * Gerador de senha BCrypt
     * @param args
     */
    public static void main(String[] args){
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        System.out.println(enc.encode("@ngul@r0"));
    }
}
