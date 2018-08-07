package cn.gitbook.securityshare.controller;

import cn.gitbook.securityshare.dto.AddOrderRequest;
import cn.gitbook.securityshare.dto.JsonObject;
import cn.gitbook.securityshare.dto.OrderVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/order")
@RestController
public class OrderController {

    @ApiOperation(value = "通过id获取订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping(value = "get_order_info_by_id/{id}")
    public JsonObject<OrderVo> getOrderInfo(@PathVariable Long id){
        //模拟db查询
        OrderVo vo = OrderVo.builder().id(id).price(30L).proName("衣服").build();
        return JsonObject.success(vo);
    }

    @ApiOperation(value = "添加订单")
    @PostMapping(value = "add_order")
    public JsonObject<String> addOrder(@RequestBody AddOrderRequest request){
        return JsonObject.success(null);
    }

}
