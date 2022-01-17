package com.stocks.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class Stock {

    Integer stockId;
    String stockName;
    BigDecimal stockPrice;
    Date stockLastUpdate;

}