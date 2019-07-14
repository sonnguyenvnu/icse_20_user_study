public void sendMedia(MediaController.PhotoEntry photoEntry,VideoEditedInfo videoEditedInfo){
  if (photoEntry == null) {
    return;
  }
  fillEditingMediaWithCaption(photoEntry.caption,photoEntry.entities);
  if (photoEntry.isVideo) {
    if (videoEditedInfo != null) {
      SendMessagesHelper.prepareSendingVideo(photoEntry.path,videoEditedInfo.estimatedSize,videoEditedInfo.estimatedDuration,videoEditedInfo.resultWidth,videoEditedInfo.resultHeight,videoEditedInfo,dialog_id,replyingMessageObject,photoEntry.caption,photoEntry.entities,photoEntry.ttl,editingMessageObject);
    }
 else {
      SendMessagesHelper.prepareSendingVideo(photoEntry.path,0,0,0,0,null,dialog_id,replyingMessageObject,photoEntry.caption,photoEntry.entities,photoEntry.ttl,editingMessageObject);
    }
    hideFieldPanel(false);
    DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
  }
 else {
    if (photoEntry.imagePath != null) {
      SendMessagesHelper.prepareSendingPhoto(photoEntry.imagePath,null,dialog_id,replyingMessageObject,photoEntry.caption,photoEntry.entities,photoEntry.stickers,null,photoEntry.ttl,editingMessageObject);
      hideFieldPanel(false);
      DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
    }
 else     if (photoEntry.path != null) {
      SendMessagesHelper.prepareSendingPhoto(photoEntry.path,null,dialog_id,replyingMessageObject,photoEntry.caption,photoEntry.entities,photoEntry.stickers,null,photoEntry.ttl,editingMessageObject);
      hideFieldPanel(false);
      DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
    }
  }
}
