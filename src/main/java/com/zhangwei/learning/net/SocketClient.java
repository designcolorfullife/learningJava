package com.zhangwei.learning.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.alibaba.fastjson.JSON;
import com.zhangwei.learning.enums.ActionEnum;
import com.zhangwei.learning.net.vo.TranslationVO;

public class SocketClient {

	private static final int port = 8080;
	private static final String addressString = "localhost";
	private static Socket socket = null;

	static {
		try {
			socket = new Socket(addressString, port);
		} catch (UnknownHostException e) {
			//
			e.printStackTrace();
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//
		String[] urls = {
				"http://mirrors.ustc.edu.cn/ubuntu-releases/10.04.4/ubuntu-10.04.4-server-i386.iso",
				"http://www.baidu.com" };
		try {

			for (String string : urls) {
				DataOutputStream dos = new DataOutputStream(
						socket.getOutputStream());
				TranslationVO translationVO = new TranslationVO();
				translationVO.setActionEnum(ActionEnum.DOWNLOAD);
				translationVO.setData(string);
				dos.writeUTF(JSON.toJSONString(translationVO));
				dos.close();
				socket.shutdownOutput();
			}
		} catch (IOException e) {
			//
			e.printStackTrace();
		}

	}

}
