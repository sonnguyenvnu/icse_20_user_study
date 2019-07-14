public void reloadMentionsCountForChannels(final ArrayList<Integer> arrayList){
  AndroidUtilities.runOnUIThread(() -> {
    for (int a=0; a < arrayList.size(); a++) {
      final long dialog_id=-arrayList.get(a);
      TLRPC.TL_messages_getUnreadMentions req=new TLRPC.TL_messages_getUnreadMentions();
      req.peer=getInputPeer((int)dialog_id);
      req.limit=1;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
        if (res != null) {
          int newCount;
          if (res.count != 0) {
            newCount=res.count;
          }
 else {
            newCount=res.messages.size();
          }
          MessagesStorage.getInstance(currentAccount).resetMentionsCount(dialog_id,newCount);
        }
      }
));
    }
  }
);
}
