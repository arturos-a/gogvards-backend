package com.gogvards.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventMessageDto {
    public List<String> users;
    public String message;
}
