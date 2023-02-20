package com.github.harlikodasma.exchangerateportal.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortalServiceRestClientException extends RuntimeException {

    public PortalServiceRestClientException(String message) {
        super(message);
    }
}
