@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull List<FirebaseVisionFace> faces,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  if (originalCameraImage != null) {
    CameraImageGraphic imageGraphic=new CameraImageGraphic(graphicOverlay,originalCameraImage);
    graphicOverlay.add(imageGraphic);
  }
  for (int i=0; i < faces.size(); ++i) {
    FirebaseVisionFace face=faces.get(i);
    FaceContourGraphic faceGraphic=new FaceContourGraphic(graphicOverlay,face);
    graphicOverlay.add(faceGraphic);
  }
  graphicOverlay.postInvalidate();
}
