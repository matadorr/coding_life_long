package com.github.algrithom.lb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
class Server {
    private String ip;
    private Integer weight;
}