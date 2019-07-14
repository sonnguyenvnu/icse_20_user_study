protected Drawable roundedCornerDrawableWithBorder(CloseableStaticBitmap closeableStaticBitmap,@Nullable BorderOptions borderOptions,@Nullable RoundingOptions roundingOptions){
  RoundedBitmapDrawable roundedBitmapDrawable=new RoundedBitmapDrawable(mResources,closeableStaticBitmap.getUnderlyingBitmap());
  if (roundingOptions != null) {
    float[] radii=roundingOptions.getCornerRadii();
    if (radii != null) {
      roundedBitmapDrawable.setRadii(radii);
    }
 else {
      roundedBitmapDrawable.setRadius(roundingOptions.getCornerRadius());
    }
  }
  if (borderOptions != null) {
    roundedBitmapDrawable.setBorder(borderOptions.color,borderOptions.width);
  }
  return roundedBitmapDrawable;
}
