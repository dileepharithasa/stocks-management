package com.stocks.services;

import com.stocks.entities.StockEntity;
import com.stocks.models.Stock;
import com.stocks.repositories.StocksRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StocksRepository stocksRepository;

    public Stock addStock(Stock stock) {
        StockEntity stockEntity = new DozerBeanMapper().map(stock, StockEntity.class);
        stockEntity = stocksRepository.save(stockEntity);
        Stock stockSaved = new DozerBeanMapper().map(stockEntity, Stock.class);
        return stockSaved;
    }

    public List<Stock> getStocks() {
        List<StockEntity> stockEntityList = (List<StockEntity>) stocksRepository.findAll();
        ArrayList stockList = new DozerBeanMapper().map(stockEntityList, new ArrayList<Stock>().getClass());
        return stockList;
    }

    public Stock getStockByStockId(Integer stockId) {
        Optional<StockEntity> stockEntityOption = stocksRepository.findById(stockId);
        Stock stock = new DozerBeanMapper().map(stockEntityOption.get(), Stock.class);
        return stock;
    }

    public Stock updateStock(Stock stock) {
        StockEntity stockEntity = new DozerBeanMapper().map(stock, StockEntity.class);
        StockEntity entityUpdated = stocksRepository.save(stockEntity);
        Stock stockUpdated = new DozerBeanMapper().map(entityUpdated, Stock.class);
        return stockUpdated;

    }

    public void deleteStockByStockId(Integer stockId) {
        stocksRepository.deleteById(stockId);
    }

}