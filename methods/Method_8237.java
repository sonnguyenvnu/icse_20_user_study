private void checkScrollForLoad(boolean scroll){
  if (chatLayoutManager == null || paused) {
    return;
  }
  int firstVisibleItem=chatLayoutManager.findFirstVisibleItemPosition();
  int visibleItemCount=firstVisibleItem == RecyclerView.NO_POSITION ? 0 : Math.abs(chatLayoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
  int totalItemCount=chatAdapter.getItemCount();
  int checkLoadCount;
  if (scroll) {
    checkLoadCount=25;
  }
 else {
    checkLoadCount=5;
  }
  if (totalItemCount - firstVisibleItem - visibleItemCount <= checkLoadCount && !loading) {
    if (!endReached[0]) {
      loading=true;
      waitingForLoad.add(lastLoadIndex);
      if (messagesByDays.size() != 0) {
        MessagesController.getInstance(currentAccount).loadMessages(dialog_id,50,maxMessageId[0],0,!cacheEndReached[0],minDate[0],classGuid,0,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
      }
 else {
        MessagesController.getInstance(currentAccount).loadMessages(dialog_id,50,0,0,!cacheEndReached[0],minDate[0],classGuid,0,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
      }
    }
 else     if (mergeDialogId != 0 && !endReached[1]) {
      loading=true;
      waitingForLoad.add(lastLoadIndex);
      MessagesController.getInstance(currentAccount).loadMessages(mergeDialogId,50,maxMessageId[1],0,!cacheEndReached[1],minDate[1],classGuid,0,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
    }
  }
  if (visibleItemCount > 0 && !loadingForward && firstVisibleItem <= 10) {
    if (mergeDialogId != 0 && !forwardEndReached[1]) {
      waitingForLoad.add(lastLoadIndex);
      MessagesController.getInstance(currentAccount).loadMessages(mergeDialogId,50,minMessageId[1],0,true,maxDate[1],classGuid,1,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
      loadingForward=true;
    }
 else     if (!forwardEndReached[0]) {
      waitingForLoad.add(lastLoadIndex);
      MessagesController.getInstance(currentAccount).loadMessages(dialog_id,50,minMessageId[0],0,true,maxDate[0],classGuid,1,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
      loadingForward=true;
    }
  }
}
