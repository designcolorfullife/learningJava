package com.zhangwei.learning.net;

import com.zhangwei.learning.net.SocketServer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by design on 15-10-15.
 */
public class SocketServerSpring {
    public static void main(String[] args) {
        BeanFactory beanFactory  = new ClassPathXmlApplicationContext("spring.xml");
        SocketServer socketServer = (SocketServer) beanFactory.getBean("socketServer");
        socketServer.runserver();
    }
}
