package dubbo.service.annotation.provider.service;

import dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String username) {
        return "hello " + username;
    }
}
