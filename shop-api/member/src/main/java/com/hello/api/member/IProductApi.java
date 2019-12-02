package com.hello.api.member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Api(tags = "商品服务")
@RequestMapping("/product")
public interface IProductApi {


    @ApiOperation(value = "商品搜索")
    @GetMapping("/searchProduct")
    List searchProduct(Map map);

    @ApiOperation(value = "商品新增")
    @GetMapping("/saveProduct")
    void saveProductList();


}
