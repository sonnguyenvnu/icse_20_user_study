static void debugPrintAnimationLockedIndices(LayoutState layoutState,int[] animationLockedIndices){
  if (!ENABLED) {
    return;
  }
  if (animationLockedIndices != null) {
    for (int i=0; i < animationLockedIndices.length; i++) {
      final LayoutOutput output=layoutState.getMountableOutputAt(i);
      Log.d(TAG,"" + i + " [" + output.getId() + "] (" + output.getTransitionId() + ") host => (" + output.getHostMarker() + "), locked ref count: " + animationLockedIndices[i]);
    }
  }
}
