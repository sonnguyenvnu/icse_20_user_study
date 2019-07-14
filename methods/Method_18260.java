private static LayoutOutput addDrawableComponent(InternalNode node,LayoutState layoutState,@Nullable LayoutOutput recycle,ComparableDrawable drawable,@OutputUnitType int type,boolean matchHostBoundsTransitions){
  final Component drawableComponent=DrawableComponent.create(drawable);
  drawableComponent.setScopedContext(ComponentContext.withComponentScope(node.getContext(),drawableComponent));
  final boolean isOutputUpdated;
  if (recycle != null) {
    isOutputUpdated=!drawableComponent.shouldComponentUpdate(recycle.getComponent(),drawableComponent);
  }
 else {
    isOutputUpdated=false;
  }
  final long previousId=recycle != null ? recycle.getId() : -1;
  final LayoutOutput output=addDrawableLayoutOutput(drawableComponent,layoutState,node,type,previousId,isOutputUpdated,matchHostBoundsTransitions);
  maybeAddLayoutOutputToAffinityGroup(layoutState.mCurrentLayoutOutputAffinityGroup,type,output);
  return output;
}
