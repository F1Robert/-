package com.nolan.bean;

import lombok.Data;

@Data
public class Position {
    private String longitude;
    private String latitude;
    private String accuracy;
    private String time;
}
