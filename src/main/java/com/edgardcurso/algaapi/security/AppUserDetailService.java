package com.edgardcurso.algaapi.security;

import com.edgardcurso.algaapi.model.Usuario;
import com.edgardcurso.algaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(s);

        Usuario u = usuario.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha incorretos"));

        return new User(s, u.getSenha(), getPermissoes(u));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario u) {
        Set<SimpleGrantedAuthority> auth = new HashSet<>();

        u.getPermissoes().forEach(p -> auth.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));

        return auth;
    }
}
