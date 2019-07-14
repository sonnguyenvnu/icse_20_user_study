private CharSequence formatArchivedDialogNames(){
  ArrayList<TLRPC.Dialog> dialogs=MessagesController.getInstance(currentAccount).getDialogs(currentDialogFolderId);
  currentDialogFolderDialogsCount=dialogs.size();
  SpannableStringBuilder builder=new SpannableStringBuilder();
  for (int a=0, N=dialogs.size(); a < N; a++) {
    TLRPC.Dialog dialog=dialogs.get(a);
    TLRPC.User currentUser=null;
    TLRPC.Chat currentChat=null;
    if (DialogObject.isSecretDialogId(dialog.id)) {
      TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat((int)(dialog.id >> 32));
      if (encryptedChat != null) {
        currentUser=MessagesController.getInstance(currentAccount).getUser(encryptedChat.user_id);
      }
    }
 else {
      int lowerId=(int)dialog.id;
      if (lowerId > 0) {
        currentUser=MessagesController.getInstance(currentAccount).getUser(lowerId);
      }
 else {
        currentChat=MessagesController.getInstance(currentAccount).getChat(-lowerId);
      }
    }
    String title;
    if (currentChat != null) {
      title=currentChat.title.replace('\n',' ');
    }
 else     if (currentUser != null) {
      if (UserObject.isDeleted(currentUser)) {
        title=LocaleController.getString("HiddenName",R.string.HiddenName);
      }
 else {
        title=ContactsController.formatName(currentUser.first_name,currentUser.last_name).replace('\n',' ');
      }
    }
 else {
      continue;
    }
    if (builder.length() > 0) {
      builder.append(", ");
    }
    int boldStart=builder.length();
    int boldEnd=boldStart + title.length();
    builder.append(title);
    if (dialog.unread_count > 0) {
      builder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf"),0,Theme.getColor(Theme.key_chats_nameArchived)),boldStart,boldEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    if (builder.length() > 150) {
      break;
    }
  }
  return Emoji.replaceEmoji(builder,Theme.dialogs_messagePaint.getFontMetricsInt(),AndroidUtilities.dp(17),false);
}
