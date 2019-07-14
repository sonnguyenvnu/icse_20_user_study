@Override public O asOperationNode(Node anyNode){
  return oClass.isInstance(anyNode) ? oClass.cast(anyNode) : null;
}
