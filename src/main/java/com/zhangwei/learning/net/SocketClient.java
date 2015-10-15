package com.zhangwei.learning.net;

import com.alibaba.fastjson.JSON;
import com.zhangwei.learning.enums.ActionEnum;
import com.zhangwei.learning.net.vo.TranslationVO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {

	private static final int port = 8999;
	private static final String addressString = "localhost";
	private static Socket socket = null;

	public static void main(String[] args) {
		//
		String[] urls = {

				"http://www.baidu.com","http://www.baidu.com","http://www.baidu.com","http://www.baidu.com","http://www.baidu.com" };
		try {

			for (String string : urls) {
                socket = new Socket(addressString, port);
				DataOutputStream dos = new DataOutputStream(
						socket.getOutputStream());
				TranslationVO translationVO = new TranslationVO();
				translationVO.setActionEnum(ActionEnum.DOWNLOAD);
				translationVO.setData(string);
				dos.writeUTF(JSON.toJSONString(translationVO));
                socket.close();
			}
		} catch (IOException e) {
			//
			e.printStackTrace();
		}

	}

}
