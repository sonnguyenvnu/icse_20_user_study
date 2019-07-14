static void debugPrintLayoutState(LayoutState layoutState){
  if (!ENABLED) {
    return;
  }
  for (int i=0; i < layoutState.getMountableOutputCount(); i++) {
    final LayoutOutput output=layoutState.getMountableOutputAt(i);
    Log.d(TAG,"" + i + " [" + output.getId() + "] (" + output.getTransitionId() + ") host => (" + output.getHostMarker() + ")");
  }
}
