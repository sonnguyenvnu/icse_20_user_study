public void startSecretChat(final Context context,final TLRPC.User user){
  if (user == null || context == null) {
    return;
  }
  startingSecretChat=true;
  final AlertDialog progressDialog=new AlertDialog(context,3);
  TLRPC.TL_messages_getDhConfig req=new TLRPC.TL_messages_getDhConfig();
  req.random_length=256;
  req.version=MessagesStorage.getInstance(currentAccount).getLastSecretVersion();
  final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      TLRPC.messages_DhConfig res=(TLRPC.messages_DhConfig)response;
      if (response instanceof TLRPC.TL_messages_dhConfig) {
        if (!Utilities.isGoodPrime(res.p,res.g)) {
          AndroidUtilities.runOnUIThread(() -> {
            try {
              if (!((Activity)context).isFinishing()) {
                progressDialog.dismiss();
              }
            }
 catch (            Exception e) {
              FileLog.e(e);
            }
          }
);
          return;
        }
        MessagesStorage.getInstance(currentAccount).setSecretPBytes(res.p);
        MessagesStorage.getInstance(currentAccount).setSecretG(res.g);
        MessagesStorage.getInstance(currentAccount).setLastSecretVersion(res.version);
        MessagesStorage.getInstance(currentAccount).saveSecretParams(MessagesStorage.getInstance(currentAccount).getLastSecretVersion(),MessagesStorage.getInstance(currentAccount).getSecretG(),MessagesStorage.getInstance(currentAccount).getSecretPBytes());
      }
      final byte[] salt=new byte[256];
      for (int a=0; a < 256; a++) {
        salt[a]=(byte)((byte)(Utilities.random.nextDouble() * 256) ^ res.random[a]);
      }
      BigInteger i_g_a=BigInteger.valueOf(MessagesStorage.getInstance(currentAccount).getSecretG());
      i_g_a=i_g_a.modPow(new BigInteger(1,salt),new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes()));
      byte[] g_a=i_g_a.toByteArray();
      if (g_a.length > 256) {
        byte[] correctedAuth=new byte[256];
        System.arraycopy(g_a,1,correctedAuth,0,256);
        g_a=correctedAuth;
      }
      TLRPC.TL_messages_requestEncryption req2=new TLRPC.TL_messages_requestEncryption();
      req2.g_a=g_a;
      req2.user_id=MessagesController.getInstance(currentAccount).getInputUser(user);
      req2.random_id=Utilities.random.nextInt();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req2,(response1,error1) -> {
        if (error1 == null) {
          AndroidUtilities.runOnUIThread(() -> {
            startingSecretChat=false;
            if (!((Activity)context).isFinishing()) {
              try {
                progressDialog.dismiss();
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
            }
            TLRPC.EncryptedChat chat=(TLRPC.EncryptedChat)response1;
            chat.user_id=chat.participant_id;
            chat.seq_in=-2;
            chat.seq_out=1;
            chat.a_or_b=salt;
            MessagesController.getInstance(currentAccount).putEncryptedChat(chat,false);
            TLRPC.Dialog dialog=new TLRPC.TL_dialog();
            dialog.id=DialogObject.makeSecretDialogId(chat.id);
            dialog.unread_count=0;
            dialog.top_message=0;
            dialog.last_message_date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
            MessagesController.getInstance(currentAccount).dialogs_dict.put(dialog.id,dialog);
            MessagesController.getInstance(currentAccount).allDialogs.add(dialog);
            MessagesController.getInstance(currentAccount).sortDialogs(null);
            MessagesStorage.getInstance(currentAccount).putEncryptedChat(chat,user,dialog);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.encryptedChatCreated,chat);
            Utilities.stageQueue.postRunnable(() -> {
              if (!delayedEncryptedChatUpdates.isEmpty()) {
                MessagesController.getInstance(currentAccount).processUpdateArray(delayedEncryptedChatUpdates,null,null,false);
                delayedEncryptedChatUpdates.clear();
              }
            }
);
          }
);
        }
 else {
          delayedEncryptedChatUpdates.clear();
          AndroidUtilities.runOnUIThread(() -> {
            if (!((Activity)context).isFinishing()) {
              startingSecretChat=false;
              try {
                progressDialog.dismiss();
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
              AlertDialog.Builder builder=new AlertDialog.Builder(context);
              builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
              builder.setMessage(LocaleController.getString("CreateEncryptedChatError",R.string.CreateEncryptedChatError));
              builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
              builder.show().setCanceledOnTouchOutside(true);
            }
          }
);
        }
      }
,ConnectionsManager.RequestFlagFailOnServerErrors);
    }
 else {
      delayedEncryptedChatUpdates.clear();
      AndroidUtilities.runOnUIThread(() -> {
        startingSecretChat=false;
        if (!((Activity)context).isFinishing()) {
          try {
            progressDialog.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
);
    }
  }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  progressDialog.setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true));
  try {
    progressDialog.show();
  }
 catch (  Exception e) {
  }
}
