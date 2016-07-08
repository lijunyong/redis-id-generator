package com.redis.id.generator.server;


import com.baidu.jprotobuf.pbrpc.ProtobufRPCService;
import com.redis.id.generator.server.model.ID;

/**
 * ID生成器接口类
 * @ClassName: IdGeneratorService 
 * @author lijy
 * @date 2016年6月27日 上午10:41:23
 */
public interface IdGeneratorService {
	@ProtobufRPCService(serviceName = "idGeneratorService", methodName = "nextId")
	public ID nextId();
}
