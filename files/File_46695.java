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
package com.codingapi.txlcn.tm.txmsg;

import com.codingapi.txlcn.txmsg.MessageConstants;
import com.codingapi.txlcn.txmsg.dto.MessageDto;
import com.codingapi.txlcn.txmsg.params.DeleteAspectLogParams;
import com.codingapi.txlcn.txmsg.params.GetAspectLogParams;
import com.codingapi.txlcn.txmsg.params.NotifyConnectParams;
import com.codingapi.txlcn.txmsg.params.NotifyUnitParams;

import java.io.Serializable;

/**
 * 消�?�创建器
 *
 * @author lorne
 */
public class MessageCreator {

    /**
     * 通知TxClient连接
     *
     * @param notifyConnectParams notifyConnectParams
     * @return MessageDto
     */
    public static MessageDto newTxManager(NotifyConnectParams notifyConnectParams) {
        MessageDto msg = new MessageDto();
        msg.setAction(MessageConstants.ACTION_NEW_TXMANAGER);
        msg.setData(notifyConnectParams);
        return msg;
    }

    /**
     * �??交事务组
     *
     * @param notifyUnitParams notifyUnitParams
     * @return MessageDto
     */
    public static MessageDto notifyUnit(NotifyUnitParams notifyUnitParams) {
        MessageDto msg = new MessageDto();
        msg.setGroupId(notifyUnitParams.getGroupId());
        msg.setAction(MessageConstants.ACTION_NOTIFY_UNIT);
        msg.setData(notifyUnitParams);
        return msg;
    }

    /**
     * 正常�?应
     *
     * @param action  action
     * @param message message
     * @return MessageDto
     */
    public static MessageDto okResponse(Serializable message, String action) {
        MessageDto messageDto = new MessageDto();
        messageDto.setState(MessageConstants.STATE_OK);
        messageDto.setAction(action);
        messageDto.setData(message);
        return messageDto;
    }

    /**
     * 失败�?应
     *
     * @param action  action
     * @param message message
     * @return MessageDto
     */
    public static MessageDto failResponse(Serializable message, String action) {
        MessageDto messageDto = new MessageDto();
        messageDto.setAction(action);
        messageDto.setState(MessageConstants.STATE_EXCEPTION);
        messageDto.setData(message);
        return messageDto;
    }

    /**
     * �?务器错误
     *
     * @param action action
     * @return MessageDto
     */
    public static MessageDto serverException(String action) {
        MessageDto messageDto = new MessageDto();
        messageDto.setAction(action);
        messageDto.setState(MessageConstants.STATE_EXCEPTION);
        return messageDto;
    }

    /**
     * 获�?�切�?�日志
     *
     * @param groupId groupId
     * @param unitId  unitId
     * @return aspect log
     */
    public static MessageDto getAspectLog(String groupId, String unitId) {
        GetAspectLogParams getAspectLogParams = new GetAspectLogParams();
        getAspectLogParams.setGroupId(groupId);
        getAspectLogParams.setUnitId(unitId);

        MessageDto messageDto = new MessageDto();
        messageDto.setGroupId(groupId);
        messageDto.setAction(MessageConstants.ACTION_GET_ASPECT_LOG);
        messageDto.setData(getAspectLogParams);
        return messageDto;
    }

    /**
     * 删除切�?�日志
     *
     * @param groupId groupId
     * @param unitId  unitId
     * @return result
     */
    public static MessageDto deleteAspectLog(String groupId, String unitId) {
        DeleteAspectLogParams deleteAspectLogParams = new DeleteAspectLogParams();
        deleteAspectLogParams.setGroupId(groupId);
        deleteAspectLogParams.setUnitId(unitId);

        MessageDto messageDto = new MessageDto();
        messageDto.setData(deleteAspectLogParams);
        messageDto.setAction(MessageConstants.ACTION_DELETE_ASPECT_LOG);
        messageDto.setGroupId(groupId);
        return messageDto;
    }
}
