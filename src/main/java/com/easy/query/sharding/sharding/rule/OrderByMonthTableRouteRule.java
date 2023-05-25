package com.easy.query.sharding.sharding.rule;

import com.easy.query.core.sharding.api.rule.time.AbstractMonthTableRule;
import com.easy.query.sharding.domain.OrderByMonthEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * create time 2023/5/25 08:51
 * 文件说明
 *
 * @author xuejiaming
 */
@Component
public class OrderByMonthTableRouteRule extends AbstractMonthTableRule<OrderByMonthEntity> {
    @Override
    protected LocalDateTime convertLocalDateTime(Object shardingValue) {
        return (LocalDateTime)shardingValue;
    }
}
