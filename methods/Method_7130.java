private TLRPC.Message createDeleteMessage(int mid,int seq_out,int seq_in,long random_id,TLRPC.EncryptedChat encryptedChat){
  TLRPC.TL_messageService newMsg=new TLRPC.TL_messageService();
  newMsg.action=new TLRPC.TL_messageEncryptedAction();
  newMsg.action.encryptedAction=new TLRPC.TL_decryptedMessageActionDeleteMessages();
  newMsg.action.encryptedAction.random_ids.add(random_id);
  newMsg.local_id=newMsg.id=mid;
  newMsg.from_id=UserConfig.getInstance(currentAccount).getClientUserId();
  newMsg.unread=true;
  newMsg.out=true;
  newMsg.flags=TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
  newMsg.dialog_id=((long)encryptedChat.id) << 32;
  newMsg.to_id=new TLRPC.TL_peerUser();
  newMsg.send_state=MessageObject.MESSAGE_SEND_STATE_SENDING;
  newMsg.seq_in=seq_in;
  newMsg.seq_out=seq_out;
  if (encryptedChat.participant_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
    newMsg.to_id.user_id=encryptedChat.admin_id;
  }
 else {
    newMsg.to_id.user_id=encryptedChat.participant_id;
  }
  newMsg.date=0;
  newMsg.random_id=random_id;
  return newMsg;
}
