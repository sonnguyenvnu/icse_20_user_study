@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.fileDidFailedLoad || id == NotificationCenter.httpFileDidFailedLoad) {
    String fileName=(String)args[0];
    Integer canceled=(Integer)args[1];
    listenerInProgress=true;
    ArrayList<WeakReference<FileDownloadProgressListener>> arrayList=loadingFileObservers.get(fileName);
    if (arrayList != null) {
      for (int a=0, size=arrayList.size(); a < size; a++) {
        WeakReference<FileDownloadProgressListener> reference=arrayList.get(a);
        if (reference.get() != null) {
          reference.get().onFailedDownload(fileName,canceled == 1);
          if (canceled != 1) {
            observersByTag.remove(reference.get().getObserverTag());
          }
        }
      }
      if (canceled != 1) {
        loadingFileObservers.remove(fileName);
      }
    }
    listenerInProgress=false;
    processLaterArrays();
    checkDownloadFinished(fileName,canceled);
  }
 else   if (id == NotificationCenter.fileDidLoad || id == NotificationCenter.httpFileDidLoad) {
    listenerInProgress=true;
    String fileName=(String)args[0];
    ArrayList<MessageObject> messageObjects=loadingFileMessagesObservers.get(fileName);
    if (messageObjects != null) {
      for (int a=0, size=messageObjects.size(); a < size; a++) {
        MessageObject messageObject=messageObjects.get(a);
        messageObject.mediaExists=true;
      }
      loadingFileMessagesObservers.remove(fileName);
    }
    ArrayList<WeakReference<FileDownloadProgressListener>> arrayList=loadingFileObservers.get(fileName);
    if (arrayList != null) {
      for (int a=0, size=arrayList.size(); a < size; a++) {
        WeakReference<FileDownloadProgressListener> reference=arrayList.get(a);
        if (reference.get() != null) {
          reference.get().onSuccessDownload(fileName);
          observersByTag.remove(reference.get().getObserverTag());
        }
      }
      loadingFileObservers.remove(fileName);
    }
    listenerInProgress=false;
    processLaterArrays();
    checkDownloadFinished(fileName,0);
  }
 else   if (id == NotificationCenter.FileLoadProgressChanged) {
    listenerInProgress=true;
    String fileName=(String)args[0];
    ArrayList<WeakReference<FileDownloadProgressListener>> arrayList=loadingFileObservers.get(fileName);
    if (arrayList != null) {
      Float progress=(Float)args[1];
      for (int a=0, size=arrayList.size(); a < size; a++) {
        WeakReference<FileDownloadProgressListener> reference=arrayList.get(a);
        if (reference.get() != null) {
          reference.get().onProgressDownload(fileName,progress);
        }
      }
    }
    listenerInProgress=false;
    processLaterArrays();
  }
 else   if (id == NotificationCenter.FileUploadProgressChanged) {
    listenerInProgress=true;
    String fileName=(String)args[0];
    ArrayList<WeakReference<FileDownloadProgressListener>> arrayList=loadingFileObservers.get(fileName);
    if (arrayList != null) {
      Float progress=(Float)args[1];
      Boolean enc=(Boolean)args[2];
      for (int a=0, size=arrayList.size(); a < size; a++) {
        WeakReference<FileDownloadProgressListener> reference=arrayList.get(a);
        if (reference.get() != null) {
          reference.get().onProgressUpload(fileName,progress,enc);
        }
      }
    }
    listenerInProgress=false;
    processLaterArrays();
    try {
      ArrayList<SendMessagesHelper.DelayedMessage> delayedMessages=SendMessagesHelper.getInstance(currentAccount).getDelayedMessages(fileName);
      if (delayedMessages != null) {
        for (int a=0; a < delayedMessages.size(); a++) {
          SendMessagesHelper.DelayedMessage delayedMessage=delayedMessages.get(a);
          if (delayedMessage.encryptedChat == null) {
            long dialog_id=delayedMessage.peer;
            if (delayedMessage.type == 4) {
              Long lastTime=typingTimes.get(dialog_id);
              if (lastTime == null || lastTime + 4000 < System.currentTimeMillis()) {
                MessageObject messageObject=(MessageObject)delayedMessage.extraHashMap.get(fileName + "_i");
                if (messageObject != null && messageObject.isVideo()) {
                  MessagesController.getInstance(currentAccount).sendTyping(dialog_id,5,0);
                }
 else {
                  MessagesController.getInstance(currentAccount).sendTyping(dialog_id,4,0);
                }
                typingTimes.put(dialog_id,System.currentTimeMillis());
              }
            }
 else {
              Long lastTime=typingTimes.get(dialog_id);
              TLRPC.Document document=delayedMessage.obj.getDocument();
              if (lastTime == null || lastTime + 4000 < System.currentTimeMillis()) {
                if (delayedMessage.obj.isRoundVideo()) {
                  MessagesController.getInstance(currentAccount).sendTyping(dialog_id,8,0);
                }
 else                 if (delayedMessage.obj.isVideo()) {
                  MessagesController.getInstance(currentAccount).sendTyping(dialog_id,5,0);
                }
 else                 if (delayedMessage.obj.isVoice()) {
                  MessagesController.getInstance(currentAccount).sendTyping(dialog_id,9,0);
                }
 else                 if (delayedMessage.obj.getDocument() != null) {
                  MessagesController.getInstance(currentAccount).sendTyping(dialog_id,3,0);
                }
 else                 if (delayedMessage.photoSize != null) {
                  MessagesController.getInstance(currentAccount).sendTyping(dialog_id,4,0);
                }
                typingTimes.put(dialog_id,System.currentTimeMillis());
              }
            }
          }
        }
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
