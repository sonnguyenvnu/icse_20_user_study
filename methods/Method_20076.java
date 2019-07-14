@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull List<FirebaseVisionObject> results,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  if (originalCameraImage != null) {
    CameraImageGraphic imageGraphic=new CameraImageGraphic(graphicOverlay,originalCameraImage);
    graphicOverlay.add(imageGraphic);
  }
  for (  FirebaseVisionObject object : results) {
    ObjectGraphic objectGraphic=new ObjectGraphic(graphicOverlay,object);
    graphicOverlay.add(objectGraphic);
  }
  graphicOverlay.postInvalidate();
}
