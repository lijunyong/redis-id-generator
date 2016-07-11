# id-generator
基于redis ID生成器,主要分成server端及client端,server端支持横向扩展,client可以通过负载均衡RPC调用多个server服务。

#原理

利用redis的lua脚本执行功能,在每个节点上通过lua脚本生成唯一ID。
生成的ID:
* 使用17位来存放时间，精确到毫秒。  
* 使用一个key保存自增步长进行拼接,Redis支持的整数型Value值范围是-9223372036854775808~9223372036854775807
生成的ID，你也可以根据自己的需求进行改造:
```
20160711102410231041100
```

redis多实例部署3个节点，则节点1返回的seq是：
```
0, 3, 6, 9, 12 ...
```
节点2返回的seq是
```
1, 4, 7, 10, 13 ...
```
节点3返回的seq是
```
2, 5, 8, 11, 14 ...
```

* server端通过lua脚本获取redis的系统时间及一个自增步长

下载id-genertaor.lua,并把它load到redis master上。
```bash
./redis-cli script load "$(cat id-genertaor.lua)" 
```

获取lua脚本的sha1值,可能是：
```
2f5d97beebc25579e14673971de7fcbccaed3fa1
```
* client通过rpc调用server服务
client端id-generator-client.properties配置
```
#####server端地址及端口
server.host=127.0.0.1
server.port=1199

##########client 重试次数
client.retry=0

##########client socket参数
client.connectTimeout=10000
client.keepAlive=true
client.testOnBorrow=true
client.testOnReturn=true
client.maxWait=30000
```
client使用例子:
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:id-generator-client.xml"})
public class IdGeneratorTest {
	@Autowired
	private IdGeneratorProvider idGeneratorProvider;
	
	@Test
	public void testNextId(){
		String id = idGeneratorProvider.nextId();
	 }
}
```

