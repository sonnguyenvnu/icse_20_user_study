@Override public void setImageResource(int resId){
  Drawable drawable=getResources().getDrawable(resId);
  if (mIcon != drawable) {
    mIcon=drawable;
    updateBackground();
  }
}
