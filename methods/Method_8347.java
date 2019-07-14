private void showLinkAlert(TLRPC.Chat chat,boolean query){
  TLRPC.ChatFull chatFull=getMessagesController().getChatFull(chat.id);
  if (chatFull == null) {
    if (query) {
      getMessagesController().loadFullChat(chat.id,0,true);
      waitingForFullChat=chat;
      waitingForFullChatProgressAlert=new AlertDialog(getParentActivity(),3);
      AndroidUtilities.runOnUIThread(() -> {
        if (waitingForFullChatProgressAlert == null) {
          return;
        }
        waitingForFullChatProgressAlert.setOnCancelListener(dialog -> waitingForFullChat=null);
        showDialog(waitingForFullChatProgressAlert);
      }
,500);
    }
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  TextView messageTextView=new TextView(getParentActivity());
  messageTextView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  messageTextView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP);
  String message;
  if (TextUtils.isEmpty(chat.username)) {
    message=LocaleController.formatString("DiscussionLinkGroupPublicPrivateAlert",R.string.DiscussionLinkGroupPublicPrivateAlert,chat.title,currentChat.title);
  }
 else {
    if (TextUtils.isEmpty(currentChat.username)) {
      message=LocaleController.formatString("DiscussionLinkGroupPrivateAlert",R.string.DiscussionLinkGroupPrivateAlert,chat.title,currentChat.title);
    }
 else {
      message=LocaleController.formatString("DiscussionLinkGroupPublicAlert",R.string.DiscussionLinkGroupPublicAlert,chat.title,currentChat.title);
    }
  }
  if (chatFull.hidden_prehistory) {
    message+="\n\n" + LocaleController.getString("DiscussionLinkGroupAlertHistory",R.string.DiscussionLinkGroupAlertHistory);
  }
  messageTextView.setText(AndroidUtilities.replaceTags(message));
  FrameLayout frameLayout2=new FrameLayout(getParentActivity());
  builder.setView(frameLayout2);
  AvatarDrawable avatarDrawable=new AvatarDrawable();
  avatarDrawable.setTextSize(AndroidUtilities.dp(12));
  BackupImageView imageView=new BackupImageView(getParentActivity());
  imageView.setRoundRadius(AndroidUtilities.dp(20));
  frameLayout2.addView(imageView,LayoutHelper.createFrame(40,40,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,22,5,22,0));
  TextView textView=new TextView(getParentActivity());
  textView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
  textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
  textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  textView.setLines(1);
  textView.setMaxLines(1);
  textView.setSingleLine(true);
  textView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
  textView.setEllipsize(TextUtils.TruncateAt.END);
  textView.setText(chat.title);
  frameLayout2.addView(textView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,(LocaleController.isRTL ? 21 : 76),11,(LocaleController.isRTL ? 76 : 21),0));
  frameLayout2.addView(messageTextView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,(LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP,24,57,24,9));
  avatarDrawable.setInfo(chat);
  imageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
  builder.setPositiveButton(LocaleController.getString("DiscussionLinkGroup",R.string.DiscussionLinkGroup),(dialogInterface,i) -> {
    if (chatFull.hidden_prehistory) {
      MessagesController.getInstance(currentAccount).toogleChannelInvitesHistory(chat.id,false);
    }
    linkChat(chat,null);
  }
);
  builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  showDialog(builder.create());
}
