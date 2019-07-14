private void drawBitmapOverLandmarkPosition(Canvas canvas,FirebaseVisionFace face,int landmarkID){
  FirebaseVisionFaceLandmark landmark=face.getLandmark(landmarkID);
  if (landmark == null) {
    return;
  }
  FirebaseVisionPoint point=landmark.getPosition();
  if (overlayBitmap != null) {
    float imageEdgeSizeBasedOnFaceSize=(face.getBoundingBox().width() / 4.0f);
    int left=(int)(translateX(point.getX()) - imageEdgeSizeBasedOnFaceSize);
    int top=(int)(translateY(point.getY()) - imageEdgeSizeBasedOnFaceSize);
    int right=(int)(translateX(point.getX()) + imageEdgeSizeBasedOnFaceSize);
    int bottom=(int)(translateY(point.getY()) + imageEdgeSizeBasedOnFaceSize);
    canvas.drawBitmap(overlayBitmap,null,new Rect(left,top,right,bottom),null);
  }
}
