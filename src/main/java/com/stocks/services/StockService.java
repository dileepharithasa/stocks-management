package com.stocks.services;

import com.stocks.models.Stock;

import java.util.List;

public interface StockService {

    public Stock addStock(Stock stock) throws Exception;

    public List<Stock> getStocks(Integer pageNo, Integer pageSize, String sortBy);

    public Stock getStockByStockId(Integer stockId) throws Exception;

    public Stock updateStock(Stock stock) throws Exception;

    public void deleteStockByStockId(Integer stockId) throws Exception;
}
