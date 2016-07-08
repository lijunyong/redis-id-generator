package com.redis.id.generator.client;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:id-generator-client.xml"})
public class IdGeneratorTest {
	@Autowired
	private IdGeneratorProvider idGeneratorProvider;
	
	@Test
	public void testNextId(){
		try {
			long start = System.currentTimeMillis();
			int threadCount = 1;
			final CountDownLatch latch = new CountDownLatch(threadCount);
			for (int i = 0; i < threadCount; ++i) {
				Thread thread = new Thread() {
					public void run() {
						String id = idGeneratorProvider.nextId();
						System.out.println(id);
						latch.countDown();
					}
				};
				thread.start();
			}
			latch.await();
			long end = System.currentTimeMillis();
			System.out.println(end- start);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testWriterNextId() throws IOException{
		final BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\id.txt"));
		try {
			long start = System.currentTimeMillis();
			int threadCount = 10000;
			final CountDownLatch latch = new CountDownLatch(threadCount);
			for (int i = 0; i < threadCount; ++i) {
				Thread thread = new Thread() {
					public void run() {
						String id = idGeneratorProvider.nextId();
						try {
							writer.write(id+"\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
						latch.countDown();
					}
				};
				thread.start();
			}
			latch.await();
			long end = System.currentTimeMillis();
			System.out.println(end- start);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}

