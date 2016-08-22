package com.redis.id.generator.server.loadbalance;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.id.generator.server.exception.IdGeneratorException;
import com.redis.id.generator.server.impl.IdGeneratorServiceImpl;
import com.redis.id.generator.server.utils.EnumUtil.LoadBalance;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class JedisSentinelLoadBalance {
private static final Logger logger = LoggerFactory.getLogger(IdGeneratorServiceImpl.class);
    
    private List<JedisSentinelPool> jedisSentinelPoolList;
    
    private String loadbalance;
    
    public Jedis getJedis() {
        try {
            if (LoadBalance.RANDOM.value.equals(loadbalance)) {
                java.util.Random random = new java.util.Random();
                int index = random.nextInt(jedisSentinelPoolList.size());
                JedisSentinelPool jedisPool = jedisSentinelPoolList.get(index);
                return jedisPool.getResource();
            }
        } catch (Exception e) {
            logger.warn("jedis load balance exception:{}", e);
            throw new IdGeneratorException(e);
        }
        return null;
    }
    
    
    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public List<JedisSentinelPool> getJedisSentinelPoolList() {
        return jedisSentinelPoolList;
    }

    public void setJedisSentinelPoolList(List<JedisSentinelPool> jedisSentinelPoolList) {
        this.jedisSentinelPoolList = jedisSentinelPoolList;
    }
    
    
    
}
