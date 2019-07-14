private void loadMessages(boolean reset){
  if (loading) {
    return;
  }
  if (reset) {
    minEventId=Long.MAX_VALUE;
    if (progressView != null) {
      progressView.setVisibility(View.VISIBLE);
      emptyViewContainer.setVisibility(View.INVISIBLE);
      chatListView.setEmptyView(null);
    }
    messagesDict.clear();
    messages.clear();
    messagesByDays.clear();
  }
  loading=true;
  TLRPC.TL_channels_getAdminLog req=new TLRPC.TL_channels_getAdminLog();
  req.channel=MessagesController.getInputChannel(currentChat);
  req.q=searchQuery;
  req.limit=50;
  if (!reset && !messages.isEmpty()) {
    req.max_id=minEventId;
  }
 else {
    req.max_id=0;
  }
  req.min_id=0;
  if (currentFilter != null) {
    req.flags|=1;
    req.events_filter=currentFilter;
  }
  if (selectedAdmins != null) {
    req.flags|=2;
    for (int a=0; a < selectedAdmins.size(); a++) {
      req.admins.add(MessagesController.getInstance(currentAccount).getInputUser(selectedAdmins.valueAt(a)));
    }
  }
  updateEmptyPlaceholder();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      final TLRPC.TL_channels_adminLogResults res=(TLRPC.TL_channels_adminLogResults)response;
      AndroidUtilities.runOnUIThread(() -> {
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        MessagesController.getInstance(currentAccount).putChats(res.chats,false);
        boolean added=false;
        int oldRowsCount=messages.size();
        for (int a=0; a < res.events.size(); a++) {
          TLRPC.TL_channelAdminLogEvent event=res.events.get(a);
          if (messagesDict.indexOfKey(event.id) >= 0) {
            continue;
          }
          minEventId=Math.min(minEventId,event.id);
          added=true;
          MessageObject messageObject=new MessageObject(currentAccount,event,messages,messagesByDays,currentChat,mid);
          if (messageObject.contentType < 0) {
            continue;
          }
          messagesDict.put(event.id,messageObject);
        }
        int newRowsCount=messages.size() - oldRowsCount;
        loading=false;
        if (!added) {
          endReached=true;
        }
        progressView.setVisibility(View.INVISIBLE);
        chatListView.setEmptyView(emptyViewContainer);
        if (newRowsCount != 0) {
          boolean end=false;
          if (endReached) {
            end=true;
            chatAdapter.notifyItemRangeChanged(0,2);
          }
          int firstVisPos=chatLayoutManager.findLastVisibleItemPosition();
          View firstVisView=chatLayoutManager.findViewByPosition(firstVisPos);
          int top=((firstVisView == null) ? 0 : firstVisView.getTop()) - chatListView.getPaddingTop();
          if (newRowsCount - (end ? 1 : 0) > 0) {
            int insertStart=1 + (end ? 0 : 1);
            chatAdapter.notifyItemChanged(insertStart);
            chatAdapter.notifyItemRangeInserted(insertStart,newRowsCount - (end ? 1 : 0));
          }
          if (firstVisPos != -1) {
            chatLayoutManager.scrollToPositionWithOffset(firstVisPos + newRowsCount - (end ? 1 : 0),top);
          }
        }
 else         if (endReached) {
          chatAdapter.notifyItemRemoved(0);
        }
      }
);
    }
  }
);
  if (reset && chatAdapter != null) {
    chatAdapter.notifyDataSetChanged();
  }
}
