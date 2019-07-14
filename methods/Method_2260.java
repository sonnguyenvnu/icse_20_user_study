protected Drawable roundedDrawableWithoutBorder(CloseableStaticBitmap closeableStaticBitmap,@Nullable RoundingOptions roundingOptions){
  if (roundingOptions != null && !roundingOptions.isCircular()) {
    return roundedCornerDrawableWithBorder(closeableStaticBitmap,null,roundingOptions);
  }
  return new BitmapDrawable(mResources,closeableStaticBitmap.getUnderlyingBitmap());
}
