package de.awacademy.zwitscher.entity;

import de.awacademy.zwitscher.entity.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    private String text;

    private Instant postedAt;

    public Message() {
    }

    public Message(User user, String text, Instant postedAt) {
        this.user = user;
        this.text = text;
        this.postedAt = postedAt;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Instant getPostedAt() {
        return postedAt;
    }

    public long getId() {
        return id;
    }
}
