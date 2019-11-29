package com.rk.lmts.controller;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rk.lmts.controller.bean.ExchangeValue;
import com.rk.lmts.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue reteriveExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to)
	{
		
		ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);//new ExchangeValue(1000L,from,to,BigDecimal.valueOf(65));
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("server.port")));
		
		logger.info("{******************}",exchangeValue);
		
		return exchangeValue ;
	}
	
}
