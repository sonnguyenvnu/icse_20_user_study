@Override @RequiresApi(Build.VERSION_CODES.M) public boolean onLayoutDirectionChanged(int layoutDirection){
  return mDrawable != null && mDrawable.setLayoutDirection(layoutDirection);
}
