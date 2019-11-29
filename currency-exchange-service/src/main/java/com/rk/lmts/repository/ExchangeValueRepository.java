package com.rk.lmts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.lmts.controller.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from,String to);
}
