## 简单ip白名单过滤器

[IpFilterServiceImpl](https://github.com/yinhutjfox/tjf-demo-utils/blob/master/white-Ip-Address-Filter/src/main/java/tjf/whitIpAddressFilter/impl/IpFilterServiceImpl.java)
> - 通过四维byte数组实现对ip白名单地址的管理。由于数组在程序启动时就已经初始化好连续地址空间，所以对于每一个ip地址其对应内存地址是预知固定的，故查询和写入效率较高，并能满足高并发。
> - 不足：由于数组较大，在系统初始化的时候会慢点，耗时约2s