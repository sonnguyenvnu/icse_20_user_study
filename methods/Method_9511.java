private void onPhotoClosed(PlaceProviderObject object){
  isVisible=false;
  disableShowCheck=true;
  currentMessageObject=null;
  currentBotInlineResult=null;
  currentFileLocation=null;
  currentSecureDocument=null;
  currentPathObject=null;
  if (videoPlayerControlFrameLayout != null) {
    videoPlayerControlFrameLayout.setVisibility(View.GONE);
    dateTextView.setVisibility(View.VISIBLE);
    nameTextView.setVisibility(View.VISIBLE);
  }
  sendPhotoType=0;
  if (currentThumb != null) {
    currentThumb.release();
    currentThumb=null;
  }
  parentAlert=null;
  if (currentAnimation != null) {
    currentAnimation.setSecondParentView(null);
    currentAnimation=null;
  }
  for (int a=0; a < 3; a++) {
    if (photoProgressViews[a] != null) {
      photoProgressViews[a].setBackgroundState(-1,false);
    }
  }
  requestVideoPreview(0);
  if (videoTimelineView != null) {
    videoTimelineView.destroy();
  }
  centerImage.setImageBitmap((Bitmap)null);
  leftImage.setImageBitmap((Bitmap)null);
  rightImage.setImageBitmap((Bitmap)null);
  containerView.post(() -> {
    animatingImageView.setImageBitmap(null);
    try {
      if (windowView.getParent() != null) {
        WindowManager wm=(WindowManager)parentActivity.getSystemService(Context.WINDOW_SERVICE);
        wm.removeView(windowView);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
  if (placeProvider != null) {
    placeProvider.willHidePhotoViewer();
  }
  groupedPhotosListView.clear();
  placeProvider=null;
  selectedPhotosAdapter.notifyDataSetChanged();
  disableShowCheck=false;
  if (object != null) {
    object.imageReceiver.setVisible(true,true);
  }
}
