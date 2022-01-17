package com.stocks.services;

import com.stocks.entities.StocksEntity;
import com.stocks.repositories.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StocksRepository stocksRepository;

    public List<StocksEntity> getStocks() {
        return (List<StocksEntity>) stocksRepository.findAll();
    }
}
