/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.pay.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * <b>功能说明:龙果支付属性�?置工具类
 * </b>
 * @author  Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
public class PayConfigUtil {

    private static final Log LOG = LogFactory.getLog(PayConfigUtil.class);

    /**
     * 通过�?��?代�?�?�读�?�上传文件的验�?格�?�?置文件,�?��?代�?�?��?�执行一次(�?�例)
     */
    private static Properties properties = new Properties();

    private PayConfigUtil() {

    }

    // 通过类装载器装载进�?�
    static {
        try {
            // 从类路径下读�?�属性文件
            properties.load(PayConfigUtil.class.getClassLoader()
                    .getResourceAsStream("pay_config.properties"));
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    /**
     * 函数功能说明 ：读�?��?置项 Administrator 2012-12-14 修改者�??字 ： 修改日期 ： 修改内容 ：
     *
     * @�?�数：
     * @return void
     * @throws
     */
    public static String readConfig(String key) {
        return (String) properties.get(key);
    }
}
