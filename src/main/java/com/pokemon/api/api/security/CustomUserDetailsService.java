package com.pokemon.api.api.security;

import com.pokemon.api.api.exceptions.PokemonNotFoundException;
import com.pokemon.api.api.models.UserEntity;
import com.pokemon.api.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username could not be founf"));
      return new User(user.getUsername(), user.getPassword(), user.getRoles());
    }

}
