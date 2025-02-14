package com.nexon.nexon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    
    @NotBlank(message = "Comment cannot be empty")
    @Size(max = 200, message = "Comment cannot exceed 200 characters")
    private String text;
}
