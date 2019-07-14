@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  if (visibility == VISIBLE && drawBitmap == null) {
    try {
      drawBitmap=Bitmap.createBitmap(AndroidUtilities.dp(size),AndroidUtilities.dp(size),Bitmap.Config.ARGB_4444);
      bitmapCanvas=new Canvas(drawBitmap);
      checkBitmap=Bitmap.createBitmap(AndroidUtilities.dp(size),AndroidUtilities.dp(size),Bitmap.Config.ARGB_4444);
      checkCanvas=new Canvas(checkBitmap);
    }
 catch (    Throwable ignore) {
    }
  }
}
