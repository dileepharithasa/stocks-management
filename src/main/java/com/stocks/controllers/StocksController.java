package com.stocks.controllers;

import com.stocks.entities.StocksEntity;
import com.stocks.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/stocks_management")
public class StocksController {

    @Autowired
    private StockService stockService;

    @GetMapping(path = "/stocks")
    public @ResponseBody List<StocksEntity> getStocks() {
        List<StocksEntity> StocksList = stockService.getStocks();
        return StocksList;
    }

}
