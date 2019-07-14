@Override public void process(Bitmap bitmap,final GraphicOverlay graphicOverlay){
  detectInVisionImage(null,FirebaseVisionImage.fromBitmap(bitmap),null,graphicOverlay);
}
