@Override public void onMessageReceived(final MessageEvent messageEvent){
  if ("/reply".equals(messageEvent.getPath())) {
    AndroidUtilities.runOnUIThread(new Runnable(){
      @Override public void run(){
        try {
          ApplicationLoader.postInitApplication();
          String data=new String(messageEvent.getData(),"UTF-8");
          JSONObject r=new JSONObject(data);
          CharSequence text=r.getString("text");
          if (text == null || text.length() == 0) {
            return;
          }
          long dialog_id=r.getLong("chat_id");
          int max_id=r.getInt("max_id");
          int currentAccount=-1;
          int accountID=r.getInt("account_id");
          for (int i=0; i < UserConfig.getActivatedAccountsCount(); i++) {
            if (UserConfig.getInstance(i).getClientUserId() == accountID) {
              currentAccount=i;
              break;
            }
          }
          if (dialog_id == 0 || max_id == 0 || currentAccount == -1) {
            return;
          }
          SendMessagesHelper.getInstance(currentAccount).sendMessage(text.toString(),dialog_id,null,null,true,null,null,null);
          MessagesController.getInstance(currentAccount).markDialogAsRead(dialog_id,max_id,max_id,0,false,0,true);
        }
 catch (        Exception x) {
          if (BuildVars.LOGS_ENABLED)           FileLog.e(x);
        }
      }
    }
);
  }
}
