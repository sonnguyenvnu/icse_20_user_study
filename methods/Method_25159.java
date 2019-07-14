@Override public V valueOfLocalVariable(LocalVariableNode node,V defaultValue){
  return valueOfAccessPath(AccessPath.fromLocalVariable(node),defaultValue);
}
