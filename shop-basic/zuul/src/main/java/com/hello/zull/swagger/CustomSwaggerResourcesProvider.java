package com.hello.zull.swagger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CustomSwaggerResourcesProvider
 * @Description TODO
 * @Author niu
 * @Date 2019/11/10
 * @Version 1.0
 **/

@Component
@Primary
public class CustomSwaggerResourcesProvider implements SwaggerResourcesProvider {


    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        // app-itmayiedu-order
        // 网关使用服务别名获取远程服务的SwaggerApi
        resources.add(swaggerResource("api-member", "/api-member/v2/api-docs", "2.0"));
        resources.add(swaggerResource("api-weChat", "/api-weChat/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }


}
