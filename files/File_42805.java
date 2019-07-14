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

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * <b>功能说明:JsonUtils工具类,用�?�通过�?的方�?将Json数�?�写回�?端
 * </b>
 * @author  Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
public class JsonUtils {

    private static final Log LOG = LogFactory.getLog(JsonUtils.class);

    /**
     * 构造函数�?有化
     */
    private JsonUtils (){}

    /**
     * 将请求中的Json�?转�?��?Json对象
     * @param httpServletRequest
     * @return
     */
    public static JSONObject requestJson(HttpServletRequest httpServletRequest){
        StringBuffer buffer = new StringBuffer();
        String line = null;
        JSONObject jsonObject = null;
        try {
            BufferedReader reader = httpServletRequest.getReader();
            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch(Exception e) {
            LOG.error(e);
        }
        return jsonObject;
    }


    /**
     * 将Map内的�?�数,转�?��?Json实体,并写出
     * @param response
     * @param object
     * @throws IOException
     */
    public static void responseJson(HttpServletResponse response,
                                    Object object) throws IOException {


        Object toJSON = JSONObject.toJSON(object);
        try {
            response.getWriter().write(toJSON.toString());
        } catch (IOException e) {
            LOG.error(e);
        }
    }

}
