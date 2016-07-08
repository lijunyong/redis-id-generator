package com.redis.id.generator.client;

/**
 * 生成ID提供者
 * @author lijy
 * @version
 */
public interface IdGeneratorProvider {
	public String nextId();
}
