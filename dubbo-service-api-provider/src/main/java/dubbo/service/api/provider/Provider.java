package dubbo.service.api.provider;

import dubbo.service.RpcContextService;
import dubbo.service.api.provider.service.RpcContextServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        // 当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("rpc-context-service-provider");

        // 连接注册中心配置
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("192.168.10.10:2181");
        // registry.setUsername("aaa");
        // registry.setPassword("bbb");

        // 服务提供者协议配置，可不填
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20888);
        protocolConfig.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        ServiceConfig<RpcContextService> serviceConfig = new ServiceConfig<>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig); // 多个注册中心可以用setRegistries()
        serviceConfig.setProtocol(protocolConfig); // 多个协议可以用setProtocols()
        serviceConfig.setInterface(RpcContextService.class);
        serviceConfig.setRef(new RpcContextServiceImpl());
        serviceConfig.setVersion("1.0.0");

        // 暴露及注册服务
        serviceConfig.export();
        System.in.read();
    }
}
