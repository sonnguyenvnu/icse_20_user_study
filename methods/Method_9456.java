private void searchBotUser(boolean gif){
  if (searchingUser) {
    return;
  }
  searchingUser=true;
  TLRPC.TL_contacts_resolveUsername req=new TLRPC.TL_contacts_resolveUsername();
  req.username=gif ? MessagesController.getInstance(currentAccount).gifSearchBot : MessagesController.getInstance(currentAccount).imageSearchBot;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      AndroidUtilities.runOnUIThread(() -> {
        TLRPC.TL_contacts_resolvedPeer res=(TLRPC.TL_contacts_resolvedPeer)response;
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        MessagesController.getInstance(currentAccount).putChats(res.chats,false);
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
        String str=lastSearchImageString;
        lastSearchImageString=null;
        searchImages(gif,str,"",false);
      }
);
    }
  }
);
}
