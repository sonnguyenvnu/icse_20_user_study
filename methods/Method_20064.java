@Override protected Task<List<FirebaseVisionFace>> detectInImage(FirebaseVisionImage image){
  return detector.detectInImage(image);
}
