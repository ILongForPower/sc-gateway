package org.sc.gateway.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.sc.gateway.model.RouteAndWeightDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class DynamicRouteServiceByNacos implements ApplicationRunner {
    @Autowired
    private DynamicRouteService dynamicRouteService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        dynamicRouteByNacosListener("test-gateway","test");
    }
    /**
     * 监听Nacos Server下发的动态路由配置
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener (String dataId, String group){
        try {
//            Properties properties = new Properties();
//            properties.put("nacos.server-addr", "");
//            properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
//            ConfigService configService=NacosFactory.createConfigService(properties);
            ConfigService configService= NacosFactory.createConfigService("127.0.0.1:8848");
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    RouteAndWeightDefinition definition= JSON.parseObject(configInfo, RouteAndWeightDefinition.class);
                    dynamicRouteService.update(definition);
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            //todo 提醒:异常自行处理此处省略
        }
    }
}