private void loadChannel(final BlockChannelCell cell,WebpageAdapter adapter,TLRPC.Chat channel){
  if (loadingChannel || TextUtils.isEmpty(channel.username)) {
    return;
  }
  loadingChannel=true;
  TLRPC.TL_contacts_resolveUsername req=new TLRPC.TL_contacts_resolveUsername();
  req.username=channel.username;
  final int currentAccount=UserConfig.selectedAccount;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    loadingChannel=false;
    if (parentFragment == null || adapter.blocks.isEmpty()) {
      return;
    }
    if (error == null) {
      TLRPC.TL_contacts_resolvedPeer res=(TLRPC.TL_contacts_resolvedPeer)response;
      if (!res.chats.isEmpty()) {
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        MessagesController.getInstance(currentAccount).putChats(res.chats,false);
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,false,true);
        loadedChannel=res.chats.get(0);
        if (loadedChannel.left && !loadedChannel.kicked) {
          cell.setState(0,false);
        }
 else {
          cell.setState(4,false);
        }
      }
 else {
        cell.setState(4,false);
      }
    }
 else {
      cell.setState(4,false);
    }
  }
));
}
