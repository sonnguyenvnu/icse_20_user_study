@SuppressLint("NewApi") void setUpPreview(){
  try {
    if (mPreview.getOutputClass() == SurfaceHolder.class) {
      final boolean needsToStopPreview=mShowingPreview && Build.VERSION.SDK_INT < 14;
      if (needsToStopPreview) {
        mCamera.stopPreview();
      }
      mCamera.setPreviewDisplay(mPreview.getSurfaceHolder());
      if (needsToStopPreview) {
        mCamera.startPreview();
      }
    }
 else {
      mCamera.setPreviewTexture((SurfaceTexture)mPreview.getSurfaceTexture());
    }
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
