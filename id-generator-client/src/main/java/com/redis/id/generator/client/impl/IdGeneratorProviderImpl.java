package com.redis.id.generator.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.redis.id.generator.client.IdGeneratorProvider;
import com.redis.id.generator.client.IdGeneratorService;
import com.redis.id.generator.client.utils.StringUtil;


public class IdGeneratorProviderImpl implements IdGeneratorProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(IdGeneratorProviderImpl.class);
	
	/**重试次数**/
	private int retry;
	
	@Autowired
	private IdGeneratorService idGenerator;
	
	@Override
	public String nextId() {
		String id = null;
		try{
			for(int i = 0; i <= retry; i++){
				id = idGenerator.nextId().getId();
				if(StringUtil.isNotEmpty(id)){
					return id;
				}
			}
			if(StringUtil.isEmpty(id)){
				return null;
			}
			return id;
		}catch(Exception e){
			logger.warn("IdGeneratorProviderImpl exception:{}", e);
		}
		return null;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}
	
}
