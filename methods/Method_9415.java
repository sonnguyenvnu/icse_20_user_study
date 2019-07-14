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
        if (button == 8 || button == 7) {
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
              photos.add(info);
              photoEntry.reset();
            }
            processSelectedFiles(photos);
          }
          return;
        }
 else         if (chatAttachAlert != null) {
          chatAttachAlert.dismissWithButtonClick(button);
        }
        processSelectedAttach(button);
      }
      @Override public View getRevealView(){
        return null;
      }
      @Override public void didSelectBot(      TLRPC.User user){
      }
      @Override public void onCameraOpened(){
        AndroidUtilities.hideKeyboard(fragmentView.findFocus());
      }
      @Override public boolean allowGroupPhotos(){
        return false;
      }
    }
);
  }
}
