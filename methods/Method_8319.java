@Override public void didSelectLocation(TLRPC.MessageMedia location,int live){
  SendMessagesHelper.getInstance(currentAccount).sendMessage(location,dialog_id,replyingMessageObject,null,null);
  moveScrollToLastMessage();
  if (live == 1) {
    hideFieldPanel(false);
    DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
  }
  if (paused) {
    scrollToTopOnResume=true;
  }
}
