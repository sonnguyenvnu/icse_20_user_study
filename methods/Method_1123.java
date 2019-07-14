private void drawDrawableWithAlpha(Canvas canvas,Drawable drawable,int alpha){
  if (drawable != null && alpha > 0) {
    mPreventInvalidateCount++;
    drawable.mutate().setAlpha(alpha);
    mPreventInvalidateCount--;
    drawable.draw(canvas);
  }
}
