public Bitmap getResult(){
  if (state == null || !state.hasChanges() && state.getBaseRotation() < EPSILON && freeform) {
    return bitmap;
  }
  RectF cropRect=new RectF();
  areaView.getCropRect(cropRect);
  RectF sizeRect=new RectF(0,0,RESULT_SIDE,RESULT_SIDE);
  float w=scaleWidthToMaxSize(cropRect,sizeRect);
  int width=(int)Math.ceil(w);
  int height=(int)(Math.ceil(width / areaView.getAspectRatio()));
  Bitmap resultBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
  Matrix matrix=new Matrix();
  matrix.postTranslate(-state.getWidth() / 2,-state.getHeight() / 2);
  matrix.postRotate(state.getOrientation());
  state.getConcatMatrix(matrix);
  float scale=width / areaView.getCropWidth();
  matrix.postScale(scale,scale);
  matrix.postTranslate(width / 2,height / 2);
  new Canvas(resultBitmap).drawBitmap(bitmap,matrix,new Paint(FILTER_BITMAP_FLAG));
  return resultBitmap;
}
