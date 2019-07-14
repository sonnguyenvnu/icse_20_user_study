@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull FirebaseVisionText text,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  if (text == null) {
    return;
  }
  List<FirebaseVisionText.TextBlock> blocks=text.getTextBlocks();
  for (int i=0; i < blocks.size(); i++) {
    List<FirebaseVisionText.Line> lines=blocks.get(i).getLines();
    for (int j=0; j < lines.size(); j++) {
      List<FirebaseVisionText.Element> elements=lines.get(j).getElements();
      for (int l=0; l < elements.size(); l++) {
        CloudTextGraphic cloudTextGraphic=new CloudTextGraphic(graphicOverlay,elements.get(l));
        graphicOverlay.add(cloudTextGraphic);
      }
    }
  }
  graphicOverlay.postInvalidate();
}
