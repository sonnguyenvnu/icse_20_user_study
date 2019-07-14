private boolean processOnClickOrPress(final int position){
  if (position == usernameRow) {
    final String username;
    if (user_id != 0) {
      final TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
      if (user == null || user.username == null) {
        return false;
      }
      username=user.username;
    }
 else     if (chat_id != 0) {
      final TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(chat_id);
      if (chat == null || chat.username == null) {
        return false;
      }
      username=chat.username;
    }
 else {
      return false;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setItems(new CharSequence[]{LocaleController.getString("Copy",R.string.Copy)},(dialogInterface,i) -> {
      if (i == 0) {
        try {
          android.content.ClipboardManager clipboard=(android.content.ClipboardManager)ApplicationLoader.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE);
          android.content.ClipData clip=android.content.ClipData.newPlainText("label","@" + username);
          clipboard.setPrimaryClip(clip);
          Toast.makeText(getParentActivity(),LocaleController.getString("TextCopied",R.string.TextCopied),Toast.LENGTH_SHORT).show();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
);
    showDialog(builder.create());
    return true;
  }
 else   if (position == phoneRow) {
    final TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
    if (user == null || user.phone == null || user.phone.length() == 0 || getParentActivity() == null) {
      return false;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    ArrayList<CharSequence> items=new ArrayList<>();
    final ArrayList<Integer> actions=new ArrayList<>();
    if (userInfo != null && userInfo.phone_calls_available) {
      items.add(LocaleController.getString("CallViaTelegram",R.string.CallViaTelegram));
      actions.add(2);
    }
    items.add(LocaleController.getString("Call",R.string.Call));
    actions.add(0);
    items.add(LocaleController.getString("Copy",R.string.Copy));
    actions.add(1);
    builder.setItems(items.toArray(new CharSequence[0]),(dialogInterface,i) -> {
      i=actions.get(i);
      if (i == 0) {
        try {
          Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:+" + user.phone));
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          getParentActivity().startActivityForResult(intent,500);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
 else       if (i == 1) {
        try {
          android.content.ClipboardManager clipboard=(android.content.ClipboardManager)ApplicationLoader.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE);
          android.content.ClipData clip=android.content.ClipData.newPlainText("label","+" + user.phone);
          clipboard.setPrimaryClip(clip);
          Toast.makeText(getParentActivity(),LocaleController.getString("PhoneCopied",R.string.PhoneCopied),Toast.LENGTH_SHORT).show();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
 else       if (i == 2) {
        VoIPHelper.startCall(user,getParentActivity(),userInfo);
      }
    }
);
    showDialog(builder.create());
    return true;
  }
 else   if (position == channelInfoRow || position == userInfoRow) {
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setItems(new CharSequence[]{LocaleController.getString("Copy",R.string.Copy)},(dialogInterface,i) -> {
      try {
        String about;
        if (position == channelInfoRow) {
          about=chatInfo != null ? chatInfo.about : null;
        }
 else {
          about=userInfo != null ? userInfo.about : null;
        }
        if (TextUtils.isEmpty(about)) {
          return;
        }
        AndroidUtilities.addToClipboard(about);
        Toast.makeText(getParentActivity(),LocaleController.getString("TextCopied",R.string.TextCopied),Toast.LENGTH_SHORT).show();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
    showDialog(builder.create());
    return true;
  }
  return false;
}
