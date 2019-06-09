package dubbo.service.api.consumer;

import dubbo.service.RpcContextService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.ArrayList;
import java.util.List;

public class Consumer {
    public static void main(String[] args) {
        // 当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("rpc-context-service-consumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://192.168.10.10:2181");
        // registry.setUsername("aaa");
        // registry.setPassword("bbb");

        // 方法级配置
        List<MethodConfig> methods = new ArrayList<MethodConfig>();
        MethodConfig method = new MethodConfig();
        method.setName("pringRpcContext");
        method.setTimeout(1000);
        method.setRetries(0);
        methods.add(method);

        // 引用远程服务
        ReferenceConfig<RpcContextService> referenceConfig = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registry); // 多个注册中心可以用setRegistries()
        referenceConfig.setInterface(RpcContextService.class);
        referenceConfig.setMethods(methods);
        referenceConfig.setVersion("1.0.0");

        // 和本地bean一样使用xxxService
        RpcContextService rpcContextService = referenceConfig.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        // 调用远程方法
        long startTime = System.currentTimeMillis();
        rpcContextService.printRpcContext();
        System.out.printf("RPC use times %sms%n", (System.currentTimeMillis() - startTime));
    }
}
