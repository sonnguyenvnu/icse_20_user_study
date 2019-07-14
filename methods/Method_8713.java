@Keep @Override public void setAlpha(float alpha){
  ColorDrawable colorDrawable=(ColorDrawable)getBackground();
  colorDrawable.setAlpha((int)(0xc0 * alpha));
  invalidate();
}
