package com.zhangwei.learning.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

import com.zhangwei.learning.enums.ServerSocketConfig;
import com.zhangwei.learning.net.handler.GlobalSocketRequestHandler;
import com.zhangwei.learning.utils.LoggerUtils;

public class SocketServer {

	private static ServerSocket serverSocket = null;

	private static GlobalSocketRequestHandler globalSocketRequestHandler = new GlobalSocketRequestHandler();

	private static Map<String, String> configsMap = new HashMap<String, String>();

	private static  void InitConfig(){
		Properties properties = new Properties();

		try {
//			InputStream inputStream = SocketServer.class.getClassLoader().getResourceAsStream("serversocket.properties");
			InputStream inputStream = new ClassPathResource(
					"resources/config/serversocket.properties").getInputStream();
			properties.load(inputStream);
			inputStream.close();

		} catch (IOException e) {
			//
			e.printStackTrace();
		}
		if (properties.isEmpty()) {
			LoggerUtils.Info("配置文件初始化失败");
		} else {
			for (Entry<Object, Object> valuesEntry : properties.entrySet()) {
				configsMap.put(valuesEntry.getKey().toString(), valuesEntry
						.getValue().toString());
				LoggerUtils.Info("初始化配置,Key:" + valuesEntry.getKey()
						+ "|value:" + valuesEntry.getValue());
			}
		}
	}

	private static void InitSocket() {
		String portObject = configsMap
				.get(ServerSocketConfig.SERVER_SOCKET_PORT);
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


	public static void main(String[] args) {
		InitConfig();
		InitSocket();
		//
		try {
			LoggerUtils.Info("服务器开始监听地址" + serverSocket.getInetAddress() + ":"
					+ serverSocket.getLocalPort());
			while (true) {
				Socket newSocket = serverSocket.accept();
				LoggerUtils.Info("建立Socket链接:"
						+ newSocket.getRemoteSocketAddress());
				globalSocketRequestHandler.handle(newSocket);
				LoggerUtils.Info("继续监听下一个请求");
			}
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}

}
