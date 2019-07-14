@Override protected float getBackdropRatio(){
  return ViewUtils.isInPortait(getContext()) ? 1 : 2;
}
