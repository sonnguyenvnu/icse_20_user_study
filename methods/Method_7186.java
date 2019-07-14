@UiThread public static void prepareSendingText(final String text,final long dialog_id){
  final int currentAccount=UserConfig.selectedAccount;
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> Utilities.stageQueue.postRunnable(() -> AndroidUtilities.runOnUIThread(() -> {
    String textFinal=getTrimmedString(text);
    if (textFinal.length() != 0) {
      int count=(int)Math.ceil(textFinal.length() / 4096.0f);
      for (int a=0; a < count; a++) {
        String mess=textFinal.substring(a * 4096,Math.min((a + 1) * 4096,textFinal.length()));
        SendMessagesHelper.getInstance(currentAccount).sendMessage(mess,dialog_id,null,null,true,null,null,null);
      }
    }
  }
)));
}
