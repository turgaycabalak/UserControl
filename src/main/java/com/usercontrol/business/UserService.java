package com.usercontrol.business;

import com.usercontrol.dataAccess.UserRepository;
import com.usercontrol.entities.ConfirmationToken;
import com.usercontrol.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "Bu email'e ait kullanici bulunamadi: %s";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE,email)));
    }

    public String signUp(User user){
        boolean userExists = this.userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists){
            //TODO: check of attributes are the same and
            //TODO: if mail not confirmed send confirmation mail


            throw new IllegalStateException("Bu email zaten kulaniliyor: "+user.getEmail());
        }

        String encodedPassword = this.bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        this.userRepository.save(user);//user tablosuna kaydedildi

        //TODO: Confirmation token olustur ve tablosuna kaydet(ok)
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);
        this.confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: Send email(ok)

        return token;
    }


    public int enableUser(String email) {
        return this.userRepository.enableUser(email);
    }
}
