private void checkPreviewMatrix(){
  if (previewSize == null) {
    return;
  }
  adjustAspectRatio(previewSize.getWidth(),previewSize.getHeight(),((Activity)getContext()).getWindowManager().getDefaultDisplay().getRotation());
}
