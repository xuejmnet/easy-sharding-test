package com.easy.query.sharding.controller;

import com.easy.query.core.api.client.EasyQuery;
import com.easy.query.core.api.pagination.EasyPageResult;
import com.easy.query.sharding.domain.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create time 2023/5/24 23:58
 * 文件说明
 *
 * @author xuejiaming
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderController {

    private final EasyQuery easyQuery;

    @GetMapping("/init")
    public Object init() {
        ArrayList<OrderEntity> orderEntities = new ArrayList<>(100);
        List<String> users = Arrays.asList("xiaoming", "xiaohong", "xiaolan");

        for (int i = 0; i < 100; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(String.valueOf(i));
            int i1 = i % 3;
            String uid = users.get(i1);
            orderEntity.setUid(uid);
            orderEntity.setOrderNo(i);
            orderEntities.add(orderEntity);
        }
        long l = easyQuery.insertable(orderEntities).executeRows();
        return "成功插入:"+l;
    }
    @GetMapping("/first")
    public Object first(@RequestParam("id") String id) {
        OrderEntity orderEntity = easyQuery.queryable(OrderEntity.class)
                .whereById(id).firstOrNull();
        return orderEntity;
    }
    @GetMapping("/firstByUid")
    public Object firstByUid(@RequestParam("uid") String uid) {
        OrderEntity orderEntity = easyQuery.queryable(OrderEntity.class)
                .where(o->o.eq(OrderEntity::getUid,uid)).firstOrNull();
        return orderEntity;
    }
    @GetMapping("/page")
    public Object page(@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize) {
        EasyPageResult<OrderEntity> pageResult = easyQuery.queryable(OrderEntity.class)
                .orderByAsc(o -> o.column(OrderEntity::getOrderNo))
                .toPageResult(pageIndex, pageSize);
        return pageResult;
    }
}
