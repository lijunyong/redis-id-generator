package com.redis.id.generator.server.model;


import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ID {
	@Protobuf
	private String id;
	
	
	public ID() {
	}

	public ID(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
