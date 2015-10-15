package com.zhangwei.learning.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.zhangwei.learning.enums.ConfigsEnum;
import com.zhangwei.learning.net.handler.SocketHandler;
import com.zhangwei.learning.utils.ConfigUtil;
import org.springframework.core.io.ClassPathResource;

import com.zhangwei.learning.net.handler.GlobalSocketRequestHandler;
import com.zhangwei.learning.utils.LoggerUtils;
import org.springframework.stereotype.Component;

@Component
public class SocketServer {

    private static ServerSocket serverSocket = null;

    private static GlobalSocketRequestHandler globalSocketRequestHandler = new GlobalSocketRequestHandler();

    private static Map<String, String> configsMap = new HashMap<String, String>();

    private static SocketHandler socketHandler = new GlobalSocketRequestHandler();

    private static void InitSocket() {
        String portObject = configsMap
                .get(ConfigsEnum.SERVER_SOCKET_PORT);
        if (portObject != null) {
            try {
                serverSocket = new ServerSocket(Integer.valueOf(portObject));
                LoggerUtils.Info("初始化服务端Socket端口:"
                        + Integer.valueOf(portObject));
            } catch (NumberFormatException e) {
                //
                e.printStackTrace();
            } catch (IOException e) {
                //
                e.printStackTrace();

            }
        }
    }


    public static void runserver(String[] args) {
        try {
            ConfigUtil.init(configsMap, "configs.properties");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        InitSocket();
        //
        try {
            LoggerUtils.Info("服务器开始监听地址" + serverSocket.getInetAddress() + ":"
                    + serverSocket.getLocalPort());
            while (true) {
                Socket newSocket = serverSocket.accept();
                LoggerUtils.Info("建立Socket链接:"
                        + newSocket.getRemoteSocketAddress());
                socketHandler.handle(newSocket);
                LoggerUtils.Info("继续监听下一个请求");
            }
        } catch (IOException e) {
            //
            e.printStackTrace();
        }
    }

    public static String getConfig(String a) {
        return configsMap.get(a);
    }
}
