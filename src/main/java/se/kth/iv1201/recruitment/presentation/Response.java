package se.kth.iv1201.recruitment.presentation;

import lombok.Data;

/**
 * A wrapper object that holds the fields of the HTTP response body.
 */
@Data
public class Response {
    private final Boolean success;
    private final String message;
}
