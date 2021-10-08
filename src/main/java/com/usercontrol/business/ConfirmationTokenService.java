package com.usercontrol.business;

import com.usercontrol.dataAccess.ConfirmationTokenRepository;
import com.usercontrol.entities.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;


    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        this.confirmationTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return this.confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return this.confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
