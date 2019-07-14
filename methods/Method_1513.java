private void maybeApplyTransformation(@Nullable BitmapTransformation transformation,CloseableReference<Bitmap> bitmapReference){
  if (transformation == null) {
    return;
  }
  Bitmap bitmap=bitmapReference.get();
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1 && transformation.modifiesTransparency()) {
    bitmap.setHasAlpha(true);
  }
  transformation.transform(bitmap);
}
