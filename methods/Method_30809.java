@Override public int getSize(@NonNull Paint paint,CharSequence text,int start,int end,@Nullable Paint.FontMetricsInt fm){
  if (fm != null) {
    int oldTop=fm.top;
    int oldBottom=fm.bottom;
    paint.getFontMetricsInt(fm);
    topFix=fm.top - oldTop;
    bottomFix=fm.bottom - oldBottom;
    int height=fm.descent - fm.ascent;
    int width;
    if (mDrawable.getIntrinsicHeight() > 0) {
      width=Math.round((float)height / mDrawable.getIntrinsicHeight() * mDrawable.getIntrinsicWidth());
    }
 else {
      width=height;
    }
    mDrawable.setBounds(0,0,width,height);
    fm.ascent=fm.top;
    fm.descent=fm.bottom;
  }
  return mDrawable.getBounds().right;
}
