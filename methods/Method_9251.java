private boolean onDonePressed(boolean alert){
  if (selectedContacts.size() == 0) {
    return false;
  }
  if (alert && addToGroup) {
    if (getParentActivity() == null) {
      return false;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    if (selectedContacts.size() == 1) {
      builder.setTitle(LocaleController.getString("AddOneMemberAlertTitle",R.string.AddOneMemberAlertTitle));
    }
 else {
      builder.setTitle(LocaleController.formatString("AddMembersAlertTitle",R.string.AddMembersAlertTitle,LocaleController.formatPluralString("Members",selectedContacts.size())));
    }
    StringBuilder stringBuilder=new StringBuilder();
    for (int a=0; a < selectedContacts.size(); a++) {
      int uid=selectedContacts.keyAt(a);
      TLRPC.User user=getMessagesController().getUser(uid);
      if (user == null) {
        continue;
      }
      if (stringBuilder.length() > 0) {
        stringBuilder.append(", ");
      }
      stringBuilder.append("**").append(ContactsController.formatName(user.first_name,user.last_name)).append("**");
    }
    TLRPC.Chat chat=getMessagesController().getChat(chatId != 0 ? chatId : channelId);
    if (selectedContacts.size() > 5) {
      SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatString("AddMembersAlertNamesText",R.string.AddMembersAlertNamesText,LocaleController.formatPluralString("Members",selectedContacts.size()),chat.title)));
      String countString=String.format("%d",selectedContacts.size());
      int index=TextUtils.indexOf(spannableStringBuilder,countString);
      if (index >= 0) {
        spannableStringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")),index,index + countString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      builder.setMessage(spannableStringBuilder);
    }
 else {
      builder.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("AddMembersAlertNamesText",R.string.AddMembersAlertNamesText,stringBuilder,chat.title)));
    }
    CheckBoxCell[] cells=new CheckBoxCell[1];
    if (!ChatObject.isChannel(chat)) {
      LinearLayout linearLayout=new LinearLayout(getParentActivity());
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      cells[0]=new CheckBoxCell(getParentActivity(),1);
      cells[0].setBackgroundDrawable(Theme.getSelectorDrawable(false));
      cells[0].setMultiline(true);
      if (selectedContacts.size() == 1) {
        TLRPC.User user=getMessagesController().getUser(selectedContacts.keyAt(0));
        cells[0].setText(AndroidUtilities.replaceTags(LocaleController.formatString("AddOneMemberForwardMessages",R.string.AddOneMemberForwardMessages,UserObject.getFirstName(user))),"",true,false);
      }
 else {
        cells[0].setText(LocaleController.getString("AddMembersForwardMessages",R.string.AddMembersForwardMessages),"",true,false);
      }
      cells[0].setPadding(LocaleController.isRTL ? AndroidUtilities.dp(16) : AndroidUtilities.dp(8),0,LocaleController.isRTL ? AndroidUtilities.dp(8) : AndroidUtilities.dp(16),0);
      linearLayout.addView(cells[0],LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
      cells[0].setOnClickListener(v -> cells[0].setChecked(!cells[0].isChecked(),true));
      builder.setCustomViewOffset(12);
      builder.setView(linearLayout);
    }
    builder.setPositiveButton(LocaleController.getString("Add",R.string.Add),(dialogInterface,i) -> onAddToGroupDone(cells[0] != null && cells[0].isChecked() ? 100 : 0));
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    showDialog(builder.create());
  }
 else {
    if (chatType == ChatObject.CHAT_TYPE_CHANNEL) {
      ArrayList<TLRPC.InputUser> result=new ArrayList<>();
      for (int a=0; a < selectedContacts.size(); a++) {
        TLRPC.InputUser user=MessagesController.getInstance(currentAccount).getInputUser(MessagesController.getInstance(currentAccount).getUser(selectedContacts.keyAt(a)));
        if (user != null) {
          result.add(user);
        }
      }
      MessagesController.getInstance(currentAccount).addUsersToChannel(chatId,result,null);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
      Bundle args2=new Bundle();
      args2.putInt("chat_id",chatId);
      presentFragment(new ChatActivity(args2),true);
    }
 else {
      if (!doneButtonVisible || selectedContacts.size() == 0) {
        return false;
      }
      if (addToGroup) {
        onAddToGroupDone(0);
      }
 else {
        ArrayList<Integer> result=new ArrayList<>();
        for (int a=0; a < selectedContacts.size(); a++) {
          result.add(selectedContacts.keyAt(a));
        }
        if (isAlwaysShare || isNeverShare) {
          if (delegate != null) {
            delegate.didSelectUsers(result);
          }
          finishFragment();
        }
 else {
          Bundle args=new Bundle();
          args.putIntegerArrayList("result",result);
          args.putInt("chatType",chatType);
          presentFragment(new GroupCreateFinalActivity(args));
        }
      }
    }
  }
  return true;
}
