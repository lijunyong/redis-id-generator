package com.redis.id.generator.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Appliaction{
    public static void main( String[] args ){
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:id-generator-server.xml"});
		context.start();
    }
}
