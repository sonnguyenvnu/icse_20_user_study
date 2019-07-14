private boolean startVideoConvertFromQueue(){
  if (!videoConvertQueue.isEmpty()) {
synchronized (videoConvertSync) {
      cancelCurrentVideoConversion=false;
    }
    MessageObject messageObject=videoConvertQueue.get(0);
    Intent intent=new Intent(ApplicationLoader.applicationContext,VideoEncodingService.class);
    intent.putExtra("path",messageObject.messageOwner.attachPath);
    intent.putExtra("currentAccount",messageObject.currentAccount);
    if (messageObject.messageOwner.media.document != null) {
      for (int a=0; a < messageObject.messageOwner.media.document.attributes.size(); a++) {
        TLRPC.DocumentAttribute documentAttribute=messageObject.messageOwner.media.document.attributes.get(a);
        if (documentAttribute instanceof TLRPC.TL_documentAttributeAnimated) {
          intent.putExtra("gif",true);
          break;
        }
      }
    }
    if (messageObject.getId() != 0) {
      try {
        ApplicationLoader.applicationContext.startService(intent);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
    VideoConvertRunnable.runConversion(messageObject);
    return true;
  }
  return false;
}
