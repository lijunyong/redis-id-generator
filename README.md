# id-generator
基于redis ID生成器，主要分成server端及client端，server端支持横向扩展

#原理

1）server端通过lua脚本获取redis的系统时间及一个自增步长

下载id-genertaor.lua，并把它load到redis上。
```bash
./redis-cli script load "$(cat id-genertaor.lua)" 
```
