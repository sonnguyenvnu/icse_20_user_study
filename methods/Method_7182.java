@UiThread public static void prepareSendingAudioDocuments(final ArrayList<MessageObject> messageObjects,final long dialog_id,final MessageObject reply_to_msg,final MessageObject editingMessageObject){
  final int currentAccount=UserConfig.selectedAccount;
  new Thread(() -> {
    int size=messageObjects.size();
    for (int a=0; a < size; a++) {
      final MessageObject messageObject=messageObjects.get(a);
      String originalPath=messageObject.messageOwner.attachPath;
      final File f=new File(originalPath);
      boolean isEncrypted=(int)dialog_id == 0;
      if (originalPath != null) {
        originalPath+="audio" + f.length();
      }
      TLRPC.TL_document document=null;
      String parentObject=null;
      if (!isEncrypted) {
        Object[] sentData=MessagesStorage.getInstance(currentAccount).getSentFile(originalPath,!isEncrypted ? 1 : 4);
        if (sentData != null) {
          document=(TLRPC.TL_document)sentData[0];
          parentObject=(String)sentData[1];
          ensureMediaThumbExists(currentAccount,isEncrypted,document,originalPath,null,0);
        }
      }
      if (document == null) {
        document=(TLRPC.TL_document)messageObject.messageOwner.media.document;
      }
      if (isEncrypted) {
        int high_id=(int)(dialog_id >> 32);
        TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
        if (encryptedChat == null) {
          return;
        }
      }
      final HashMap<String,String> params=new HashMap<>();
      if (originalPath != null) {
        params.put("originalPath",originalPath);
      }
      final TLRPC.TL_document documentFinal=document;
      final String parentFinal=parentObject;
      AndroidUtilities.runOnUIThread(() -> {
        if (editingMessageObject != null) {
          SendMessagesHelper.getInstance(currentAccount).editMessageMedia(editingMessageObject,null,null,documentFinal,messageObject.messageOwner.attachPath,params,false,parentFinal);
        }
 else {
          SendMessagesHelper.getInstance(currentAccount).sendMessage(documentFinal,null,messageObject.messageOwner.attachPath,dialog_id,reply_to_msg,null,null,null,params,0,parentFinal);
        }
      }
);
    }
  }
).start();
}
