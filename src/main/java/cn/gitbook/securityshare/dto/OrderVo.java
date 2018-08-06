package cn.gitbook.securityshare.dto;

import lombok.Builder;

@Builder
public class OrderVo {
    private Long id;
    private String proName;
    private Long price;
    private Long time;//采用utc 时间，兼容国内外时间
}
