public void setDefaultBannedRole(final int chatId,TLRPC.TL_chatBannedRights rights,final boolean isChannel,final BaseFragment parentFragment){
  if (rights == null) {
    return;
  }
  final TLRPC.TL_messages_editChatDefaultBannedRights req=new TLRPC.TL_messages_editChatDefaultBannedRights();
  req.peer=getInputPeer(-chatId);
  req.banned_rights=rights;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      processUpdates((TLRPC.Updates)response,false);
      AndroidUtilities.runOnUIThread(() -> loadFullChat(chatId,0,true),1000);
    }
 else {
      AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(currentAccount,error,parentFragment,req,isChannel));
    }
  }
);
}
