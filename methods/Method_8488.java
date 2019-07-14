private void openPhotoViewer(MediaController.PhotoEntry entry,final boolean sameTakePictureOrientation,boolean external){
  if (entry != null) {
    cameraPhotos.add(entry);
    selectedPhotos.put(entry.imageId,entry);
    selectedPhotosOrder.add(entry.imageId);
    updatePhotosButton();
    photoAttachAdapter.notifyDataSetChanged();
    cameraAttachAdapter.notifyDataSetChanged();
  }
  if (entry != null && !external && cameraPhotos.size() > 1) {
    updatePhotosCounter();
    if (cameraView != null) {
      CameraController.getInstance().startPreview(cameraView.getCameraSession());
    }
    mediaCaptured=false;
    return;
  }
  if (cameraPhotos.isEmpty()) {
    return;
  }
  cancelTakingPhotos=true;
  PhotoViewer.getInstance().setParentActivity(baseFragment.getParentActivity());
  PhotoViewer.getInstance().setParentAlert(ChatAttachAlert.this);
  PhotoViewer.getInstance().setMaxSelectedPhotos(maxSelectedPhotos);
  ChatActivity chatActivity;
  int type;
  if (baseFragment instanceof ChatActivity) {
    chatActivity=(ChatActivity)baseFragment;
    type=2;
  }
 else {
    chatActivity=null;
    type=5;
  }
  PhotoViewer.getInstance().openPhotoForSelect(getAllPhotosArray(),cameraPhotos.size() - 1,type,new BasePhotoProvider(){
    @Override public ImageReceiver.BitmapHolder getThumbForPhoto(    MessageObject messageObject,    TLRPC.FileLocation fileLocation,    int index){
      return null;
    }
    @Override public boolean cancelButtonPressed(){
      if (cameraOpened && cameraView != null) {
        AndroidUtilities.runOnUIThread(() -> {
          if (cameraView != null && !isDismissed() && Build.VERSION.SDK_INT >= 21) {
            cameraView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN);
          }
        }
,1000);
        CameraController.getInstance().startPreview(cameraView.getCameraSession());
      }
      if (cancelTakingPhotos && cameraPhotos.size() == 1) {
        for (int a=0, size=cameraPhotos.size(); a < size; a++) {
          MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)cameraPhotos.get(a);
          new File(photoEntry.path).delete();
          if (photoEntry.imagePath != null) {
            new File(photoEntry.imagePath).delete();
          }
          if (photoEntry.thumbPath != null) {
            new File(photoEntry.thumbPath).delete();
          }
        }
        cameraPhotos.clear();
        selectedPhotosOrder.clear();
        selectedPhotos.clear();
        counterTextView.setVisibility(View.INVISIBLE);
        cameraPhotoRecyclerView.setVisibility(View.GONE);
        photoAttachAdapter.notifyDataSetChanged();
        cameraAttachAdapter.notifyDataSetChanged();
        updatePhotosButton();
      }
      return true;
    }
    @Override public void needAddMorePhotos(){
      cancelTakingPhotos=false;
      if (mediaFromExternalCamera) {
        delegate.didPressedButton(0);
        return;
      }
      if (!cameraOpened) {
        openCamera(false);
      }
      counterTextView.setVisibility(View.VISIBLE);
      cameraPhotoRecyclerView.setVisibility(View.VISIBLE);
      counterTextView.setAlpha(1.0f);
      updatePhotosCounter();
    }
    @Override public void sendButtonPressed(    int index,    VideoEditedInfo videoEditedInfo){
      if (cameraPhotos.isEmpty() || baseFragment == null) {
        return;
      }
      if (videoEditedInfo != null && index >= 0 && index < cameraPhotos.size()) {
        MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)cameraPhotos.get(index);
        photoEntry.editedInfo=videoEditedInfo;
      }
      if (!(baseFragment instanceof ChatActivity) || !((ChatActivity)baseFragment).isSecretChat()) {
        for (int a=0, size=cameraPhotos.size(); a < size; a++) {
          AndroidUtilities.addMediaToGallery(((MediaController.PhotoEntry)cameraPhotos.get(a)).path);
        }
      }
      delegate.didPressedButton(8);
      cameraPhotos.clear();
      selectedPhotosOrder.clear();
      selectedPhotos.clear();
      photoAttachAdapter.notifyDataSetChanged();
      cameraAttachAdapter.notifyDataSetChanged();
      closeCamera(false);
      dismiss();
    }
    @Override public boolean scaleToFill(){
      if (baseFragment == null || baseFragment.getParentActivity() == null) {
        return false;
      }
      int locked=Settings.System.getInt(baseFragment.getParentActivity().getContentResolver(),Settings.System.ACCELEROMETER_ROTATION,0);
      return sameTakePictureOrientation || locked == 1;
    }
    @Override public void willHidePhotoViewer(){
      mediaCaptured=false;
      int count=attachPhotoRecyclerView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=attachPhotoRecyclerView.getChildAt(a);
        if (view instanceof PhotoAttachPhotoCell) {
          PhotoAttachPhotoCell cell=(PhotoAttachPhotoCell)view;
          cell.showImage();
          cell.showCheck(true);
        }
      }
    }
    @Override public boolean canScrollAway(){
      return false;
    }
    @Override public boolean canCaptureMorePhotos(){
      return maxSelectedPhotos != 1;
    }
  }
,chatActivity);
}
