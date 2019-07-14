private void joinChannel(final BlockChannelCell cell,final TLRPC.Chat channel){
  final TLRPC.TL_channels_joinChannel req=new TLRPC.TL_channels_joinChannel();
  req.channel=MessagesController.getInputChannel(channel);
  final int currentAccount=UserConfig.selectedAccount;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error != null) {
      AndroidUtilities.runOnUIThread(() -> {
        cell.setState(0,false);
        AlertsCreator.processError(currentAccount,error,parentFragment,req,true);
      }
);
      return;
    }
    boolean hasJoinMessage=false;
    TLRPC.Updates updates=(TLRPC.Updates)response;
    for (int a=0; a < updates.updates.size(); a++) {
      TLRPC.Update update=updates.updates.get(a);
      if (update instanceof TLRPC.TL_updateNewChannelMessage) {
        if (((TLRPC.TL_updateNewChannelMessage)update).message.action instanceof TLRPC.TL_messageActionChatAddUser) {
          hasJoinMessage=true;
          break;
        }
      }
    }
    MessagesController.getInstance(currentAccount).processUpdates(updates,false);
    if (!hasJoinMessage) {
      MessagesController.getInstance(currentAccount).generateJoinMessage(channel.id,true);
    }
    AndroidUtilities.runOnUIThread(() -> cell.setState(2,false));
    AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).loadFullChat(channel.id,0,true),1000);
    MessagesStorage.getInstance(currentAccount).updateDialogsWithDeletedMessages(new ArrayList<>(),null,true,channel.id);
  }
);
}
