public static SNode cast(SNode node,SAbstractConcept castTo){
  if (node == null) {
    return null;
  }
  if (!(SNodeOperations.isInstanceOf(node,castTo))) {
    String message="Can't cast node: " + node.getNodeId().toString() + ", concept: " + node.getConcept().getQualifiedName() + " to concept: " + castTo;
    if (ourCastExceptionsEnabled) {
      throw new NodeCastException(message);
    }
 else {
      LOG.warning(message);
    }
  }
  return node;
}
