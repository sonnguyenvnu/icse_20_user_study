@Override public T asTypeNode(Node anyNode){
  return tClass.isInstance(anyNode) ? tClass.cast(anyNode) : null;
}
