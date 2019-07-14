@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull FirebaseVisionText results,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  if (originalCameraImage != null) {
    CameraImageGraphic imageGraphic=new CameraImageGraphic(graphicOverlay,originalCameraImage);
    graphicOverlay.add(imageGraphic);
  }
  List<FirebaseVisionText.TextBlock> blocks=results.getTextBlocks();
  for (int i=0; i < blocks.size(); i++) {
    List<FirebaseVisionText.Line> lines=blocks.get(i).getLines();
    for (int j=0; j < lines.size(); j++) {
      List<FirebaseVisionText.Element> elements=lines.get(j).getElements();
      for (int k=0; k < elements.size(); k++) {
        GraphicOverlay.Graphic textGraphic=new TextGraphic(graphicOverlay,elements.get(k));
        graphicOverlay.add(textGraphic);
      }
    }
  }
  graphicOverlay.postInvalidate();
}
