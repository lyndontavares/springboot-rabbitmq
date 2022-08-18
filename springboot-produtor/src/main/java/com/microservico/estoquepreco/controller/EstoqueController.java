package com.microservico.estoquepreco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservico.estoquepreco.dto.EstoqueDto;
import com.microservico.estoquepreco.dto.RabbitmqConstantes;
import com.microservico.estoquepreco.service.RabbitmqService;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

	@Autowired
	private RabbitmqService rabbitmqService;

	@SuppressWarnings("rawtypes")
	@PutMapping
	private ResponseEntity<?> alteraEstoque(@RequestBody EstoqueDto estoqueDto) {
		System.out.println(estoqueDto.codigoproduto);

		this.rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_ESTOQUE, estoqueDto);
		return new ResponseEntity(HttpStatus.OK);
	}
}
