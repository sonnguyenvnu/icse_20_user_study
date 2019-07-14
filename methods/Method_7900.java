private void getCalls(int max_id,final int count){
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
  TLRPC.TL_messages_search req=new TLRPC.TL_messages_search();
  req.limit=count;
  req.peer=new TLRPC.TL_inputPeerEmpty();
  req.filter=new TLRPC.TL_inputMessagesFilterPhoneCalls();
  req.q="";
  req.offset_id=max_id;
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      SparseArray<TLRPC.User> users=new SparseArray<>();
      TLRPC.messages_Messages msgs=(TLRPC.messages_Messages)response;
      endReached=msgs.messages.isEmpty();
      for (int a=0; a < msgs.users.size(); a++) {
        TLRPC.User user=msgs.users.get(a);
        users.put(user.id,user);
      }
      CallLogRow currentRow=calls.size() > 0 ? calls.get(calls.size() - 1) : null;
      for (int a=0; a < msgs.messages.size(); a++) {
        TLRPC.Message msg=msgs.messages.get(a);
        if (msg.action == null || msg.action instanceof TLRPC.TL_messageActionHistoryClear) {
          continue;
        }
        int callType=msg.from_id == UserConfig.getInstance(currentAccount).getClientUserId() ? TYPE_OUT : TYPE_IN;
        TLRPC.PhoneCallDiscardReason reason=msg.action.reason;
        if (callType == TYPE_IN && (reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed || reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy)) {
          callType=TYPE_MISSED;
        }
        int userID=msg.from_id == UserConfig.getInstance(currentAccount).getClientUserId() ? msg.to_id.user_id : msg.from_id;
        if (currentRow == null || currentRow.user.id != userID || currentRow.type != callType) {
          if (currentRow != null && !calls.contains(currentRow)) {
            calls.add(currentRow);
          }
          CallLogRow row=new CallLogRow();
          row.calls=new ArrayList<>();
          row.user=users.get(userID);
          row.type=callType;
          currentRow=row;
        }
        currentRow.calls.add(msg);
      }
      if (currentRow != null && currentRow.calls.size() > 0 && !calls.contains(currentRow)) {
        calls.add(currentRow);
      }
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
),ConnectionsManager.RequestFlagFailOnServerErrors);
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
