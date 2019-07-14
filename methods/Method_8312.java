@Override public void didSelectDialogs(DialogsActivity fragment,ArrayList<Long> dids,CharSequence message,boolean param){
  if (forwardingMessage == null && selectedMessagesIds[0].size() == 0 && selectedMessagesIds[1].size() == 0) {
    return;
  }
  ArrayList<MessageObject> fmessages=new ArrayList<>();
  if (forwardingMessage != null) {
    if (forwardingMessageGroup != null) {
      fmessages.addAll(forwardingMessageGroup.messages);
    }
 else {
      fmessages.add(forwardingMessage);
    }
    forwardingMessage=null;
    forwardingMessageGroup=null;
  }
 else {
    for (int a=1; a >= 0; a--) {
      ArrayList<Integer> ids=new ArrayList<>();
      for (int b=0; b < selectedMessagesIds[a].size(); b++) {
        ids.add(selectedMessagesIds[a].keyAt(b));
      }
      Collections.sort(ids);
      for (int b=0; b < ids.size(); b++) {
        Integer id=ids.get(b);
        MessageObject messageObject=selectedMessagesIds[a].get(id);
        if (messageObject != null) {
          fmessages.add(messageObject);
        }
      }
      selectedMessagesCanCopyIds[a].clear();
      selectedMessagesCanStarIds[a].clear();
      selectedMessagesIds[a].clear();
    }
    hideActionMode();
    updatePinnedMessageView(true);
  }
  if (dids.size() > 1 || dids.get(0) == UserConfig.getInstance(currentAccount).getClientUserId() || message != null) {
    for (int a=0; a < dids.size(); a++) {
      long did=dids.get(a);
      if (message != null) {
        SendMessagesHelper.getInstance(currentAccount).sendMessage(message.toString(),did,null,null,true,null,null,null);
      }
      SendMessagesHelper.getInstance(currentAccount).sendMessage(fmessages,did);
    }
    fragment.finishFragment();
  }
 else {
    long did=dids.get(0);
    if (did != dialog_id) {
      int lower_part=(int)did;
      int high_part=(int)(did >> 32);
      Bundle args=new Bundle();
      args.putBoolean("scrollToTopOnResume",scrollToTopOnResume);
      if (lower_part != 0) {
        if (lower_part > 0) {
          args.putInt("user_id",lower_part);
        }
 else         if (lower_part < 0) {
          args.putInt("chat_id",-lower_part);
        }
      }
 else {
        args.putInt("enc_id",high_part);
      }
      if (lower_part != 0) {
        if (!MessagesController.getInstance(currentAccount).checkCanOpenChat(args,fragment)) {
          return;
        }
      }
      ChatActivity chatActivity=new ChatActivity(args);
      if (presentFragment(chatActivity,true)) {
        chatActivity.showFieldPanelForForward(true,fmessages);
        if (!AndroidUtilities.isTablet()) {
          removeSelfFromStack();
        }
      }
 else {
        fragment.finishFragment();
      }
    }
 else {
      fragment.finishFragment();
      moveScrollToLastMessage();
      showFieldPanelForForward(true,fmessages);
      if (AndroidUtilities.isTablet()) {
        hideActionMode();
        updatePinnedMessageView(true);
      }
      updateVisibleRows();
    }
  }
}
