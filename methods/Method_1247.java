public void setEnabled(boolean enabled){
  mEnabled=enabled;
  if (enabled) {
    setupListeners();
    if (mImageOriginListener != null) {
      mPipelineDraweeController.addImageOriginListener(mImageOriginListener);
    }
    if (mImagePerfControllerListener != null) {
      mPipelineDraweeController.addControllerListener(mImagePerfControllerListener);
    }
    if (mForwardingRequestListener != null) {
      mPipelineDraweeController.addRequestListener(mForwardingRequestListener);
    }
  }
 else {
    if (mImageOriginListener != null) {
      mPipelineDraweeController.removeImageOriginListener(mImageOriginListener);
    }
    if (mImagePerfControllerListener != null) {
      mPipelineDraweeController.removeControllerListener(mImagePerfControllerListener);
    }
    if (mForwardingRequestListener != null) {
      mPipelineDraweeController.removeRequestListener(mForwardingRequestListener);
    }
  }
}
