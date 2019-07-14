@GuardedBy("this") private void registerNodes(GraphBinding binding){
  final ArrayList<ValueNode> nodes=binding.getAllNodes();
  for (int i=0, size=nodes.size(); i < size; i++) {
    final ValueNode node=nodes.get(i);
    final NodeState nodeState=mNodeStates.get(node);
    if (nodeState != null) {
      nodeState.refCount++;
    }
 else {
      final NodeState newState=new NodeState();
      newState.refCount=1;
      mNodeStates.put(node,newState);
    }
  }
}
