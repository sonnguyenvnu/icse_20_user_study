static void applyLoopCount(final int loopCount,final Drawable drawable){
  if (drawable instanceof GifDrawable) {
    ((GifDrawable)drawable).setLoopCount(loopCount);
  }
}
