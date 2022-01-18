package com.stocks.controllers;

import com.stocks.models.Stock;
import com.stocks.models.StockResponse;
import com.stocks.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/stocks_management")
public class StocksController {
    @Autowired
    private StockService stockService;

    @GetMapping(path = "/stocks")
    public ResponseEntity getStocks() throws Exception {
        List<Stock> StocksList = null;
        try {
            StocksList = stockService.getStocks();
        } catch (Exception ex) {
            StockResponse error = StockResponse.builder()
                    .errorMessage("Error retrieving the stocks")
                    .httpStatus(String.valueOf(HttpStatus.NOT_FOUND)).status("Get Stocks service error").build();
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(StocksList, HttpStatus.OK);
    }

    @GetMapping(path = "/stocks/{stockId}")
    public ResponseEntity getStockByStockId(@PathVariable Integer stockId) {
        Stock stock = null;

        try {
            stock = stockService.getStockByStockId(stockId);
        } catch (Exception ex) {
            StockResponse error = StockResponse.builder()
                    .errorMessage("Error retrieving the stock with stockid" + stockId)
                    .httpStatus(String.valueOf(HttpStatus.NOT_FOUND)).status("Get Stocks service error").build();
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PostMapping(path = "/stocks")
    public ResponseEntity addStock(@RequestBody Stock stock) throws Exception {
        Stock stockSaved = null;
        try {
            stockSaved = stockService.addStock(stock);
        } catch (Exception ex) {
            StockResponse error = StockResponse.builder()
                    .errorMessage("Error adding the stock")
                    .httpStatus(String.valueOf(HttpStatus.BAD_REQUEST)).status("Add Stocks service error").build();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(stockSaved, HttpStatus.OK);
    }

    @PatchMapping(path = "/stocks/{stockId}")
    public ResponseEntity updateStock(@RequestBody Stock stock, @PathVariable Integer stockId) {
        stock.setStockId(stockId);
        Stock stockUpdated = null;
        try {
            stockUpdated = stockService.updateStock(stock);
        } catch (Exception ex) {
            StockResponse error = StockResponse.builder()
                    .errorMessage("Error updating the stock")
                    .httpStatus(String.valueOf(HttpStatus.NOT_FOUND)).status("Update Stocks service error").build();
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stockUpdated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/stocks/{stockId}")
    public ResponseEntity deleteStock(@PathVariable Integer stockId) {
        Stock deletedStock = new Stock();
        try {
            stockService.deleteStockByStockId(stockId);
        } catch (Exception ex) {
            StockResponse error = StockResponse.builder()
                    .errorMessage("Error updating the stock")
                    .httpStatus(String.valueOf(HttpStatus.NOT_FOUND)).status("Update Stocks service error").build();
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        deletedStock.setStockId(stockId);
        return new ResponseEntity<>(deletedStock, HttpStatus.OK);
    }
}