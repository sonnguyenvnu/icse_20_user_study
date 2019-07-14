/** 
 * <p>Starts a capture session for camera preview.</p> <p>This rewrites  {@link #mPreviewRequestBuilder}.</p> <p>The result will be continuously processed in  {@link #mSessionCallback}.</p>
 */
void startCaptureSession(){
  if (!isCameraOpened() || !mPreview.isReady() || mImageReader == null) {
    return;
  }
  Size previewSize=chooseOptimalSize();
  mPreview.setBufferSize(previewSize.getWidth(),previewSize.getHeight());
  Surface surface=mPreview.getSurface();
  try {
    mPreviewRequestBuilder=mCamera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
    mPreviewRequestBuilder.addTarget(surface);
    mCamera.createCaptureSession(Arrays.asList(surface,mImageReader.getSurface()),mSessionCallback,null);
  }
 catch (  CameraAccessException e) {
    throw new RuntimeException("Failed to start camera session");
  }
}
