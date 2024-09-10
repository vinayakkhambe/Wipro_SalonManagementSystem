package com.app.exhandler;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorResponse {
	private String message;
	private String timeStamp;
	
	public ErrorResponse(String message) {
		super();
		this.message = message;
		this.timeStamp=LocalDateTime.now().toString().substring(0, 10) + " time " + LocalDateTime.now().toString().substring(11, 19);
	}
	

}
