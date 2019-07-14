protected Drawable rotatedDrawable(CloseableStaticBitmap closeableStaticBitmap,Drawable drawable){
  if (!hasTransformableRotationAngle(closeableStaticBitmap) && !hasTransformableExifOrientation(closeableStaticBitmap)) {
    return drawable;
  }
 else {
    return new OrientedDrawable(drawable,closeableStaticBitmap.getRotationAngle(),closeableStaticBitmap.getExifOrientation());
  }
}
