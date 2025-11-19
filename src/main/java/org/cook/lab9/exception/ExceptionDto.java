package org.cook.lab9.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {

    private String message;
    private String details;
    private LocalDateTime timestamp;

}
