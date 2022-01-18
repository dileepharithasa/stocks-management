package com.stocks.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StockResponse {
    String status;
    String httpStatus;
    String errorMessage;
}
