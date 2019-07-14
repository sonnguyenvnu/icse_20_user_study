protected Drawable circularDrawableWithBorder(CloseableStaticBitmap closeableStaticBitmap,BorderOptions borderOptions){
  CircularBorderBitmapDrawable drawable=new CircularBorderBitmapDrawable(mResources,closeableStaticBitmap.getUnderlyingBitmap());
  drawable.setBorder(borderOptions);
  return drawable;
}
