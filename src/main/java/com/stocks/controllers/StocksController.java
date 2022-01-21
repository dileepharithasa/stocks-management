package com.stocks.controllers;

import com.stocks.exceptions.RecordNotFoundException;
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
    public ResponseEntity getStocks(@RequestParam(defaultValue = "1") Integer pageNo,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    @RequestParam(defaultValue = "stockId") String sortBy) throws Exception {
        List<Stock> StocksList = null;
        try {
            StocksList = stockService.getStocks(pageNo, pageSize, sortBy);
        } catch (Exception ex) {
            StockResponse error = StockResponse.builder()
                    .errorMessage("Error retrieving the stocks")
                    .httpStatus(String.valueOf(HttpStatus.NOT_FOUND)).status("Get Stocks service error").build();
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(StocksList, HttpStatus.OK);
    }

    @GetMapping(path = "/stocks/{stockId}")
    public ResponseEntity getStockByStockId(@PathVariable Integer stockId) throws Exception {
        Stock stock = null;
        try {
            stock = stockService.getStockByStockId(stockId);
        } catch (Exception ex) {
            if (ex instanceof RecordNotFoundException) {
                throw new RecordNotFoundException("Error retrieving the stock with stockId :" + stockId);
            } else {
                throw new Exception("Internal Server Error");
            }
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
                    .httpStatus(String.valueOf(HttpStatus.BAD_REQUEST)).
                    status("Add Stocks service error").build();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(stockSaved, HttpStatus.OK);
    }

    @PatchMapping(path = "/stocks/{stockId}")
    public ResponseEntity updateStock(@RequestBody Stock stock, @PathVariable Integer stockId) throws Exception {
        stock.setStockId(stockId);
        Stock stockUpdated = null;
        try {
            stockUpdated = stockService.updateStock(stock);
        } catch (Exception ex) {
            if (ex instanceof RecordNotFoundException) {
                throw new RecordNotFoundException("Error retrieving the stock with stockId :" + stockId);
            } else {
                throw new Exception("Internal Server Error");
            }
        }
        return new ResponseEntity<>(stockUpdated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/stocks/{stockId}")
    public ResponseEntity deleteStock(@PathVariable Integer stockId) throws Exception {
        Stock deletedStock = new Stock();
        try {
            stockService.deleteStockByStockId(stockId);
        } catch (Exception ex) {
            if (ex instanceof RecordNotFoundException) {
                throw new RecordNotFoundException("No record found to delete with stockId : " + stockId);
            } else {
                throw new Exception("Internal Server Error");
            }
        }
        deletedStock.setStockId(stockId);
        return new ResponseEntity<>(deletedStock, HttpStatus.OK);
    }
}