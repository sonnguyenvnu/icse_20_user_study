@Override @SuppressWarnings("unchecked") public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.didReceiveNewMessages && firstLoaded) {
    ArrayList<MessageObject> arr=(ArrayList<MessageObject>)args[1];
    for (    MessageObject msg : arr) {
      if (msg.messageOwner.action instanceof TLRPC.TL_messageActionPhoneCall) {
        int userID=msg.messageOwner.from_id == UserConfig.getInstance(currentAccount).getClientUserId() ? msg.messageOwner.to_id.user_id : msg.messageOwner.from_id;
        int callType=msg.messageOwner.from_id == UserConfig.getInstance(currentAccount).getClientUserId() ? TYPE_OUT : TYPE_IN;
        TLRPC.PhoneCallDiscardReason reason=msg.messageOwner.action.reason;
        if (callType == TYPE_IN && (reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed || reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy)) {
          callType=TYPE_MISSED;
        }
        if (calls.size() > 0) {
          CallLogRow topRow=calls.get(0);
          if (topRow.user.id == userID && topRow.type == callType) {
            topRow.calls.add(0,msg.messageOwner);
            listViewAdapter.notifyItemChanged(0);
            continue;
          }
        }
        CallLogRow row=new CallLogRow();
        row.calls=new ArrayList<>();
        row.calls.add(msg.messageOwner);
        row.user=MessagesController.getInstance(currentAccount).getUser(userID);
        row.type=callType;
        calls.add(0,row);
        listViewAdapter.notifyItemInserted(0);
      }
    }
  }
 else   if (id == NotificationCenter.messagesDeleted && firstLoaded) {
    boolean didChange=false;
    ArrayList<Integer> ids=(ArrayList<Integer>)args[0];
    Iterator<CallLogRow> itrtr=calls.iterator();
    while (itrtr.hasNext()) {
      CallLogRow row=itrtr.next();
      Iterator<TLRPC.Message> msgs=row.calls.iterator();
      while (msgs.hasNext()) {
        TLRPC.Message msg=msgs.next();
        if (ids.contains(msg.id)) {
          didChange=true;
          msgs.remove();
        }
      }
      if (row.calls.size() == 0)       itrtr.remove();
    }
    if (didChange && listViewAdapter != null)     listViewAdapter.notifyDataSetChanged();
  }
}
