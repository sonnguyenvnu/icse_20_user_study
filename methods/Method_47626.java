private void initCache(int width,int height){
  if (drawingCache != null)   drawingCache.recycle();
  drawingCache=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
  cacheCanvas=new Canvas(drawingCache);
}
