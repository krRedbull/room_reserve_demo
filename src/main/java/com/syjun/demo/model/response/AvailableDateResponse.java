package com.syjun.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailableDateResponse {
    private String view;
    private String value;
}
