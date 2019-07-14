private static LayoutOutput createHostLayoutOutput(LayoutState layoutState,InternalNode node){
  final HostComponent hostComponent=HostComponent.create();
  Component tailComponent=node.getTailComponent();
  if (tailComponent != null) {
    hostComponent.setCommonDynamicProps(tailComponent.getCommonDynamicProps());
  }
  long hostMarker=layoutState.isLayoutRoot(node) ? ROOT_HOST_ID : layoutState.mCurrentHostMarker;
  final LayoutOutput hostOutput=createLayoutOutput(hostComponent,hostMarker,layoutState,node,false,node.getImportantForAccessibility(),node.isDuplicateParentStateEnabled(),false);
  ViewNodeInfo viewNodeInfo=hostOutput.getViewNodeInfo();
  if (viewNodeInfo != null) {
    if (node.hasStateListAnimatorResSet()) {
      viewNodeInfo.setStateListAnimatorRes(node.getStateListAnimatorRes());
    }
 else {
      viewNodeInfo.setStateListAnimator(node.getStateListAnimator());
    }
  }
  return hostOutput;
}
