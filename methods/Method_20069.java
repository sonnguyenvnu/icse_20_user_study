@Override protected Task<List<FirebaseVisionImageLabel>> detectInImage(FirebaseVisionImage image){
  return detector.processImage(image);
}
