package com.stocks.models;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock implements Serializable  {
    private Integer stockId;
    private String stockName;
    private BigDecimal stockPrice;
    private Date stockLastUpdate;
}