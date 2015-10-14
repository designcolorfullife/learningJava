package com.zhangwei.learning.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by design on 15-10-15.
 */
public class ConfigUtil {

    public static void init(Map<String, String> configsMap, String filename) {

        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource(
                    filename).getInputStream();
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

            if (properties.isEmpty()) {
                LoggerUtils.Info("配置文件初始化失败");
                throw new Exception("配置文件初始化失败");

            } else {
                for (Map.Entry<Object, Object> valuesEntry : properties.entrySet()) {
                    configsMap.put(valuesEntry.getKey().toString(), valuesEntry
                            .getValue().toString());
                    LoggerUtils.Info("初始化配置,Key:" + valuesEntry.getKey()
                            + "|value:" + valuesEntry.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
