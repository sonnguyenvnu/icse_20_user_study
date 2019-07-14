public boolean processSwitchButton(TLRPC.TL_keyboardButtonSwitchInline button){
  if (inlineReturn == 0 || button.same_peer || parentLayout == null) {
    return false;
  }
  String query="@" + currentUser.username + " " + button.query;
  if (inlineReturn == dialog_id) {
    inlineReturn=0;
    chatActivityEnterView.setFieldText(query);
  }
 else {
    DataQuery.getInstance(currentAccount).saveDraft(inlineReturn,query,null,null,false);
    if (parentLayout.fragmentsStack.size() > 1) {
      BaseFragment prevFragment=parentLayout.fragmentsStack.get(parentLayout.fragmentsStack.size() - 2);
      if (prevFragment instanceof ChatActivity && ((ChatActivity)prevFragment).dialog_id == inlineReturn) {
        finishFragment();
      }
 else {
        Bundle bundle=new Bundle();
        int lower_part=(int)inlineReturn;
        int high_part=(int)(inlineReturn >> 32);
        if (lower_part != 0) {
          if (lower_part > 0) {
            bundle.putInt("user_id",lower_part);
          }
 else           if (lower_part < 0) {
            bundle.putInt("chat_id",-lower_part);
          }
        }
 else {
          bundle.putInt("enc_id",high_part);
        }
        presentFragment(new ChatActivity(bundle),true);
      }
    }
  }
  return true;
}
