@Override protected boolean onLevelChange(int level){
  return mDrawable != null && mDrawable.setLevel(level);
}
