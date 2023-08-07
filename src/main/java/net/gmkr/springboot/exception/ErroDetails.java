package net.gmkr.springboot.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String path;
	private String errorCode;
}
