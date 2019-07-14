public void showCamera(){
  if (paused || !mediaEnabled) {
    return;
  }
  if (cameraView == null) {
    cameraView=new CameraView(baseFragment.getParentActivity(),openWithFrontFaceCamera);
    cameraView.setFocusable(true);
    cameraView.setContentDescription(LocaleController.getString("AccDescrInstantCamera",R.string.AccDescrInstantCamera));
    container.addView(cameraView,1,LayoutHelper.createFrame(80,80));
    cameraView.setDelegate(new CameraView.CameraViewDelegate(){
      @Override public void onCameraCreated(      Camera camera){
      }
      @Override public void onCameraInit(){
        int count=attachPhotoRecyclerView.getChildCount();
        for (int a=0; a < count; a++) {
          View child=attachPhotoRecyclerView.getChildAt(a);
          if (child instanceof PhotoAttachCameraCell) {
            child.setVisibility(View.INVISIBLE);
            break;
          }
        }
        String current=cameraView.getCameraSession().getCurrentFlashMode();
        String next=cameraView.getCameraSession().getNextFlashMode();
        if (current.equals(next)) {
          for (int a=0; a < 2; a++) {
            flashModeButton[a].setVisibility(View.INVISIBLE);
            flashModeButton[a].setAlpha(0.0f);
            flashModeButton[a].setTranslationY(0.0f);
          }
        }
 else {
          setCameraFlashModeIcon(flashModeButton[0],cameraView.getCameraSession().getCurrentFlashMode());
          for (int a=0; a < 2; a++) {
            flashModeButton[a].setVisibility(a == 0 ? View.VISIBLE : View.INVISIBLE);
            flashModeButton[a].setAlpha(a == 0 && cameraOpened ? 1.0f : 0.0f);
            flashModeButton[a].setTranslationY(0.0f);
          }
        }
        switchCameraButton.setImageResource(cameraView.isFrontface() ? R.drawable.camera_revert1 : R.drawable.camera_revert2);
        switchCameraButton.setVisibility(cameraView.hasFrontFaceCamera() ? View.VISIBLE : View.INVISIBLE);
      }
    }
);
    if (cameraIcon == null) {
      cameraIcon=new FrameLayout(baseFragment.getParentActivity());
      cameraImageView=new ImageView(baseFragment.getParentActivity());
      cameraImageView.setScaleType(ImageView.ScaleType.CENTER);
      cameraImageView.setImageResource(R.drawable.instant_camera);
      cameraImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogCameraIcon),PorterDuff.Mode.MULTIPLY));
      cameraIcon.addView(cameraImageView,LayoutHelper.createFrame(80,80,Gravity.RIGHT | Gravity.BOTTOM));
    }
    container.addView(cameraIcon,2,LayoutHelper.createFrame(80,80));
    cameraView.setAlpha(mediaEnabled ? 1.0f : 0.2f);
    cameraView.setEnabled(mediaEnabled);
    cameraIcon.setAlpha(mediaEnabled ? 1.0f : 0.2f);
    cameraIcon.setEnabled(mediaEnabled);
  }
  cameraView.setTranslationX(cameraViewLocation[0]);
  cameraView.setTranslationY(cameraViewLocation[1]);
  cameraIcon.setTranslationX(cameraViewLocation[0]);
  cameraIcon.setTranslationY(cameraViewLocation[1]);
}
