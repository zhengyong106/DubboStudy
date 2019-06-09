package dubbo.service.xml.provider.service;

import dubbo.service.GreetingService;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String username) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + username;
    }
}
