package dubbo.annotation.service.consumer;

import dubbo.annotation.service.consumer.action.AnnotationAction;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "dubbo.annotation.service.consumer.action")
@PropertySource("classpath:/dubbo-consumer.properties")
public class Consumer {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Consumer.class, args);
        final AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        System.out.println(annotationAction.doSayHello("Michael"));
    }
}
