package com.usercontrol.dataAccess;

import com.usercontrol.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Transactional(readOnly = true)
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update ConfirmationToken ct " +
            "set ct.confirmedAt = ?2 " +
            "where ct.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);

}
