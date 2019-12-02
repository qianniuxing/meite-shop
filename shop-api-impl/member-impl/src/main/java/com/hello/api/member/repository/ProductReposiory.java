package com.hello.api.member.repository;

import com.hello.api.member.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductReposiory extends ElasticsearchRepository<ProductEntity, Long> {
}
