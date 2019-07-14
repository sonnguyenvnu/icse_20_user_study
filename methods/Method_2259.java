/** 
 * We handle the given bitmap and return a Drawable ready for being displayed: If rounding is set, the image will be rounded, if a border if set, the border will be applied and finally, the image will be rotated if required. <p>Bitmap -> border -> rounded corners -> RoundedBitmapDrawable (since bitmap is square) -> fully circular -> CircularBorderBitmapDrawable (bitmap is circular) -> square image -> RoundedBitmapDrawable (for border support) -> no border -> rounded corners -> RoundedBitmapDrawable (since bitmap is square) -> fully circular -> BitmapDrawable (since bitmap is circular) -> square image -> BitmapDrawable <p>If needed, the resulting drawable is rotated using OrientedDrawable.
 * @param closeableStaticBitmap the image to handle
 * @param imageOptions display options for the given image
 * @return the drawable to display
 */
protected Drawable handleCloseableStaticBitmap(CloseableStaticBitmap closeableStaticBitmap,ImageOptions imageOptions){
  RoundingOptions roundingOptions=imageOptions.getRoundingOptions();
  BorderOptions borderOptions=imageOptions.getBorderOptions();
  if (borderOptions != null && borderOptions.width > 0) {
    return rotatedDrawable(closeableStaticBitmap,roundedDrawableWithBorder(closeableStaticBitmap,borderOptions,roundingOptions));
  }
 else {
    return rotatedDrawable(closeableStaticBitmap,roundedDrawableWithoutBorder(closeableStaticBitmap,roundingOptions));
  }
}
