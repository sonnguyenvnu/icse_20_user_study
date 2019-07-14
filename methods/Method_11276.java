private Bitmap setBitmapSize(int iconId,float w){
  Bitmap bitmap=BitmapFactory.decodeResource(getContext().getResources(),iconId);
  float s=w * 1.0f / bitmap.getWidth();
  bitmap=Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth() * s),(int)(bitmap.getHeight() * s),true);
  return bitmap;
}
