@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull List<FirebaseVisionImageLabel> labels,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  if (originalCameraImage != null) {
    CameraImageGraphic imageGraphic=new CameraImageGraphic(graphicOverlay,originalCameraImage);
    graphicOverlay.add(imageGraphic);
  }
  LabelGraphic labelGraphic=new LabelGraphic(graphicOverlay,labels);
  graphicOverlay.add(labelGraphic);
  graphicOverlay.postInvalidate();
}
