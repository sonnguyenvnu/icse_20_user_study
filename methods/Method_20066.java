@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull List<FirebaseVisionFace> faces,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  if (originalCameraImage != null) {
    CameraImageGraphic imageGraphic=new CameraImageGraphic(graphicOverlay,originalCameraImage);
    graphicOverlay.add(imageGraphic);
  }
  for (int i=0; i < faces.size(); ++i) {
    FirebaseVisionFace face=faces.get(i);
    int cameraFacing=frameMetadata != null ? frameMetadata.getCameraFacing() : Camera.CameraInfo.CAMERA_FACING_BACK;
    FaceGraphic faceGraphic=new FaceGraphic(graphicOverlay,face,cameraFacing,overlayBitmap);
    graphicOverlay.add(faceGraphic);
  }
  graphicOverlay.postInvalidate();
}
