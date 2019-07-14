@Override public void didSelectDialogs(DialogsActivity fragment,ArrayList<Long> dids,CharSequence message,boolean param){
  long did=dids.get(0);
  Bundle args=new Bundle();
  args.putBoolean("scrollToTopOnResume",true);
  int lower_part=(int)did;
  if (lower_part != 0) {
    if (lower_part > 0) {
      args.putInt("user_id",lower_part);
    }
 else     if (lower_part < 0) {
      args.putInt("chat_id",-lower_part);
    }
  }
 else {
    args.putInt("enc_id",(int)(did >> 32));
  }
  if (!MessagesController.getInstance(currentAccount).checkCanOpenChat(args,fragment)) {
    return;
  }
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.closeChats);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
  presentFragment(new ChatActivity(args),true);
  removeSelfFromStack();
  TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
  SendMessagesHelper.getInstance(currentAccount).sendMessage(user,did,null,null,null);
}
