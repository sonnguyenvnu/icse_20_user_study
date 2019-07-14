private void checkAutoDownloadMessages(boolean scrollUp){
  if (chatListView == null) {
    return;
  }
  int count=chatListView.getChildCount();
  int firstMessagePosition=-1;
  int lastMessagePosition=-1;
  for (int a=0; a < count; a++) {
    View child=chatListView.getChildAt(a);
    if (!(child instanceof ChatMessageCell)) {
      continue;
    }
    RecyclerListView.ViewHolder holder=chatListView.findContainingViewHolder(child);
    if (holder != null) {
      int p=holder.getAdapterPosition();
      if (firstMessagePosition == -1) {
        firstMessagePosition=p;
      }
      lastMessagePosition=p;
    }
    ChatMessageCell cell=(ChatMessageCell)child;
    MessageObject object=cell.getMessageObject();
    if (object == null || object.mediaExists || !object.isSent()) {
      continue;
    }
    TLRPC.Document document=object.getDocument();
    if (document == null) {
      continue;
    }
    int canDownload;
    if (!MessageObject.isStickerDocument(document) && !MessageObject.isGifDocument(document) && !MessageObject.isRoundVideoDocument(document) && (canDownload=DownloadController.getInstance(currentAccount).canDownloadMedia(object.messageOwner)) != 0) {
      if (canDownload == 2) {
        if (currentEncryptedChat == null && !object.shouldEncryptPhotoOrVideo() && object.canStreamVideo()) {
          FileLoader.getInstance(currentAccount).loadFile(document,object,0,10);
        }
      }
 else {
        FileLoader.getInstance(currentAccount).loadFile(document,object,0,MessageObject.isVideoDocument(document) && object.shouldEncryptPhotoOrVideo() ? 2 : 0);
        cell.updateButtonState(false,true,false);
      }
    }
  }
  if (firstMessagePosition != -1) {
    int lastPosition;
    if (scrollUp) {
      firstMessagePosition=lastPosition=lastMessagePosition;
      if (firstMessagePosition + 10 >= chatAdapter.messagesEndRow) {
        firstMessagePosition=chatAdapter.messagesEndRow;
      }
 else {
        firstMessagePosition=firstMessagePosition + 10;
      }
      for (int a=lastPosition, N=messages.size(); a < firstMessagePosition; a++) {
        int n=a - chatAdapter.messagesStartRow;
        if (n < 0 || n >= N) {
          continue;
        }
        checkAutoDownloadMessage(messages.get(n));
      }
    }
 else {
      if (firstMessagePosition - 20 <= chatAdapter.messagesStartRow) {
        lastPosition=chatAdapter.messagesStartRow;
      }
 else {
        lastPosition=firstMessagePosition - 20;
      }
      for (int a=firstMessagePosition - 1, N=messages.size(); a >= lastPosition; a--) {
        int n=a - chatAdapter.messagesStartRow;
        if (n < 0 || n >= N) {
          continue;
        }
        checkAutoDownloadMessage(messages.get(n));
      }
    }
  }
  showNoSoundHint();
}
