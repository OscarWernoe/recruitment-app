package se.kth.iv1201.recruitment.payload;

import lombok.Data;

@Data
public class Response {
    private final Boolean success;
    private final String message;
}
