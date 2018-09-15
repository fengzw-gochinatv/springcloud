这是本人摸索出来的springcloud-demo，经过实践，运行没问题

以下是各个组件重点的配置参数
1️Eureka配置

```
#是否向服务注册中心注册自己，默认值true
eureka.client.register-with-eureka=true
#是否是否检索服务，默认值true
eureka.client.fetch-registry=true

#服务续约（心跳）任务的调用间隔时间，默认30秒
eureka.instance.lease-renewal-interval-in-seconds=30
#服务失效时间，默认90秒
eureka.instance.lease-expiration-duration-in-seconds=90

#Eureka Server维护一份只读的服务清单返回给客户端，客户端缓存清单的更新时间，默认值30秒
eureka.client.registry-fetch-interval-seconds=30

#Eureka Server在启动时候会创建一个定时任务，默认每隔一段时间（默认为60秒）将当期清单中超时
（默认90秒）没有续约的服务剔除出去。所以服务可能90《X《(90+60)下线

#自我保护,默认不关闭，关闭保护机制，确保注册中心可以将不可用的实例正确剔除
eureka.server.enable-self-preservation=false

#服务下线，Eureka Server收到下线请求后，将该服务状态置为下线DOWN，并把该下线事件传播出去

#是否优先使用IP地址作为主机名的标识，默认值false
eureka.instance.prefer-ip-address=true

#默认情况下，Eureka使用Jersey和XStream配合JSON作为Server与Client之间的通讯协议

Eureka强调了CAP原理中的AP，即可用性和可靠性，而ZooKeeper强调CP（一致性和可靠性），Eureka为
了实现服务的可用性，牺牲了一定的一致性

```

2️⃣ribbon配置

```

#该参数用来开启重试机制，默认是关闭的
spring.cloud.loadbalancer.retry.enabled=true

#是否开启熔断器，默认值false 不开启
feign.hystrix.enabled=false

#断路器的超时时间,断路器的超时时间需要大于ribbon的超时时间，不然不会触发重试。默认值：1000毫秒
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=1000
#禁用hystix的超时时间
hystrix.command.default.execution.timeout.enabled=false 

#ribbon请求连接的超时时间，默认值5000毫秒
ribbon.ConnectTimeout=5000
#请求处理的超时时间，默认值2000毫秒
ribbon.ReadTimeout=2000
#对所有操作请求都进行重试
ribbon.OkToRetryOnAllOperations=true
#对当前实例的重试次数
ribbon.MaxAutoRetries=1
#对下个实例的重试次数
ribbon.MaxAutoRetriesNextServer=2

#ribbon全局配置或者指定服务配置<client>.ribbon.key=value
总结：
	如果hystrix没开启，第三方API时间>readtimeout，则直接报readtimeout
	如果开启hystrix，第三方API返回时间>timeoutInMilliseconds则直接熔断，
		跟ribbon配置的ribbon.ConnectTimeout和ribbon.Readtimeout无关
	建议timeoutInMilliseconds>ribbon.ReadTimeout
```


3️⃣Hystrix

```
Hystrix具备服务降级，服务熔断，线程和信号量，请求缓存，请求合并及服务监控等强大功能

Hystrix为了保证不会因为某个依赖服务的问题影响到其他依赖服务而采用了舱壁模式（Bulkhead 
Pattern）来隔离每个依赖的服务


```
4️⃣Feign

```
Feign 整合了Ribbon和Hystrix，声明试的Web服务客户端定义方式，具备可插拔的注解支持

#Feign支持请求与响应进行GZIP压缩，以减少通信过程中的性能损耗

#开启请求与响应的压缩功能
feign.compression.request.enabled=true
feign.compression.response.enabled=true

#支持压缩的mime types
feign.compression.request.enabled=true
feign.compression.request.mime-types=text/xml,application/xml,application/json
feign.compression.request.min-request-size=2048


```
