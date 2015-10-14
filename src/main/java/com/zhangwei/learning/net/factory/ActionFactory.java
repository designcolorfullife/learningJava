package com.zhangwei.learning.net.factory;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.zhangwei.learning.enums.ActionEnum;
import com.zhangwei.learning.net.factory.processor.ActionProcessor;
import com.zhangwei.learning.net.factory.processor.DownLoadActionProcessor;
import com.zhangwei.learning.net.vo.TranslationVO;
import com.zhangwei.learning.utils.LoggerUtils;

public class ActionFactory {
	private Map<ActionEnum, ActionProcessor> actionMap = new HashMap<ActionEnum, ActionProcessor>();

	public void handle(TranslationVO translationVO, Socket client) {
		actionMap.put(ActionEnum.DOWNLOAD, new DownLoadActionProcessor());

		LoggerUtils.Info("当前得到的动作" + translationVO + "From:"
				+ client.getRemoteSocketAddress());
		ActionProcessor actionProcessor = actionMap.get(translationVO
				.getActionEnum());
		actionProcessor.invoke(translationVO, client);
	}
}
