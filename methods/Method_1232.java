@Override @Nullable public Drawable createDrawable(CloseableImage closeableImage){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("DefaultDrawableFactory#createDrawable");
    }
    if (closeableImage instanceof CloseableStaticBitmap) {
      CloseableStaticBitmap closeableStaticBitmap=(CloseableStaticBitmap)closeableImage;
      Drawable bitmapDrawable=new BitmapDrawable(mResources,closeableStaticBitmap.getUnderlyingBitmap());
      if (!hasTransformableRotationAngle(closeableStaticBitmap) && !hasTransformableExifOrientation(closeableStaticBitmap)) {
        return bitmapDrawable;
      }
 else {
        return new OrientedDrawable(bitmapDrawable,closeableStaticBitmap.getRotationAngle(),closeableStaticBitmap.getExifOrientation());
      }
    }
 else     if (mAnimatedDrawableFactory != null && mAnimatedDrawableFactory.supportsImageType(closeableImage)) {
      return mAnimatedDrawableFactory.createDrawable(closeableImage);
    }
    return null;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
