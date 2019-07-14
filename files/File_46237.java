/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.transport.bolt;

import com.alipay.remoting.Connection;
import com.alipay.remoting.Url;
import com.alipay.remoting.exception.RemotingException;
import com.alipay.remoting.rpc.RpcClient;
import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;
import com.alipay.sofa.rpc.transport.ClientTransportConfig;

/**
 * @author <a href="mailto:zhiyuan.lzy@antfin.com">zhiyuan.lzy</a>
 */
class AloneBoltClientConnectionManager extends BoltClientConnectionManager {

    public AloneBoltClientConnectionManager(boolean addHook) {
        super(addHook);
    }

    @Override
    protected void checkLeak() {
        //do not check
    }

    /**
     * 通过�?置获�?�长连接
     *
     * @param rpcClient       bolt客户端
     * @param transportConfig 传输层�?置
     * @param url             传输层地�?�
     * @return 长连接
     */
    public Connection getConnection(RpcClient rpcClient, ClientTransportConfig transportConfig, Url url) {
        if (rpcClient == null || transportConfig == null || url == null) {
            return null;
        }
        Connection connection;
        try {
            connection = rpcClient.getConnection(url, url.getConnectTimeout());
        } catch (InterruptedException e) {
            throw new SofaRpcRuntimeException(e);
        } catch (RemotingException e) {
            throw new SofaRpcRuntimeException(e);
        }
        if (connection == null) {
            return null;
        }

        return connection;
    }

    /**
     * 关闭长连接
     *
     * @param rpcClient       bolt客户端
     * @param transportConfig 传输层�?置
     * @param url             传输层地�?�
     */
    public void closeConnection(RpcClient rpcClient, ClientTransportConfig transportConfig, Url url) {
        if (rpcClient == null || transportConfig == null || url == null) {
            return;
        }
        //TODO do not close
    }

    @Override
    public boolean isConnectionFine(RpcClient rpcClient, ClientTransportConfig transportConfig, Url url) {
        Connection connection;
        try {
            connection = rpcClient.getConnection(url, url.getConnectTimeout());
        } catch (RemotingException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }

        return connection != null && connection.isFine();
    }
}
