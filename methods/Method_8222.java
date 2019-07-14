private void createChatAttachView(){
  if (getParentActivity() == null) {
    return;
  }
  if (chatAttachAlert == null) {
    chatAttachAlert=new ChatAttachAlert(getParentActivity(),this);
    chatAttachAlert.setDelegate(new ChatAttachAlert.ChatAttachViewDelegate(){
      @Override public void didPressedButton(      int button){
        if (getParentActivity() == null || chatAttachAlert == null) {
          return;
        }
        if (chatAttachAlert != null) {
          editingMessageObject=chatAttachAlert.getEditingMessageObject();
        }
 else {
          editingMessageObject=null;
        }
        if (button == 8 || button == 7 || button == 4 && !chatAttachAlert.getSelectedPhotos().isEmpty()) {
          if (button != 8) {
            chatAttachAlert.dismiss();
          }
          HashMap<Object,Object> selectedPhotos=chatAttachAlert.getSelectedPhotos();
          ArrayList<Object> selectedPhotosOrder=chatAttachAlert.getSelectedPhotosOrder();
          if (!selectedPhotos.isEmpty()) {
            ArrayList<SendMessagesHelper.SendingMediaInfo> photos=new ArrayList<>();
            for (int a=0; a < selectedPhotosOrder.size(); a++) {
              MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)selectedPhotos.get(selectedPhotosOrder.get(a));
              SendMessagesHelper.SendingMediaInfo info=new SendMessagesHelper.SendingMediaInfo();
              if (photoEntry.imagePath != null) {
                info.path=photoEntry.imagePath;
              }
 else               if (photoEntry.path != null) {
                info.path=photoEntry.path;
              }
              info.isVideo=photoEntry.isVideo;
              info.caption=photoEntry.caption != null ? photoEntry.caption.toString() : null;
              info.entities=photoEntry.entities;
              info.masks=!photoEntry.stickers.isEmpty() ? new ArrayList<>(photoEntry.stickers) : null;
              info.ttl=photoEntry.ttl;
              info.videoEditedInfo=photoEntry.editedInfo;
              info.canDeleteAfter=photoEntry.canDeleteAfter;
              photos.add(info);
              photoEntry.reset();
            }
            fillEditingMediaWithCaption(photos.get(0).caption,photos.get(0).entities);
            SendMessagesHelper.prepareSendingMedia(photos,dialog_id,replyingMessageObject,null,button == 4,SharedConfig.groupPhotosEnabled,editingMessageObject);
            hideFieldPanel(false);
            DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,true);
          }
          return;
        }
 else         if (chatAttachAlert != null) {
          chatAttachAlert.dismissWithButtonClick(button);
        }
        processSelectedAttach(button);
      }
      @Override public View getRevealView(){
        return chatActivityEnterView.getAttachButton();
      }
      @Override public void didSelectBot(      TLRPC.User user){
        if (chatActivityEnterView == null || TextUtils.isEmpty(user.username)) {
          return;
        }
        chatActivityEnterView.setFieldText("@" + user.username + " ");
        chatActivityEnterView.openKeyboard();
      }
      @Override public void onCameraOpened(){
        chatActivityEnterView.closeKeyboard();
      }
      @Override public boolean allowGroupPhotos(){
        return ChatActivity.this.allowGroupPhotos();
      }
    }
);
  }
}
