@GuardedBy("this") private boolean areInputsFinished(ValueNode node){
  for (  ValueNode input : node.getAllInputs()) {
    final NodeState nodeState=mNodeStates.get(input);
    if (!nodeState.isFinished) {
      return false;
    }
  }
  return true;
}
