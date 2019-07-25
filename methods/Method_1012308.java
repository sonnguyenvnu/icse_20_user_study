@Override public Badge stroke(int color,float width,boolean isDpValue){
  mColorBackgroundBorder=color;
  mBackgroundBorderWidth=isDpValue ? DensityUtils.dp2px(getContext(),width) : width;
  invalidate();
  return this;
}
