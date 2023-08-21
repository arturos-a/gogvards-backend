package com.gogvards.dto;

import lombok.Data;

import java.util.List;
@Data
public class ClassEventMessageDto {
    public List<String> className;
    public String message;
}
