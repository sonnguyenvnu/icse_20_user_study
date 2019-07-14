private static boolean needsHostViewForTransition(InternalNode node){
  return !TextUtils.isEmpty(node.getTransitionKey()) && !isMountViewSpec(node.getTailComponent());
}
