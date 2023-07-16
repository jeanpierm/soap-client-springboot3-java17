package com.jm.soapclientspringboot3java17;

import java.net.MalformedURLException;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tempuri.Add;
import org.tempuri.AddResponse;
import org.tempuri.Calculator;

@RestController
@RequestMapping(path = "/calculator")
public class CalculatorController {

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddResponse> add(@RequestBody final Add add) throws MalformedURLException {
		final var res = new AddResponse();
		final var endpoint = "http://www.dneonline.com/calculator.asmx";
		final var url = URI.create(endpoint).toURL();
		final var service = new Calculator(url);
		final var calculatorService = service.getCalculatorSoap12();
		final var addResult = calculatorService.add(add.getIntA(), add.getIntB());
		res.setAddResult(addResult);
		return ResponseEntity.ok(res);
	}
}
