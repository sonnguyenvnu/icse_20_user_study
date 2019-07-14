public void setLastVisibleMessageIds(int account,long enterTime,long leaveTime,TLRPC.User user,TLRPC.EncryptedChat encryptedChat,ArrayList<Long> visibleMessages,int visibleMessage){
  lastChatEnterTime=enterTime;
  lastChatLeaveTime=leaveTime;
  lastChatAccount=account;
  lastSecretChat=encryptedChat;
  lastUser=user;
  lastMessageId=visibleMessage;
  lastChatVisibleMessages=visibleMessages;
}
