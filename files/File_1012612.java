package com.freddy.im;

import com.freddy.im.interf.IMSClientInterface;
import com.freddy.im.protobuf.MessageProtobuf;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.util.internal.StringUtil;

/**
 * <p>@ProjectName:     NettyChat</p>
 * <p>@ClassName:       MsgTimeoutTimerManager.java</p>
 * <p>@PackageName:     com.freddy.im</p>
 * <b>
 * <p>@Description:     æ¶ˆæ?¯å?‘é€?è¶…æ—¶ç®¡ç?†å™¨ï¼Œç”¨äºŽç®¡ç?†æ¶ˆæ?¯å®šæ—¶å™¨çš„æ–°å¢žã€?ç§»é™¤ç­‰</p>
 * </b>
 * <p>@author:          FreddyChen</p>
 * <p>@date:            2019/04/09 22:42</p>
 * <p>@email:           chenshichao@outlook.com</p>
 */
public class MsgTimeoutTimerManager {

    private Map<String, MsgTimeoutTimer> mMsgTimeoutMap = new ConcurrentHashMap<>();
    private IMSClientInterface imsClient;// imså®¢æˆ·ç«¯

    public MsgTimeoutTimerManager(IMSClientInterface imsClient) {
        this.imsClient = imsClient;
    }

    /**
     * æ·»åŠ æ¶ˆæ?¯åˆ°å?‘é€?è¶…æ—¶ç®¡ç?†å™¨
     *
     * @param msg
     */
    public void add(MessageProtobuf.Msg msg) {
        if (msg == null || msg.getHead() == null) {
            return;
        }

        int handshakeMsgType = -1;
        int heartbeatMsgType = -1;
        int clientReceivedReportMsgType = imsClient.getClientReceivedReportMsgType();
        MessageProtobuf.Msg handshakeMsg = imsClient.getHandshakeMsg();
        if (handshakeMsg != null && handshakeMsg.getHead() != null) {
            handshakeMsgType = handshakeMsg.getHead().getMsgType();
        }
        MessageProtobuf.Msg heartbeatMsg = imsClient.getHeartbeatMsg();
        if (heartbeatMsg != null && heartbeatMsg.getHead() != null) {
            heartbeatMsgType = heartbeatMsg.getHead().getMsgType();
        }

        int msgType = msg.getHead().getMsgType();
        // æ?¡æ‰‹æ¶ˆæ?¯ã€?å¿ƒè·³æ¶ˆæ?¯ã€?å®¢æˆ·ç«¯è¿”å›žçš„çŠ¶æ€?æŠ¥å‘Šæ¶ˆæ?¯ï¼Œä¸?ç”¨é‡?å?‘ã€‚
        if (msgType == handshakeMsgType || msgType == heartbeatMsgType || msgType == clientReceivedReportMsgType) {
            return;
        }

        String msgId = msg.getHead().getMsgId();
        if (!mMsgTimeoutMap.containsKey(msgId)) {
            MsgTimeoutTimer timer = new MsgTimeoutTimer(imsClient, msg);
            mMsgTimeoutMap.put(msgId, timer);
        }

        System.out.println("æ·»åŠ æ¶ˆæ?¯è¶…å?‘é€?è¶…æ—¶ç®¡ç?†å™¨ï¼Œmessage=" + msg + "\tå½“å‰?ç®¡ç?†å™¨æ¶ˆæ?¯æ•°ï¼š" + mMsgTimeoutMap.size());
    }

    /**
     * ä»Žå?‘é€?è¶…æ—¶ç®¡ç?†å™¨ä¸­ç§»é™¤æ¶ˆæ?¯ï¼Œå¹¶å?œæ­¢å®šæ—¶å™¨
     *
     * @param msgId
     */
    public void remove(String msgId) {
        if (StringUtil.isNullOrEmpty(msgId)) {
            return;
        }

        MsgTimeoutTimer timer = mMsgTimeoutMap.remove(msgId);
        MessageProtobuf.Msg msg = null;
        if (timer != null) {
            msg = timer.getMsg();
            timer.cancel();
            timer = null;
        }

        System.out.println("ä»Žå?‘é€?æ¶ˆæ?¯ç®¡ç?†å™¨ç§»é™¤æ¶ˆæ?¯ï¼Œmessage=" + msg);
    }

    /**
     * é‡?è¿žæˆ?åŠŸå›žè°ƒï¼Œé‡?è¿žå¹¶æ?¡æ‰‹æˆ?åŠŸæ—¶ï¼Œé‡?å?‘æ¶ˆæ?¯å?‘é€?è¶…æ—¶ç®¡ç?†å™¨ä¸­æ‰€æœ‰çš„æ¶ˆæ?¯
     */
    public synchronized void onResetConnected() {
        for(Iterator<Map.Entry<String, MsgTimeoutTimer>> it = mMsgTimeoutMap.entrySet().iterator(); it.hasNext();) {
            it.next().getValue().sendMsg();
        }
    }
}
