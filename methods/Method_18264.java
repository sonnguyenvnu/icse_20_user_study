/** 
 * If we have an interactive LayoutSpec or a MountSpec Drawable, we need to insert an HostComponent in the Outputs such as it will be used as a HostView at Mount time. View MountSpec are not allowed.
 * @return The position the HostLayoutOutput was inserted.
 */
private static int addHostLayoutOutput(InternalNode node,LayoutState layoutState,DiffNode diffNode){
  final Component component=node.getTailComponent();
  if (isMountViewSpec(component) && !layoutState.isLayoutRoot(node)) {
    throw new IllegalArgumentException("We shouldn't insert a host as a parent of a View");
  }
  final LayoutOutput hostLayoutOutput=createHostLayoutOutput(layoutState,node);
  addMountableOutput(layoutState,hostLayoutOutput);
  final int hostOutputPosition=layoutState.mMountableOutputs.size() - 1;
  if (diffNode != null) {
    diffNode.setHost(hostLayoutOutput);
  }
  calculateAndSetHostOutputIdAndUpdateState(node,hostLayoutOutput,layoutState);
  addLayoutOutputIdToPositionsMap(layoutState.mOutputsIdToPositionMap,hostLayoutOutput,hostOutputPosition);
  maybeAddLayoutOutputToAffinityGroup(layoutState.mCurrentLayoutOutputAffinityGroup,OutputUnitType.HOST,hostLayoutOutput);
  return hostOutputPosition;
}
