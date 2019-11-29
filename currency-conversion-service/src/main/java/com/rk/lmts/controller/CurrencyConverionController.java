package com.rk.lmts.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rk.lmts.bean.CurrencyConversionBean;
import com.rk.lmts.feign.CurrencyExchangeServiceProxy;


@RestController
public class CurrencyConverionController {

	private ResponseEntity<CurrencyConversionBean> responseEntity;
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable("from") String from,
												  @PathVariable("to")String to,
												  @PathVariable("quantity") BigDecimal quantity)
	{
		Map<String,String> uriVariables=new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,uriVariables);
		CurrencyConversionBean response = responseEntity.getBody();
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),0);
	}
	

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable("from") String from,
												  @PathVariable("to")String to,
												  @PathVariable("quantity") BigDecimal quantity)
	{
		CurrencyConversionBean response = proxy.reteriveExchangeValue(from, to);
		logger.info("{*******************}",response);
		return new CurrencyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
	}
	 
}
