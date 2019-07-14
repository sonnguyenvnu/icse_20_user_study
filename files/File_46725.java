/*
 * Copyright 2017-2019 CodingApi .
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
package com.codingapi.txlcn.txmsg;

import com.codingapi.txlcn.txmsg.dto.AppInfo;
import com.codingapi.txlcn.txmsg.dto.RpcResponseState;
import com.codingapi.txlcn.txmsg.loadbalance.RpcLoadBalance;
import com.codingapi.txlcn.txmsg.dto.MessageDto;
import com.codingapi.txlcn.txmsg.dto.RpcCmd;
import com.codingapi.txlcn.txmsg.exception.RpcException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/10
 *
 * @author ujued
 */
public abstract class RpcClient {

    @Autowired
    private RpcLoadBalance rpcLoadBalance;

    /**
     * �?��?指令�?需�?返回数�?�，需�?知�?�返回的状�?
     *
     * @param rpcCmd 指令内容
     * @return 指令状�?
     * @throws RpcException 远程调用请求异常
     */
    public abstract RpcResponseState send(RpcCmd rpcCmd) throws RpcException;


    /**
     * �?��?指令�?需�?返回数�?�，需�?知�?�返回的状�?
     *
     * @param remoteKey 远程标识关键字
     * @param msg       指令内容
     * @return 指令状�?
     * @throws RpcException 远程调用请求异常
     */
    public abstract RpcResponseState send(String remoteKey, MessageDto msg) throws RpcException;


    /**
     * �?��?请求并获�?��?应
     *
     * @param rpcCmd 指令内容
     * @return �?应指令数�?�
     * @throws RpcException 远程调用请求异常
     */
    public abstract MessageDto request(RpcCmd rpcCmd) throws RpcException;


    /**
     * �?��?请求并�?应
     *
     * @param remoteKey 远程标识关键字
     * @param msg       请求内容
     * @return 相应指令数�?�
     * @throws RpcException 远程调用请求异常
     */
    public abstract MessageDto request(String remoteKey, MessageDto msg) throws RpcException;

    /**
     * �?��?请求并获�?��?应
     *
     * @param remoteKey 远程标识关键字
     * @param msg       请求内容
     * @param timeout   超时时间
     * @return �?应消�?�
     * @throws RpcException 远程调用请求异常
     */
    public abstract MessageDto request(String remoteKey, MessageDto msg, long timeout) throws RpcException;


    /**
     * 获�?�一个远程标识关键字
     *
     * @return 远程标识关键字
     * @throws RpcException 远程调用请求异常
     */
    public String loadRemoteKey() throws RpcException {
        return rpcLoadBalance.getRemoteKey();
    }


    /**
     * 获�?�所有的远程连接对象
     *
     * @return 远程连接对象数组.
     */
    public abstract List<String> loadAllRemoteKey();


    /**
     * 获�?�模�?�远程标识
     *
     * @param moduleName 模�?��??称
     * @return 远程标识
     */
    public abstract List<String> remoteKeys(String moduleName);


    /**
     * 绑定模�?��??称
     *
     * @param remoteKey 远程标识
     * @param appName   应用�??称
     * @param labelName  TC标识�??称
     * @throws RpcException   RpcException
     */
    public abstract void bindAppName(String remoteKey, String appName,String labelName) throws RpcException;


    /**
     * 获�?�模�?��??称
     *
     * @param remoteKey 远程标识
     * @return 应用�??称
     */
    public abstract String getAppName(String remoteKey);


    /**
     * 获�?�所有的模�?�信�?�
     *
     * @return 应用�??称
     */
    public abstract List<AppInfo> apps();

}
