@Override @NonNull public ControllerChangeHandler copy(){
  return new VerticalChangeHandler(getAnimationDuration(),removesFromViewOnPush());
}
