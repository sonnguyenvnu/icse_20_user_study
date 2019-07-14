@Override protected void releaseDrawable(@Nullable Drawable drawable){
  if (drawable instanceof DrawableWithCaches) {
    ((DrawableWithCaches)drawable).dropCaches();
  }
}
