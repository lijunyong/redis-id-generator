# id-generator
基于redis ID生成器，主要分成server端及client端，server端支持横向扩展，client可以通过负载均衡RPC调用多个server服务。

#原理

利用redis的lua脚本执行功能，在每个节点上通过lua脚本生成唯一ID。
生成的ID:
* 使用17位来存放时间，精确到毫秒。  
* 使用一个key保存自增步长进行拼接，Redis支持的整数型Value值范围是-9223372036854775808~9223372036854775807
生成的ID，你也可以根据自己的需求进行改造:
```
20160711102410231041100
```
1）server端通过lua脚本获取redis的系统时间及一个自增步长

下载id-genertaor.lua，并把它load到redis上。
```bash
./redis-cli script load "$(cat id-genertaor.lua)" 
```

获取lua脚本的sha1值，可能是：
```
2f5d97beebc25579e14673971de7fcbccaed3fa1
```
