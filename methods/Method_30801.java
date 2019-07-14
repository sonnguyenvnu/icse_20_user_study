private float View_getScrollFactor(){
  if (mView_verticalScrollFactor == Float.MIN_VALUE) {
    Context context=getContext();
    TypedValue outValue=new TypedValue();
    if (context.getTheme().resolveAttribute(android.R.attr.listPreferredItemHeight,outValue,true)) {
      mView_verticalScrollFactor=outValue.getDimension(context.getResources().getDisplayMetrics());
    }
 else {
      throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
    }
  }
  return mView_verticalScrollFactor;
}
