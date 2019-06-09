package dubbo.service.api.provider.service;

import dubbo.service.RpcContextService;
import org.apache.dubbo.rpc.RpcContext;

public class RpcContextServiceImpl implements RpcContextService {

    /**
     * RpcContext 是一个 ThreadLocal 的临时状态记录器，当接收到 RPC 请求，或发起 RPC 请求时，RpcContext 的状态都会变化。
     * 比如：A 调 B，B 再调 C，则 B 机器上，在 B 调 C 之前，RpcContext 记录的是 A 调 B 的信息，在 B 调 C 之后，RpcContext 记录的是 B 调 C 的信息。
     */
    @Override
    public void printRpcContext() {
        RpcContext rpcContext =  RpcContext.getContext();

        // 本端是否为提供端，这里会返回true
        boolean isProviderSide = rpcContext.isProviderSide();
        System.out.println("isProviderSide: " + isProviderSide);

        // 获取调用方IP地址
        String remoteHost = rpcContext.getRemoteHost();
        int remotePort = rpcContext.getRemotePort();
        System.out.printf("remoteHost: %s remotePort: %s \n", remoteHost, remotePort);

        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String application = rpcContext.getUrl().getParameter("application");
        System.out.println("currentApplicationName: " + application);

        // 注意：每发起RPC调用，上下文状态会变化
        //yyyService.yyy();
        // 此时本端变成消费端，这里会返回false
        //boolean isProviderSide = rpcContext.isProviderSide();
    }
}
