private void searchMessagesInChat(String query,final long dialog_id,final long mergeDialogId,final int guid,final int direction,final boolean internal,final TLRPC.User user){
  int max_id=0;
  long queryWithDialog=dialog_id;
  boolean firstQuery=!internal;
  if (reqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true);
    reqId=0;
  }
  if (mergeReqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(mergeReqId,true);
    mergeReqId=0;
  }
  if (query == null) {
    if (searchResultMessages.isEmpty()) {
      return;
    }
    if (direction == 1) {
      lastReturnedNum++;
      if (lastReturnedNum < searchResultMessages.size()) {
        MessageObject messageObject=searchResultMessages.get(lastReturnedNum);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatSearchResultsAvailable,guid,messageObject.getId(),getMask(),messageObject.getDialogId(),lastReturnedNum,messagesSearchCount[0] + messagesSearchCount[1]);
        return;
      }
 else {
        if (messagesSearchEndReached[0] && mergeDialogId == 0 && messagesSearchEndReached[1]) {
          lastReturnedNum--;
          return;
        }
        firstQuery=false;
        query=lastSearchQuery;
        MessageObject messageObject=searchResultMessages.get(searchResultMessages.size() - 1);
        if (messageObject.getDialogId() == dialog_id && !messagesSearchEndReached[0]) {
          max_id=messageObject.getId();
          queryWithDialog=dialog_id;
        }
 else {
          if (messageObject.getDialogId() == mergeDialogId) {
            max_id=messageObject.getId();
          }
          queryWithDialog=mergeDialogId;
          messagesSearchEndReached[1]=false;
        }
      }
    }
 else     if (direction == 2) {
      lastReturnedNum--;
      if (lastReturnedNum < 0) {
        lastReturnedNum=0;
        return;
      }
      if (lastReturnedNum >= searchResultMessages.size()) {
        lastReturnedNum=searchResultMessages.size() - 1;
      }
      MessageObject messageObject=searchResultMessages.get(lastReturnedNum);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatSearchResultsAvailable,guid,messageObject.getId(),getMask(),messageObject.getDialogId(),lastReturnedNum,messagesSearchCount[0] + messagesSearchCount[1]);
      return;
    }
 else {
      return;
    }
  }
 else   if (firstQuery) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatSearchResultsLoading,guid);
    messagesSearchEndReached[0]=messagesSearchEndReached[1]=false;
    messagesSearchCount[0]=messagesSearchCount[1]=0;
    searchResultMessages.clear();
    searchResultMessagesMap[0].clear();
    searchResultMessagesMap[1].clear();
  }
  if (messagesSearchEndReached[0] && !messagesSearchEndReached[1] && mergeDialogId != 0) {
    queryWithDialog=mergeDialogId;
  }
  if (queryWithDialog == dialog_id && firstQuery) {
    if (mergeDialogId != 0) {
      TLRPC.InputPeer inputPeer=MessagesController.getInstance(currentAccount).getInputPeer((int)mergeDialogId);
      if (inputPeer == null) {
        return;
      }
      final TLRPC.TL_messages_search req=new TLRPC.TL_messages_search();
      req.peer=inputPeer;
      lastMergeDialogId=mergeDialogId;
      req.limit=1;
      req.q=query != null ? query : "";
      if (user != null) {
        req.from_id=MessagesController.getInstance(currentAccount).getInputUser(user);
        req.flags|=1;
      }
      req.filter=new TLRPC.TL_inputMessagesFilterEmpty();
      mergeReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (lastMergeDialogId == mergeDialogId) {
          mergeReqId=0;
          if (response != null) {
            TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
            messagesSearchEndReached[1]=res.messages.isEmpty();
            messagesSearchCount[1]=res instanceof TLRPC.TL_messages_messagesSlice ? res.count : res.messages.size();
            searchMessagesInChat(req.q,dialog_id,mergeDialogId,guid,direction,true,user);
          }
        }
      }
),ConnectionsManager.RequestFlagFailOnServerErrors);
      return;
    }
 else {
      lastMergeDialogId=0;
      messagesSearchEndReached[1]=true;
      messagesSearchCount[1]=0;
    }
  }
  final TLRPC.TL_messages_search req=new TLRPC.TL_messages_search();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)queryWithDialog);
  if (req.peer == null) {
    return;
  }
  req.limit=21;
  req.q=query != null ? query : "";
  req.offset_id=max_id;
  if (user != null) {
    req.from_id=MessagesController.getInstance(currentAccount).getInputUser(user);
    req.flags|=1;
  }
  req.filter=new TLRPC.TL_inputMessagesFilterEmpty();
  final int currentReqId=++lastReqId;
  lastSearchQuery=query;
  final long queryWithDialogFinal=queryWithDialog;
  reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (currentReqId == lastReqId) {
      reqId=0;
      if (response != null) {
        TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
        for (int a=0; a < res.messages.size(); a++) {
          TLRPC.Message message=res.messages.get(a);
          if (message instanceof TLRPC.TL_messageEmpty || message.action instanceof TLRPC.TL_messageActionHistoryClear) {
            res.messages.remove(a);
            a--;
          }
        }
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(res.users,res.chats,true,true);
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        MessagesController.getInstance(currentAccount).putChats(res.chats,false);
        if (req.offset_id == 0 && queryWithDialogFinal == dialog_id) {
          lastReturnedNum=0;
          searchResultMessages.clear();
          searchResultMessagesMap[0].clear();
          searchResultMessagesMap[1].clear();
          messagesSearchCount[0]=0;
        }
        boolean added=false;
        for (int a=0; a < Math.min(res.messages.size(),20); a++) {
          TLRPC.Message message=res.messages.get(a);
          added=true;
          MessageObject messageObject=new MessageObject(currentAccount,message,false);
          searchResultMessages.add(messageObject);
          searchResultMessagesMap[queryWithDialogFinal == dialog_id ? 0 : 1].put(messageObject.getId(),messageObject);
        }
        messagesSearchEndReached[queryWithDialogFinal == dialog_id ? 0 : 1]=res.messages.size() != 21;
        messagesSearchCount[queryWithDialogFinal == dialog_id ? 0 : 1]=res instanceof TLRPC.TL_messages_messagesSlice || res instanceof TLRPC.TL_messages_channelMessages ? res.count : res.messages.size();
        if (searchResultMessages.isEmpty()) {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatSearchResultsAvailable,guid,0,getMask(),(long)0,0,0);
        }
 else {
          if (added) {
            if (lastReturnedNum >= searchResultMessages.size()) {
              lastReturnedNum=searchResultMessages.size() - 1;
            }
            MessageObject messageObject=searchResultMessages.get(lastReturnedNum);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatSearchResultsAvailable,guid,messageObject.getId(),getMask(),messageObject.getDialogId(),lastReturnedNum,messagesSearchCount[0] + messagesSearchCount[1]);
          }
        }
        if (queryWithDialogFinal == dialog_id && messagesSearchEndReached[0] && mergeDialogId != 0 && !messagesSearchEndReached[1]) {
          searchMessagesInChat(lastSearchQuery,dialog_id,mergeDialogId,guid,0,true,user);
        }
      }
    }
  }
),ConnectionsManager.RequestFlagFailOnServerErrors);
}
