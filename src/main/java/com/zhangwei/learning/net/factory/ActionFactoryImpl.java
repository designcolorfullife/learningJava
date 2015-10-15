package com.zhangwei.learning.net.factory;

import com.zhangwei.learning.enums.ActionEnum;
import com.zhangwei.learning.net.processor.ActionProcessor;
import com.zhangwei.learning.net.processor.DownLoadActionProcessor;
import com.zhangwei.learning.net.vo.TranslationVO;
import com.zhangwei.learning.utils.LoggerUtils;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by design on 15-10-15.
 */
public class ActionFactoryImpl implements ActionFactory{

    private Map<String, ActionProcessor> actionMap;

    public void handle(TranslationVO translationVO, Socket client) {


        LoggerUtils.Info("当前得到的动作" + translationVO + "From:"
                + client.getRemoteSocketAddress());
        ActionProcessor actionProcessor = actionMap.get(translationVO
                .getActionEnum().getCode());
        actionProcessor.invoke(translationVO, client);
    }

    public void setActionMap(Map<String, ActionProcessor> actionMap) {
        this.actionMap = actionMap;
    }
}
