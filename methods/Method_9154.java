@Override protected void onBoundsChange(Rect bounds){
  int size=Math.min(bounds.width(),bounds.height());
  if (size <= 0) {
    shadowBitmap=null;
    return;
  }
  shadowBitmap=Bitmap.createBitmap(size,size,Bitmap.Config.ALPHA_8);
  Canvas c=new Canvas(shadowBitmap);
  Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
  p.setShadowLayer(AndroidUtilities.dp(3.33333f),0,AndroidUtilities.dp(0.666f),0xFFFFFFFF);
  c.drawCircle(size / 2,size / 2,size / 2 - AndroidUtilities.dp(4),p);
}
