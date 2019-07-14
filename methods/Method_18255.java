private static LayoutOutput createDrawableLayoutOutput(Component component,LayoutState layoutState,InternalNode node,boolean hasHostView){
  long hostMarker=layoutState.mCurrentHostMarker;
  return createLayoutOutput(component,hostMarker,layoutState,node,false,IMPORTANT_FOR_ACCESSIBILITY_NO,layoutState.mShouldDuplicateParentState,hasHostView);
}
