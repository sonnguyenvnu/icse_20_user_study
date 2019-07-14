private Point transposePoint(PointF point,Bitmap sourceBitmap,Size targetSize,boolean sideward){
  float bitmapW=sideward ? sourceBitmap.getHeight() : sourceBitmap.getWidth();
  float bitmapH=sideward ? sourceBitmap.getWidth() : sourceBitmap.getHeight();
  return new Point(targetSize.width * point.x / bitmapW,targetSize.height * point.y / bitmapH);
}
