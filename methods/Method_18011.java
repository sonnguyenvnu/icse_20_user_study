@GuardedBy("this") private void notifyFinishedBindings(){
  for (int i=mBindings.size() - 1; i >= 0; i--) {
    final GraphBinding binding=mBindings.get(i);
    boolean allAreFinished=true;
    final ArrayList<ValueNode> nodesToCheck=binding.getAllNodes();
    for (int j=0, nodesSize=nodesToCheck.size(); j < nodesSize; j++) {
      final NodeState nodeState=mNodeStates.get(nodesToCheck.get(j));
      if (!nodeState.isFinished) {
        allAreFinished=false;
        break;
      }
    }
    if (allAreFinished) {
      binding.notifyNodesHaveFinished();
    }
  }
}
