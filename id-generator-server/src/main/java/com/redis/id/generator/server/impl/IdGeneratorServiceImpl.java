package com.redis.id.generator.server.impl;



import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.redis.id.generator.server.IdGeneratorService;
import com.redis.id.generator.server.model.ID;
import com.redis.id.generator.server.utils.DateUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class IdGeneratorServiceImpl implements IdGeneratorService{
	
	private static final Logger logger = LoggerFactory.getLogger(IdGeneratorServiceImpl.class);
	
	private JedisSentinelPool jedisSentinelPool;
	
	private String key;
	
	private String step;
	
	private String sha;
	
	@ProtobufRPCService(serviceName = "idGeneratorService", methodName = "nextId")
	public ID nextId(){
		Jedis jedis = null;
		StringBuffer buf = new StringBuffer();
		try{
			jedis = jedisSentinelPool.getResource();
			List<String> result = (List<String>)jedis.evalsha(sha, 1, key, step);
			Date date = new Date(Long.parseLong(result.get(0))*1000+Long.parseLong(result.get(1)));
			buf.append(DateUtil.formatDate(date, DateUtil.DATE_FMT_YMDHMSSSSS));
			buf.append(result.get(2));
		}catch(Exception e){
			logger.warn("IdGeneratorServiceImpl nextId exception:{}", e);
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
		return new ID(buf.toString());
	}

	public JedisSentinelPool getJedisSentinelPool() {
		return jedisSentinelPool;
	}

	public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
		this.jedisSentinelPool = jedisSentinelPool;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}
	
}
