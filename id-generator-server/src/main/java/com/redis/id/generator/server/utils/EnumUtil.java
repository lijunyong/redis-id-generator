package com.redis.id.generator.server.utils;


public class EnumUtil {
	public static enum LoadBalance {

		ROUNDROBIN("roundrobin", "轮询"), RANDOM("random", "随机");

        public final String value;
        public final String desc;

        LoadBalance(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    } 
}
