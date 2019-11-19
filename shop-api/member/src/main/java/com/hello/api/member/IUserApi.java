package com.hello.api.member;


import com.hello.entity.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "用户接口")
@RequestMapping("/user")
public interface IUserApi {


    @PostMapping("/login")
    @ApiOperation(value = "用户认证")
    PageData login(PageData pageData);

    @PostMapping("/ssologin")
    @ApiOperation(value = "sso用户认证")
    PageData ssoLogin(PageData pageData);



}
