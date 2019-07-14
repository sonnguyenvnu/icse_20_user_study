public void updateChannelUserName(final int chat_id,final String userName){
  TLRPC.TL_channels_updateUsername req=new TLRPC.TL_channels_updateUsername();
  req.channel=getInputChannel(chat_id);
  req.username=userName;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response instanceof TLRPC.TL_boolTrue) {
      AndroidUtilities.runOnUIThread(() -> {
        TLRPC.Chat chat=getChat(chat_id);
        if (userName.length() != 0) {
          chat.flags|=TLRPC.CHAT_FLAG_IS_PUBLIC;
        }
 else {
          chat.flags&=~TLRPC.CHAT_FLAG_IS_PUBLIC;
        }
        chat.username=userName;
        ArrayList<TLRPC.Chat> arrayList=new ArrayList<>();
        arrayList.add(chat);
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(null,arrayList,true,true);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,UPDATE_MASK_CHAT);
      }
);
    }
  }
,ConnectionsManager.RequestFlagInvokeAfter);
}
