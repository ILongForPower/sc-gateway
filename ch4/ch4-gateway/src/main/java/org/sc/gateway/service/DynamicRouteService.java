package org.sc.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DynamicRouteService {
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    //增加路由
    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        return "success";
    }
    //更新路由
    public String update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "更新删除失败 ,找不到对应路由id  routeId: "+definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            return "success";
        } catch (Exception e) {
            return "更新失败";
        }
    }
    //删除路由
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id))
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }
}