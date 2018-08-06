package cn.gitbook.securityshare.dto;

import lombok.Builder;

@Builder
public class AddOrderRequest {
    private Long id;
    private String productName;
    private Long price;
}
