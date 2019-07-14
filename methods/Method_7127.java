protected void performSendEncryptedRequest(final TLRPC.DecryptedMessage req,final TLRPC.Message newMsgObj,final TLRPC.EncryptedChat chat,final TLRPC.InputEncryptedFile encryptedFile,final String originalPath,final MessageObject newMsg){
  if (req == null || chat.auth_key == null || chat instanceof TLRPC.TL_encryptedChatRequested || chat instanceof TLRPC.TL_encryptedChatWaiting) {
    return;
  }
  SendMessagesHelper.getInstance(currentAccount).putToSendingMessages(newMsgObj);
  Utilities.stageQueue.postRunnable(() -> {
    try {
      TLObject toEncryptObject;
      TLRPC.TL_decryptedMessageLayer layer=new TLRPC.TL_decryptedMessageLayer();
      int myLayer=Math.max(46,AndroidUtilities.getMyLayerVersion(chat.layer));
      layer.layer=Math.min(myLayer,Math.max(46,AndroidUtilities.getPeerLayerVersion(chat.layer)));
      layer.message=req;
      layer.random_bytes=new byte[15];
      Utilities.random.nextBytes(layer.random_bytes);
      toEncryptObject=layer;
      int mtprotoVersion=AndroidUtilities.getPeerLayerVersion(chat.layer) >= 73 ? 2 : 1;
      if (chat.seq_in == 0 && chat.seq_out == 0) {
        if (chat.admin_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
          chat.seq_out=1;
          chat.seq_in=-2;
        }
 else {
          chat.seq_in=-1;
        }
      }
      if (newMsgObj.seq_in == 0 && newMsgObj.seq_out == 0) {
        layer.in_seq_no=chat.seq_in > 0 ? chat.seq_in : chat.seq_in + 2;
        layer.out_seq_no=chat.seq_out;
        chat.seq_out+=2;
        if (AndroidUtilities.getPeerLayerVersion(chat.layer) >= 20) {
          if (chat.key_create_date == 0) {
            chat.key_create_date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
          }
          chat.key_use_count_out++;
          if ((chat.key_use_count_out >= 100 || chat.key_create_date < ConnectionsManager.getInstance(currentAccount).getCurrentTime() - 60 * 60 * 24 * 7) && chat.exchange_id == 0 && chat.future_key_fingerprint == 0) {
            requestNewSecretChatKey(chat);
          }
        }
        MessagesStorage.getInstance(currentAccount).updateEncryptedChatSeq(chat,false);
        if (newMsgObj != null) {
          newMsgObj.seq_in=layer.in_seq_no;
          newMsgObj.seq_out=layer.out_seq_no;
          MessagesStorage.getInstance(currentAccount).setMessageSeq(newMsgObj.id,newMsgObj.seq_in,newMsgObj.seq_out);
        }
      }
 else {
        layer.in_seq_no=newMsgObj.seq_in;
        layer.out_seq_no=newMsgObj.seq_out;
      }
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d(req + " send message with in_seq = " + layer.in_seq_no + " out_seq = " + layer.out_seq_no);
      }
      int len=toEncryptObject.getObjectSize();
      NativeByteBuffer toEncrypt=new NativeByteBuffer(4 + len);
      toEncrypt.writeInt32(len);
      toEncryptObject.serializeToStream(toEncrypt);
      len=toEncrypt.length();
      int extraLen=len % 16 != 0 ? 16 - len % 16 : 0;
      if (mtprotoVersion == 2) {
        extraLen+=(2 + Utilities.random.nextInt(3)) * 16;
      }
      NativeByteBuffer dataForEncryption=new NativeByteBuffer(len + extraLen);
      toEncrypt.position(0);
      dataForEncryption.writeBytes(toEncrypt);
      if (extraLen != 0) {
        byte[] b=new byte[extraLen];
        Utilities.random.nextBytes(b);
        dataForEncryption.writeBytes(b);
      }
      byte[] messageKey=new byte[16];
      byte[] messageKeyFull;
      boolean incoming=mtprotoVersion == 2 && chat.admin_id != UserConfig.getInstance(currentAccount).getClientUserId();
      if (mtprotoVersion == 2) {
        messageKeyFull=Utilities.computeSHA256(chat.auth_key,88 + (incoming ? 8 : 0),32,dataForEncryption.buffer,0,dataForEncryption.buffer.limit());
        System.arraycopy(messageKeyFull,8,messageKey,0,16);
      }
 else {
        messageKeyFull=Utilities.computeSHA1(toEncrypt.buffer);
        System.arraycopy(messageKeyFull,messageKeyFull.length - 16,messageKey,0,16);
      }
      toEncrypt.reuse();
      MessageKeyData keyData=MessageKeyData.generateMessageKeyData(chat.auth_key,messageKey,incoming,mtprotoVersion);
      Utilities.aesIgeEncryption(dataForEncryption.buffer,keyData.aesKey,keyData.aesIv,true,false,0,dataForEncryption.limit());
      NativeByteBuffer data=new NativeByteBuffer(8 + messageKey.length + dataForEncryption.length());
      dataForEncryption.position(0);
      data.writeInt64(chat.key_fingerprint);
      data.writeBytes(messageKey);
      data.writeBytes(dataForEncryption);
      dataForEncryption.reuse();
      data.position(0);
      TLObject reqToSend;
      if (encryptedFile == null) {
        if (req instanceof TLRPC.TL_decryptedMessageService) {
          TLRPC.TL_messages_sendEncryptedService req2=new TLRPC.TL_messages_sendEncryptedService();
          req2.data=data;
          req2.random_id=req.random_id;
          req2.peer=new TLRPC.TL_inputEncryptedChat();
          req2.peer.chat_id=chat.id;
          req2.peer.access_hash=chat.access_hash;
          reqToSend=req2;
        }
 else {
          TLRPC.TL_messages_sendEncrypted req2=new TLRPC.TL_messages_sendEncrypted();
          req2.data=data;
          req2.random_id=req.random_id;
          req2.peer=new TLRPC.TL_inputEncryptedChat();
          req2.peer.chat_id=chat.id;
          req2.peer.access_hash=chat.access_hash;
          reqToSend=req2;
        }
      }
 else {
        TLRPC.TL_messages_sendEncryptedFile req2=new TLRPC.TL_messages_sendEncryptedFile();
        req2.data=data;
        req2.random_id=req.random_id;
        req2.peer=new TLRPC.TL_inputEncryptedChat();
        req2.peer.chat_id=chat.id;
        req2.peer.access_hash=chat.access_hash;
        req2.file=encryptedFile;
        reqToSend=req2;
      }
      ConnectionsManager.getInstance(currentAccount).sendRequest(reqToSend,(response,error) -> {
        if (error == null) {
          if (req.action instanceof TLRPC.TL_decryptedMessageActionNotifyLayer) {
            TLRPC.EncryptedChat currentChat=MessagesController.getInstance(currentAccount).getEncryptedChat(chat.id);
            if (currentChat == null) {
              currentChat=chat;
            }
            if (currentChat.key_hash == null) {
              currentChat.key_hash=AndroidUtilities.calcAuthKeyHash(currentChat.auth_key);
            }
            if (AndroidUtilities.getPeerLayerVersion(currentChat.layer) >= 46 && currentChat.key_hash.length == 16) {
              try {
                byte[] sha256=Utilities.computeSHA256(chat.auth_key,0,chat.auth_key.length);
                byte[] key_hash=new byte[36];
                System.arraycopy(chat.key_hash,0,key_hash,0,16);
                System.arraycopy(sha256,0,key_hash,16,20);
                currentChat.key_hash=key_hash;
                MessagesStorage.getInstance(currentAccount).updateEncryptedChat(currentChat);
              }
 catch (              Throwable e) {
                FileLog.e(e);
              }
            }
            sendingNotifyLayer.remove((Integer)currentChat.id);
            currentChat.layer=AndroidUtilities.setMyLayerVersion(currentChat.layer,CURRENT_SECRET_CHAT_LAYER);
            MessagesStorage.getInstance(currentAccount).updateEncryptedChatLayer(currentChat);
          }
        }
        if (newMsgObj != null) {
          if (error == null) {
            final String attachPath=newMsgObj.attachPath;
            final TLRPC.messages_SentEncryptedMessage res=(TLRPC.messages_SentEncryptedMessage)response;
            if (isSecretVisibleMessage(newMsgObj)) {
              newMsgObj.date=res.date;
            }
            int existFlags;
            if (newMsg != null && res.file instanceof TLRPC.TL_encryptedFile) {
              updateMediaPaths(newMsg,res.file,req,originalPath);
              existFlags=newMsg.getMediaExistanceFlags();
            }
 else {
              existFlags=0;
            }
            MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
              if (isSecretInvisibleMessage(newMsgObj)) {
                res.date=0;
              }
              MessagesStorage.getInstance(currentAccount).updateMessageStateAndId(newMsgObj.random_id,newMsgObj.id,newMsgObj.id,res.date,false,0);
              AndroidUtilities.runOnUIThread(() -> {
                newMsgObj.send_state=MessageObject.MESSAGE_SEND_STATE_SENT;
                NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer,newMsgObj.id,newMsgObj.id,newMsgObj,newMsgObj.dialog_id,0L,existFlags);
                SendMessagesHelper.getInstance(currentAccount).processSentMessage(newMsgObj.id);
                if (MessageObject.isVideoMessage(newMsgObj) || MessageObject.isNewGifMessage(newMsgObj) || MessageObject.isRoundVideoMessage(newMsgObj)) {
                  SendMessagesHelper.getInstance(currentAccount).stopVideoService(attachPath);
                }
                SendMessagesHelper.getInstance(currentAccount).removeFromSendingMessages(newMsgObj.id);
              }
);
            }
);
          }
 else {
            MessagesStorage.getInstance(currentAccount).markMessageAsSendError(newMsgObj);
            AndroidUtilities.runOnUIThread(() -> {
              newMsgObj.send_state=MessageObject.MESSAGE_SEND_STATE_SEND_ERROR;
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messageSendError,newMsgObj.id);
              SendMessagesHelper.getInstance(currentAccount).processSentMessage(newMsgObj.id);
              if (MessageObject.isVideoMessage(newMsgObj) || MessageObject.isNewGifMessage(newMsgObj) || MessageObject.isRoundVideoMessage(newMsgObj)) {
                SendMessagesHelper.getInstance(currentAccount).stopVideoService(newMsgObj.attachPath);
              }
              SendMessagesHelper.getInstance(currentAccount).removeFromSendingMessages(newMsgObj.id);
            }
);
          }
        }
      }
,ConnectionsManager.RequestFlagInvokeAfter);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
