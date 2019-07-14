@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull List<FirebaseVisionImageLabel> labels,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  Log.d(TAG,"cloud label size: " + labels.size());
  List<String> labelsStr=new ArrayList<>();
  for (int i=0; i < labels.size(); ++i) {
    FirebaseVisionImageLabel label=labels.get(i);
    Log.d(TAG,"cloud label: " + label);
    if (label.getText() != null) {
      labelsStr.add((label.getText()));
    }
  }
  CloudLabelGraphic cloudLabelGraphic=new CloudLabelGraphic(graphicOverlay,labelsStr);
  graphicOverlay.add(cloudLabelGraphic);
  graphicOverlay.postInvalidate();
}
