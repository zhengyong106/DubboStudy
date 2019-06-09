package dubbo.service.api.consumer;

import dubbo.service.RpcContextService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

public class PeerToPeerConsumer {
    public static void main(String[] args) {
        ReferenceConfig<RpcContextService> referenceConfig = new ReferenceConfig<>();
        // 如果点对点直连，可以用referenceConfig.setUrl()指定目标地址，设置url后将绕过注册中心，
        // 其中，协议对应服务提供者的providerConfig.setProtocol()的值，端口对应providerConfig.setPort()的值，
        // 路径对应服务提供者的serviceConfig.setPath()的值，如果未设置path，缺省path为接口名
        referenceConfig.setUrl("dubbo://192.168.10.1:20888/dubbo.service.RpcContextService");
        referenceConfig.setInterface(RpcContextService.class);
        referenceConfig.setApplication(new ApplicationConfig("rpc-context-service-consumer"));
        referenceConfig.setVersion("1.0.0");

        long startTime = System.currentTimeMillis();
        // 和本地bean一样使用xxxService
        RpcContextService rpcContextService = referenceConfig.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        rpcContextService.printRpcContext();
        System.out.printf("RPC use times %sms%n", (System.currentTimeMillis() - startTime));
    }
}
