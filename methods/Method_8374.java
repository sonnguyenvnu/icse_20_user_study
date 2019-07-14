private void getChats(int max_id,final int count){
  if (loading) {
    return;
  }
  loading=true;
  if (emptyView != null && !firstLoaded) {
    emptyView.showProgress();
  }
  if (listViewAdapter != null) {
    listViewAdapter.notifyDataSetChanged();
  }
  TLRPC.TL_messages_getCommonChats req=new TLRPC.TL_messages_getCommonChats();
  req.user_id=MessagesController.getInstance(currentAccount).getInputUser(userId);
  if (req.user_id instanceof TLRPC.TL_inputUserEmpty) {
    return;
  }
  req.limit=count;
  req.max_id=max_id;
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      TLRPC.messages_Chats res=(TLRPC.messages_Chats)response;
      MessagesController.getInstance(currentAccount).putChats(res.chats,false);
      endReached=res.chats.isEmpty() || res.chats.size() != count;
      chats.addAll(res.chats);
    }
 else {
      endReached=true;
    }
    loading=false;
    firstLoaded=true;
    if (emptyView != null) {
      emptyView.showTextView();
    }
    if (listViewAdapter != null) {
      listViewAdapter.notifyDataSetChanged();
    }
  }
));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
