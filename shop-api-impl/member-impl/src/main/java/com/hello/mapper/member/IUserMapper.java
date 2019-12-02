package com.hello.mapper.member;

import com.hello.entity.PageData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper {


    PageData selectUser(PageData pageData);

    PageData selectUserToken(PageData pageData);

    Integer updateUserToken(PageData pageData);

    Integer insertUserToken(PageData pageData);

    List<PageData> selectProductList(PageData pageData);


}
