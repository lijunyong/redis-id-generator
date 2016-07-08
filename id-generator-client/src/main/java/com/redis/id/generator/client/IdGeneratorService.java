package com.redis.id.generator.client;

import com.baidu.jprotobuf.pbrpc.ProtobufRPC;
import com.redis.id.generator.client.model.ID;

/**
 * ID生成器接口类
 * @ClassName: IdGeneratorService 
 * @author lijy
 * @date 2016年6月27日 上午10:41:23
 */
public interface IdGeneratorService {
	@ProtobufRPC(serviceName = "idGeneratorService", methodName = "nextId")
	public ID nextId();
}
