package dubbo.service.xml.consumer;

import dubbo.service.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:/consumer.xml"});
        context.start();

        GreetingService greetingService = (GreetingService)context.getBean("greetingService"); // 获取远程服务代理

        long startTime = System.currentTimeMillis();
        System.out.println( greetingService.sayHello("Michael") );
        System.out.println( greetingService.sayHello("Michael") );
        System.out.println( greetingService.sayHello("Michael") );
        System.out.println( greetingService.sayHello("Michael") ); // 执行远程方法
        System.out.printf("RPC use times %sms%n", (System.currentTimeMillis() - startTime));
    }
}