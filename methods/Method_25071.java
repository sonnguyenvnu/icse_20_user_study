@Override public void setImageDrawable(Drawable drawable){
  if (mIcon != drawable) {
    mIcon=drawable;
    updateBackground();
  }
}
