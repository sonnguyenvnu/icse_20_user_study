private void uploadMultiMedia(final DelayedMessage message,final TLRPC.InputMedia inputMedia,final TLRPC.InputEncryptedFile inputEncryptedFile,String key){
  if (inputMedia != null) {
    TLRPC.TL_messages_sendMultiMedia multiMedia=(TLRPC.TL_messages_sendMultiMedia)message.sendRequest;
    for (int a=0; a < multiMedia.multi_media.size(); a++) {
      if (multiMedia.multi_media.get(a).media == inputMedia) {
        putToSendingMessages(message.messages.get(a));
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.FileUploadProgressChanged,key,1.0f,false);
        break;
      }
    }
    TLRPC.TL_messages_uploadMedia req=new TLRPC.TL_messages_uploadMedia();
    req.media=inputMedia;
    req.peer=((TLRPC.TL_messages_sendMultiMedia)message.sendRequest).peer;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      TLRPC.InputMedia newInputMedia=null;
      if (response != null) {
        TLRPC.MessageMedia messageMedia=(TLRPC.MessageMedia)response;
        if (inputMedia instanceof TLRPC.TL_inputMediaUploadedPhoto && messageMedia instanceof TLRPC.TL_messageMediaPhoto) {
          TLRPC.TL_inputMediaPhoto inputMediaPhoto=new TLRPC.TL_inputMediaPhoto();
          inputMediaPhoto.id=new TLRPC.TL_inputPhoto();
          inputMediaPhoto.id.id=messageMedia.photo.id;
          inputMediaPhoto.id.access_hash=messageMedia.photo.access_hash;
          inputMediaPhoto.id.file_reference=messageMedia.photo.file_reference;
          newInputMedia=inputMediaPhoto;
        }
 else         if (inputMedia instanceof TLRPC.TL_inputMediaUploadedDocument && messageMedia instanceof TLRPC.TL_messageMediaDocument) {
          TLRPC.TL_inputMediaDocument inputMediaDocument=new TLRPC.TL_inputMediaDocument();
          inputMediaDocument.id=new TLRPC.TL_inputDocument();
          inputMediaDocument.id.id=messageMedia.document.id;
          inputMediaDocument.id.access_hash=messageMedia.document.access_hash;
          inputMediaDocument.id.file_reference=messageMedia.document.file_reference;
          newInputMedia=inputMediaDocument;
        }
      }
      if (newInputMedia != null) {
        if (inputMedia.ttl_seconds != 0) {
          newInputMedia.ttl_seconds=inputMedia.ttl_seconds;
          newInputMedia.flags|=1;
        }
        TLRPC.TL_messages_sendMultiMedia req1=(TLRPC.TL_messages_sendMultiMedia)message.sendRequest;
        for (int a=0; a < req1.multi_media.size(); a++) {
          if (req1.multi_media.get(a).media == inputMedia) {
            req1.multi_media.get(a).media=newInputMedia;
            break;
          }
        }
        sendReadyToSendGroup(message,false,true);
      }
 else {
        message.markAsError();
      }
    }
));
  }
 else   if (inputEncryptedFile != null) {
    TLRPC.TL_messages_sendEncryptedMultiMedia multiMedia=(TLRPC.TL_messages_sendEncryptedMultiMedia)message.sendEncryptedRequest;
    for (int a=0; a < multiMedia.files.size(); a++) {
      if (multiMedia.files.get(a) == inputEncryptedFile) {
        putToSendingMessages(message.messages.get(a));
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.FileUploadProgressChanged,key,1.0f,false);
        break;
      }
    }
    sendReadyToSendGroup(message,false,true);
  }
}
