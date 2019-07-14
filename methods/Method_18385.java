/** 
 * @return bounds for a given LayoutOutput within its actual host, {@see getActualHostMarker} 
 */
private static void getActualBounds(LayoutOutput layoutOutput,LayoutState layoutState,Rect outRect){
  final long actualHostMarker=layoutOutput.getHostMarker();
  layoutOutput.getMountBounds(outRect);
  long hostMarker=layoutOutput.getHostMarker();
  while (hostMarker != actualHostMarker) {
    final LayoutOutput ancestor=layoutState.getLayoutOutput(hostMarker);
    ancestor.getMountBounds(sTempRect2);
    outRect.offset(sTempRect2.left,sTempRect2.top);
    hostMarker=ancestor.getHostMarker();
  }
}
