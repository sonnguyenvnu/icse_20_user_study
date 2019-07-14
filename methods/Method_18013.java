@GuardedBy("this") private void unregisterNodes(GraphBinding binding){
  final ArrayList<ValueNode> nodes=binding.getAllNodes();
  for (int i=0, size=nodes.size(); i < size; i++) {
    final ValueNode node=nodes.get(i);
    final NodeState nodeState=mNodeStates.get(node);
    nodeState.refCount--;
    if (nodeState.refCount == 0) {
      mNodeStates.remove(node);
    }
  }
}
