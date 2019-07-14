@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull List<FirebaseVisionCloudLandmark> landmarks,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  Log.d(TAG,"cloud landmark size: " + landmarks.size());
  for (int i=0; i < landmarks.size(); ++i) {
    FirebaseVisionCloudLandmark landmark=landmarks.get(i);
    Log.d(TAG,"cloud landmark: " + landmark);
    CloudLandmarkGraphic cloudLandmarkGraphic=new CloudLandmarkGraphic(graphicOverlay,landmark);
    graphicOverlay.add(cloudLandmarkGraphic);
  }
  graphicOverlay.postInvalidate();
}
