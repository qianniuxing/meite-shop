package com.hello.api.member.impl;

import com.hello.api.member.IProductApi;
import com.hello.api.member.entity.ProductEntity;
import com.hello.api.member.repository.ProductReposiory;
import com.hello.entity.PageData;
import com.hello.mapper.member.IUserMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ProductApi implements IProductApi {

    @Autowired
    ProductReposiory productReposiory;

    @Autowired
    IUserMapper userMapper;



    @Override
    public List searchProduct(@RequestBody Map map) {
        // 1.拼接查询条件
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        // 2.模糊查询name字段
        builder.must(QueryBuilders.fuzzyQuery("name",  map.get("name")));
        Pageable pageable = new QPageRequest(0, 5);
        // 3.调用ES接口查询
        Page<ProductEntity> page = productReposiory.search(builder, pageable);
        // 4.获取集合数据
        List<ProductEntity> content = page.getContent();
        // 5.将entity转换dto
//        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//        List mapAsList = mapperFactory.getMapperFacade().mapAsList(content, );
        return content;

    }


    @Override
    public void saveProductList() {
        List<PageData> productList = this.userMapper.selectProductList(null);
        productList.get(100);
        List<ProductEntity> peList = new ArrayList<>();
        for (PageData p : productList) {
            ProductEntity pe = new ProductEntity();
            pe.setId(Integer.parseInt(p.getString("ID")));
            pe.setCategoryId(Integer.parseInt(p.getString("CATEGORY_ID")));
            pe.setName(p.getString("NAME"));
            pe.setPrice(Double.parseDouble(p.getString("PRICE")));
            pe.setStock(Integer.parseInt(p.getString("STOCK")));
            pe.setStatus(Integer.parseInt(p.getString("STATUS")));
            pe.setCreatedTime((Date) p.get("CREATED_TIME"));
            peList.add(pe);
        }
        Iterable<ProductEntity> productEntities = productReposiory.saveAll(peList);
    }


}
