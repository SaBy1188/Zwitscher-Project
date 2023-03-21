package de.awacademy.zwitscher.controller;

import de.awacademy.zwitscher.entity.Session;
import de.awacademy.zwitscher.entity.User;
import de.awacademy.zwitscher.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.Instant;
import java.util.Optional;

@ControllerAdvice
public class SessionControllerAdvice {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionControllerAdvice(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @ModelAttribute("sessionUser")
    public User sessionUser(@CookieValue(value = "sessionId", defaultValue = "") String sessionId) {
        if(!sessionId.isEmpty()) {
            Optional<Session> optionalSession = sessionRepository.findByIdAndExpiresAtAfter(
                sessionId, Instant.now());
            if (optionalSession.isPresent()) {
                Session session = optionalSession.get();

                // neues Ablaufdatum für die Session
                session.setExpiresAt(Instant.now().plusSeconds(7*24*60*60));
                sessionRepository.save(session);

                return session.getUser();
            }
        }
        return null;
    }
}
