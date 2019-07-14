private void didSelectResult(final long dialog_id,boolean useAlert,final boolean param){
  if (addToGroupAlertString == null && checkCanWrite) {
    if ((int)dialog_id < 0) {
      TLRPC.Chat chat=getMessagesController().getChat(-(int)dialog_id);
      if (ChatObject.isChannel(chat) && !chat.megagroup && (cantSendToChannels || !ChatObject.isCanWriteToChannel(-(int)dialog_id,currentAccount))) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setMessage(LocaleController.getString("ChannelCantSendMessage",R.string.ChannelCantSendMessage));
        builder.setNegativeButton(LocaleController.getString("OK",R.string.OK),null);
        showDialog(builder.create());
        return;
      }
    }
  }
  if (useAlert && (selectAlertString != null && selectAlertStringGroup != null || addToGroupAlertString != null)) {
    if (getParentActivity() == null) {
      return;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    int lower_part=(int)dialog_id;
    int high_id=(int)(dialog_id >> 32);
    if (lower_part != 0) {
      if (high_id == 1) {
        TLRPC.Chat chat=getMessagesController().getChat(lower_part);
        if (chat == null) {
          return;
        }
        builder.setMessage(LocaleController.formatStringSimple(selectAlertStringGroup,chat.title));
      }
 else {
        if (lower_part == getUserConfig().getClientUserId()) {
          builder.setMessage(LocaleController.formatStringSimple(selectAlertStringGroup,LocaleController.getString("SavedMessages",R.string.SavedMessages)));
        }
 else         if (lower_part > 0) {
          TLRPC.User user=getMessagesController().getUser(lower_part);
          if (user == null) {
            return;
          }
          builder.setMessage(LocaleController.formatStringSimple(selectAlertString,UserObject.getUserName(user)));
        }
 else         if (lower_part < 0) {
          TLRPC.Chat chat=getMessagesController().getChat(-lower_part);
          if (chat == null) {
            return;
          }
          if (addToGroupAlertString != null) {
            builder.setMessage(LocaleController.formatStringSimple(addToGroupAlertString,chat.title));
          }
 else {
            builder.setMessage(LocaleController.formatStringSimple(selectAlertStringGroup,chat.title));
          }
        }
      }
    }
 else {
      TLRPC.EncryptedChat chat=getMessagesController().getEncryptedChat(high_id);
      TLRPC.User user=getMessagesController().getUser(chat.user_id);
      if (user == null) {
        return;
      }
      builder.setMessage(LocaleController.formatStringSimple(selectAlertString,UserObject.getUserName(user)));
    }
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> didSelectResult(dialog_id,false,false));
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    showDialog(builder.create());
  }
 else {
    if (delegate != null) {
      ArrayList<Long> dids=new ArrayList<>();
      dids.add(dialog_id);
      delegate.didSelectDialogs(DialogsActivity.this,dids,null,param);
      delegate=null;
    }
 else {
      finishFragment();
    }
  }
}
