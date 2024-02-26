package com.galaxe.metrics.Exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	 private int statusCode;
	    private String errorMessage;

	    public ExceptionResponse(String errorMessage) {
	        this.errorMessage = errorMessage;
	    }
}
