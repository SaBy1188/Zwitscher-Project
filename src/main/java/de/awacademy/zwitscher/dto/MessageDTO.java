package de.awacademy.zwitscher.dto;

import javax.validation.constraints.Size;

public class MessageDTO {

    @Size(min = 1, max = 99)
    private String text;

    public MessageDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
