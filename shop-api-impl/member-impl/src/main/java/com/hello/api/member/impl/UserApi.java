package com.hello.api.member.impl;

import com.hello.api.member.IUserApi;
import com.hello.entity.PageData;
import com.hello.mapper.member.IUserMapper;
import com.hello.utils.Constants;
import com.hello.utils.GenerateToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserApi
 * @Description TODO
 * @Author niu
 * @Date 2019/11/16
 * @Version 1.0
 **/
@RestController
public class UserApi implements IUserApi {

    @Autowired
    IUserMapper userMapper;

    @Autowired
    GenerateToken generateToken;

    @Override
    public PageData login(@RequestBody PageData pd) {
        PageData result = new PageData();
        if (StringUtils.isBlank(pd.getString("username")) || StringUtils.isBlank(pd.getString("password")) || StringUtils.isBlank(pd.getString("loginType"))) {
            result.put("code", 201);
            result.put("msg", "参数缺失！");
            return result;
        }
        PageData user = userMapper.selectUser(pd);
        if (Objects.isNull(user)) {
            result.put("code", 202);
            result.put("msg", "用户或密码错误！");
            return result;
        }
        user.put("is_availability", 0);
        PageData userToken = this.userMapper.selectUserToken(user);
        if (Objects.nonNull(userToken)) {
            Boolean removeToken = generateToken.removeToken(userToken.getString("token"));
            if (removeToken) {
                Integer affectedRows = this.userMapper.updateUserToken(userToken);
            }
        }
        String token = generateToken.createToken(Constants.MEMBER_TOKEN_KEYPREFIX + "." +pd.getString("loginType")+ ".", user.getString("USER_ID"));
        result.put("code", 200);
        result.put("token", token);
        Integer affectedRows = this.userMapper.insertUserToken(user);
        return result;
    }


    @Override
    public PageData ssoLogin(@RequestBody PageData pd) {
        PageData result = new PageData();
        if (StringUtils.isBlank(pd.getString("username")) || StringUtils.isBlank(pd.getString("password"))) {
            result.put("code", 201);
            result.put("msg", "参数缺失！");
            return result;
        }
        PageData user = userMapper.selectUser(pd);
        if (Objects.isNull(user)) {
            result.put("code", 202);
            result.put("msg", "用户或密码错误！");
            return result;
        }
        result.put("code", 200);
        result.put("data", user);
        return result;
    }


}
