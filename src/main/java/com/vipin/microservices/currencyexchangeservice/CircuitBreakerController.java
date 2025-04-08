package com.vipin.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {

	private Logger logger = 
			LoggerFactory.getLogger(CircuitBreakerController.class);

@GetMapping("/sample-api")
//@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse") // attempt mentioned in property file default is 3 attempt
//@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")  // open, half open, close state of circute breaker n case of huge no.of request
//@RateLimiter(name="default")  //allow a particular no.of request in  a time period
@Bulkhead(name="sample-api") //howmany concurrent calls allowed
public String sampleApi() {
	logger.info("Sample api call received");
//	ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", 
//				String.class);
//	return forEntity.getBody();
	return "sample-api";
}

public String hardcodedResponse(Exception ex) {
	return "fallback-response";
}
}
