@Override public final void requestModelBuild(){
  if (!insideSetModels) {
    throw new IllegalEpoxyUsage("You cannot call `requestModelBuild` directly. Call `setModels` instead.");
  }
  super.requestModelBuild();
}
