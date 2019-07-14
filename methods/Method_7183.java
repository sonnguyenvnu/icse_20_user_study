@UiThread public static void prepareSendingDocuments(final ArrayList<String> paths,final ArrayList<String> originalPaths,final ArrayList<Uri> uris,final String caption,final String mime,final long dialog_id,final MessageObject reply_to_msg,final InputContentInfoCompat inputContent,final MessageObject editingMessageObject){
  if (paths == null && originalPaths == null && uris == null || paths != null && originalPaths != null && paths.size() != originalPaths.size()) {
    return;
  }
  final int currentAccount=UserConfig.selectedAccount;
  new Thread(() -> {
    boolean error=false;
    if (paths != null) {
      for (int a=0; a < paths.size(); a++) {
        if (!prepareSendingDocumentInternal(currentAccount,paths.get(a),originalPaths.get(a),null,mime,dialog_id,reply_to_msg,caption,null,editingMessageObject,false)) {
          error=true;
        }
      }
    }
    if (uris != null) {
      for (int a=0; a < uris.size(); a++) {
        if (!prepareSendingDocumentInternal(currentAccount,null,null,uris.get(a),mime,dialog_id,reply_to_msg,caption,null,editingMessageObject,false)) {
          error=true;
        }
      }
    }
    if (inputContent != null) {
      inputContent.releasePermission();
    }
    if (error) {
      AndroidUtilities.runOnUIThread(() -> {
        try {
          Toast toast=Toast.makeText(ApplicationLoader.applicationContext,LocaleController.getString("UnsupportedAttachment",R.string.UnsupportedAttachment),Toast.LENGTH_SHORT);
          toast.show();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
);
    }
  }
).start();
}
