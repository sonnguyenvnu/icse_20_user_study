@Override public void requestDelayedModelBuild(int delayMs){
  if (!allowModelBuildRequests) {
    throw new IllegalStateException("You cannot call `requestModelBuild` directly. Call `setData` instead to trigger a " + "model refresh with new data.");
  }
  super.requestDelayedModelBuild(delayMs);
}
