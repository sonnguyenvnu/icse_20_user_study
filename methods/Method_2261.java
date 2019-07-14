protected Drawable roundedDrawableWithBorder(CloseableStaticBitmap closeableStaticBitmap,BorderOptions borderOptions,@Nullable RoundingOptions roundingOptions){
  if (roundingOptions != null) {
    if (roundingOptions.isCircular()) {
      return circularDrawableWithBorder(closeableStaticBitmap,borderOptions);
    }
 else {
      return roundedCornerDrawableWithBorder(closeableStaticBitmap,borderOptions,roundingOptions);
    }
  }
 else {
    return squareDrawableWithBorder(closeableStaticBitmap,borderOptions);
  }
}
