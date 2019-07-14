public void processInlineBotContextPM(TLRPC.TL_inlineBotSwitchPM object){
  if (object == null) {
    return;
  }
  TLRPC.User user=mentionsAdapter.getContextBotUser();
  if (user == null) {
    return;
  }
  chatActivityEnterView.setFieldText("");
  if (dialog_id == user.id) {
    inlineReturn=dialog_id;
    MessagesController.getInstance(currentAccount).sendBotStart(currentUser,object.start_param);
  }
 else {
    Bundle args=new Bundle();
    args.putInt("user_id",user.id);
    args.putString("inline_query",object.start_param);
    args.putLong("inline_return",dialog_id);
    if (!MessagesController.getInstance(currentAccount).checkCanOpenChat(args,ChatActivity.this)) {
      return;
    }
    presentFragment(new ChatActivity(args));
  }
}
