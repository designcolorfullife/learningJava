package com.zhangwei.learning.net.handler;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.zhangwei.learning.net.factory.ActionFactory;
import com.zhangwei.learning.net.factory.ActionFactoryImpl;
import com.zhangwei.learning.net.vo.TranslationVO;
import com.zhangwei.learning.threadpool.GlobalThreadPool;

/**
 * socket请求的处理Handler
 * 
 * @author Administrator
 * 
 */
public class GlobalSocketRequestHandler implements SocketHandler{

	private ActionFactory actionFactory = new ActionFactoryImpl();

	/**
	 * 从客户端读取请求,渲染出对应的动作并且交给动作工厂处理
	 * 
	 * @param client
	 */
	public void handle(final Socket client) {
		GlobalThreadPool.submitTaskWithoutResult(new Runnable() {

			public void run() {
				TranslationVO translationVO = getTranslationVO(client);
				if (translationVO == null) {
					return;
				}
				actionFactory.handle(translationVO, client);
			}
		});
	}

	private TranslationVO getTranslationVO(Socket client) {
		DataInputStream dis = null;
		TranslationVO translationVO = null;
		try {
			dis = new DataInputStream(client.getInputStream());
			translationVO = JSON
					.parseObject(dis.readUTF(), TranslationVO.class);
			client.shutdownInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (dis != null)
					dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return translationVO;
	}
}
