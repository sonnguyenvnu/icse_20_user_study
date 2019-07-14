protected ArrayList<TLRPC.Message> decryptMessage(TLRPC.EncryptedMessage message){
  final TLRPC.EncryptedChat chat=MessagesController.getInstance(currentAccount).getEncryptedChatDB(message.chat_id,true);
  if (chat == null || chat instanceof TLRPC.TL_encryptedChatDiscarded) {
    return null;
  }
  try {
    NativeByteBuffer is=new NativeByteBuffer(message.bytes.length);
    is.writeBytes(message.bytes);
    is.position(0);
    long fingerprint=is.readInt64(false);
    byte[] keyToDecrypt=null;
    boolean new_key_used=false;
    if (chat.key_fingerprint == fingerprint) {
      keyToDecrypt=chat.auth_key;
    }
 else     if (chat.future_key_fingerprint != 0 && chat.future_key_fingerprint == fingerprint) {
      keyToDecrypt=chat.future_auth_key;
      new_key_used=true;
    }
    int mtprotoVersion=AndroidUtilities.getPeerLayerVersion(chat.layer) >= 73 ? 2 : 1;
    int decryptedWithVersion=mtprotoVersion;
    if (keyToDecrypt != null) {
      byte[] messageKey=is.readData(16,false);
      boolean incoming=chat.admin_id == UserConfig.getInstance(currentAccount).getClientUserId();
      boolean tryAnotherDecrypt=true;
      if (decryptedWithVersion == 2 && chat.mtproto_seq != 0) {
        tryAnotherDecrypt=false;
      }
      if (!decryptWithMtProtoVersion(is,keyToDecrypt,messageKey,mtprotoVersion,incoming,tryAnotherDecrypt)) {
        if (mtprotoVersion == 2) {
          decryptedWithVersion=1;
          if (!tryAnotherDecrypt || !decryptWithMtProtoVersion(is,keyToDecrypt,messageKey,1,incoming,false)) {
            return null;
          }
        }
 else {
          decryptedWithVersion=2;
          if (!decryptWithMtProtoVersion(is,keyToDecrypt,messageKey,2,incoming,tryAnotherDecrypt)) {
            return null;
          }
        }
      }
      TLObject object=TLClassStore.Instance().TLdeserialize(is,is.readInt32(false),false);
      is.reuse();
      if (!new_key_used && AndroidUtilities.getPeerLayerVersion(chat.layer) >= 20) {
        chat.key_use_count_in++;
      }
      if (object instanceof TLRPC.TL_decryptedMessageLayer) {
        final TLRPC.TL_decryptedMessageLayer layer=(TLRPC.TL_decryptedMessageLayer)object;
        if (chat.seq_in == 0 && chat.seq_out == 0) {
          if (chat.admin_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
            chat.seq_out=1;
            chat.seq_in=-2;
          }
 else {
            chat.seq_in=-1;
          }
        }
        if (layer.random_bytes.length < 15) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.e("got random bytes less than needed");
          }
          return null;
        }
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("current chat in_seq = " + chat.seq_in + " out_seq = " + chat.seq_out);
          FileLog.d("got message with in_seq = " + layer.in_seq_no + " out_seq = " + layer.out_seq_no);
        }
        if (layer.out_seq_no <= chat.seq_in) {
          return null;
        }
        if (decryptedWithVersion == 1 && chat.mtproto_seq != 0 && layer.out_seq_no >= chat.mtproto_seq) {
          return null;
        }
        if (chat.seq_in != layer.out_seq_no - 2) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.e("got hole");
          }
          ArrayList<TL_decryptedMessageHolder> arr=secretHolesQueue.get(chat.id);
          if (arr == null) {
            arr=new ArrayList<>();
            secretHolesQueue.put(chat.id,arr);
          }
          if (arr.size() >= 4) {
            secretHolesQueue.remove(chat.id);
            final TLRPC.TL_encryptedChatDiscarded newChat=new TLRPC.TL_encryptedChatDiscarded();
            newChat.id=chat.id;
            newChat.user_id=chat.user_id;
            newChat.auth_key=chat.auth_key;
            newChat.key_create_date=chat.key_create_date;
            newChat.key_use_count_in=chat.key_use_count_in;
            newChat.key_use_count_out=chat.key_use_count_out;
            newChat.seq_in=chat.seq_in;
            newChat.seq_out=chat.seq_out;
            AndroidUtilities.runOnUIThread(() -> {
              MessagesController.getInstance(currentAccount).putEncryptedChat(newChat,false);
              MessagesStorage.getInstance(currentAccount).updateEncryptedChat(newChat);
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.encryptedChatUpdated,newChat);
            }
);
            declineSecretChat(chat.id);
            return null;
          }
          TL_decryptedMessageHolder holder=new TL_decryptedMessageHolder();
          holder.layer=layer;
          holder.file=message.file;
          holder.date=message.date;
          holder.new_key_used=new_key_used;
          holder.decryptedWithVersion=decryptedWithVersion;
          arr.add(holder);
          return null;
        }
        if (decryptedWithVersion == 2) {
          chat.mtproto_seq=Math.min(chat.mtproto_seq,chat.seq_in);
        }
        applyPeerLayer(chat,layer.layer);
        chat.seq_in=layer.out_seq_no;
        chat.in_seq_no=layer.in_seq_no;
        MessagesStorage.getInstance(currentAccount).updateEncryptedChatSeq(chat,true);
        object=layer.message;
      }
 else       if (!(object instanceof TLRPC.TL_decryptedMessageService && ((TLRPC.TL_decryptedMessageService)object).action instanceof TLRPC.TL_decryptedMessageActionNotifyLayer)) {
        return null;
      }
      ArrayList<TLRPC.Message> messages=new ArrayList<>();
      TLRPC.Message decryptedMessage=processDecryptedObject(chat,message.file,message.date,object,new_key_used);
      if (decryptedMessage != null) {
        messages.add(decryptedMessage);
      }
      checkSecretHoles(chat,messages);
      return messages;
    }
 else {
      is.reuse();
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e(String.format("fingerprint mismatch %x",fingerprint));
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}
