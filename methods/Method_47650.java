private void reallocateCache(){
  if (drawingCache != null)   drawingCache.recycle();
  drawingCache=Bitmap.createBitmap(diameter,diameter,Bitmap.Config.ARGB_8888);
  cacheCanvas=new Canvas(drawingCache);
}
