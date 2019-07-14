@Override public void onConfigurationChanged(android.content.res.Configuration newConfig){
  fixLayout();
  if (visibleDialog instanceof DatePickerDialog) {
    visibleDialog.dismiss();
  }
  if (scrimPopupWindow != null) {
    scrimPopupWindow.dismiss();
  }
  if (!AndroidUtilities.isTablet()) {
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isVisible()) {
        return;
      }
      MessageObject message=MediaController.getInstance().getPlayingMessageObject();
      if (message != null && message.isVideo()) {
        PhotoViewer.getInstance().setParentActivity(getParentActivity());
        FileLoader.getInstance(currentAccount).setLoadingVideoForPlayer(message.getDocument(),false);
        MediaController.getInstance().cleanupPlayer(true,true,false,true);
        if (PhotoViewer.getInstance().openPhoto(message,message.type != 0 ? dialog_id : 0,message.type != 0 ? mergeDialogId : 0,photoViewerProvider,false)) {
          PhotoViewer.getInstance().setParentChatActivity(ChatActivity.this);
        }
        if (noSoundHintView != null) {
          noSoundHintView.hide();
        }
        if (forwardHintView != null) {
          forwardHintView.hide();
        }
        MediaController.getInstance().resetGoingToShowMessageObject();
      }
    }
 else     if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isOpenedFullScreenVideo()) {
      PhotoViewer.getInstance().injectVideoPlayerToMediaController();
      PhotoViewer.getInstance().closePhoto(false,true);
    }
  }
}
