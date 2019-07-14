@Override protected float getBackdropRatio(){
  return ViewUtils.isInPortait(getContext()) ? 0 : 2;
}
