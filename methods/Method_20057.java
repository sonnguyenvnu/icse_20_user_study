@Override protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull FirebaseVisionDocumentText text,@NonNull FrameMetadata frameMetadata,@NonNull GraphicOverlay graphicOverlay){
  graphicOverlay.clear();
  Log.d(TAG,"detected text is: " + text.getText());
  List<FirebaseVisionDocumentText.Block> blocks=text.getBlocks();
  for (int i=0; i < blocks.size(); i++) {
    List<FirebaseVisionDocumentText.Paragraph> paragraphs=blocks.get(i).getParagraphs();
    for (int j=0; j < paragraphs.size(); j++) {
      List<FirebaseVisionDocumentText.Word> words=paragraphs.get(j).getWords();
      for (int l=0; l < words.size(); l++) {
        List<FirebaseVisionDocumentText.Symbol> symbols=words.get(l).getSymbols();
        for (int m=0; m < symbols.size(); m++) {
          CloudDocumentTextGraphic cloudDocumentTextGraphic=new CloudDocumentTextGraphic(graphicOverlay,symbols.get(m));
          graphicOverlay.add(cloudDocumentTextGraphic);
        }
      }
    }
  }
  graphicOverlay.postInvalidate();
}
