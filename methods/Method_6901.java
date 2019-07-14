public void convertToMegaGroup(final Context context,int chat_id,MessagesStorage.IntCallback convertRunnable){
  TLRPC.TL_messages_migrateChat req=new TLRPC.TL_messages_migrateChat();
  req.chat_id=chat_id;
  final AlertDialog progressDialog=new AlertDialog(context,3);
  final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      AndroidUtilities.runOnUIThread(() -> {
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
      TLRPC.Updates updates=(TLRPC.Updates)response;
      processUpdates((TLRPC.Updates)response,false);
      AndroidUtilities.runOnUIThread(() -> {
        if (convertRunnable != null) {
          for (int a=0; a < updates.chats.size(); a++) {
            TLRPC.Chat chat=updates.chats.get(a);
            if (ChatObject.isChannel(chat)) {
              convertRunnable.run(chat.id);
              break;
            }
          }
        }
      }
);
    }
 else {
      AndroidUtilities.runOnUIThread(() -> {
        if (!((Activity)context).isFinishing()) {
          try {
            progressDialog.dismiss();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
          AlertDialog.Builder builder=new AlertDialog.Builder(context);
          builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
          builder.setMessage(LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred));
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
          builder.show().setCanceledOnTouchOutside(true);
        }
      }
);
    }
  }
);
  progressDialog.setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true));
  try {
    progressDialog.show();
  }
 catch (  Exception e) {
  }
}
