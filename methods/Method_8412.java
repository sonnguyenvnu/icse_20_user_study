private void onSubItemClick(int id){
  final MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
  if (messageObject == null || parentActivity == null) {
    return;
  }
  if (id == 1) {
    if (UserConfig.selectedAccount != currentAccount) {
      parentActivity.switchToAccount(currentAccount,true);
    }
    Bundle args=new Bundle();
    args.putBoolean("onlySelect",true);
    args.putInt("dialogsType",3);
    DialogsActivity fragment=new DialogsActivity(args);
    final ArrayList<MessageObject> fmessages=new ArrayList<>();
    fmessages.add(messageObject);
    fragment.setDelegate((fragment1,dids,message,param) -> {
      if (dids.size() > 1 || dids.get(0) == UserConfig.getInstance(currentAccount).getClientUserId() || message != null) {
        for (int a=0; a < dids.size(); a++) {
          long did=dids.get(a);
          if (message != null) {
            SendMessagesHelper.getInstance(currentAccount).sendMessage(message.toString(),did,null,null,true,null,null,null);
          }
          SendMessagesHelper.getInstance(currentAccount).sendMessage(fmessages,did);
        }
        fragment1.finishFragment();
      }
 else {
        long did=dids.get(0);
        int lower_part=(int)did;
        int high_part=(int)(did >> 32);
        Bundle args1=new Bundle();
        args1.putBoolean("scrollToTopOnResume",true);
        if (lower_part != 0) {
          if (lower_part > 0) {
            args1.putInt("user_id",lower_part);
          }
 else           if (lower_part < 0) {
            args1.putInt("chat_id",-lower_part);
          }
        }
 else {
          args1.putInt("enc_id",high_part);
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
        ChatActivity chatActivity=new ChatActivity(args1);
        if (parentActivity.presentFragment(chatActivity,true,false)) {
          chatActivity.showFieldPanelForForward(true,fmessages);
        }
 else {
          fragment1.finishFragment();
        }
      }
    }
);
    parentActivity.presentFragment(fragment);
    dismiss();
  }
 else   if (id == 2) {
    try {
      File f=null;
      boolean isVideo=false;
      if (!TextUtils.isEmpty(messageObject.messageOwner.attachPath)) {
        f=new File(messageObject.messageOwner.attachPath);
        if (!f.exists()) {
          f=null;
        }
      }
      if (f == null) {
        f=FileLoader.getPathToMessage(messageObject.messageOwner);
      }
      if (f.exists()) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        if (messageObject != null) {
          intent.setType(messageObject.getMimeType());
        }
 else {
          intent.setType("audio/mp3");
        }
        if (Build.VERSION.SDK_INT >= 24) {
          try {
            intent.putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(ApplicationLoader.applicationContext,BuildConfig.APPLICATION_ID + ".provider",f));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
          }
 catch (          Exception ignore) {
            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
          }
        }
 else {
          intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
        }
        parentActivity.startActivityForResult(Intent.createChooser(intent,LocaleController.getString("ShareFile",R.string.ShareFile)),500);
      }
 else {
        AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
        builder.setMessage(LocaleController.getString("PleaseDownload",R.string.PleaseDownload));
        builder.show();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
 else   if (id == 4) {
    if (UserConfig.selectedAccount != currentAccount) {
      parentActivity.switchToAccount(currentAccount,true);
    }
    Bundle args=new Bundle();
    long did=messageObject.getDialogId();
    int lower_part=(int)did;
    int high_id=(int)(did >> 32);
    if (lower_part != 0) {
      if (high_id == 1) {
        args.putInt("chat_id",lower_part);
      }
 else {
        if (lower_part > 0) {
          args.putInt("user_id",lower_part);
        }
 else         if (lower_part < 0) {
          TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_part);
          if (chat != null && chat.migrated_to != null) {
            args.putInt("migrated_to",lower_part);
            lower_part=-chat.migrated_to.channel_id;
          }
          args.putInt("chat_id",-lower_part);
        }
      }
    }
 else {
      args.putInt("enc_id",high_id);
    }
    args.putInt("message_id",messageObject.getId());
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
    parentActivity.presentFragment(new ChatActivity(args),false,false);
    dismiss();
  }
}
