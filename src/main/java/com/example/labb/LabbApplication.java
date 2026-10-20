package com.example.labb;

import com.example.labb.difexamples.ClientComponent;
import com.example.labb.difexamples.SingletonComponent;
import com.example.labb.difexamples.TransientComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LabbApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LabbApplication.class, args);

        TransientComponent transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();
        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        SingletonComponent singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();
        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        ClientComponent c = context.getBean(ClientComponent.class);
        c.operation();
        c = (ClientComponent) context.getBean("clientComponent");
        c.operation();
    }
}