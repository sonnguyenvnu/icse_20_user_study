@Override Nullness visitAssignment(AssignmentNode node,SubNodeValues inputs,Updates updates){
  Nullness value=inputs.valueOfSubNode(node.getExpression());
  Node target=node.getTarget();
  if (target instanceof LocalVariableNode) {
    updates.set((LocalVariableNode)target,value);
  }
  if (target instanceof ArrayAccessNode) {
    setNonnullIfTrackable(updates,((ArrayAccessNode)target).getArray());
  }
  if (target instanceof FieldAccessNode) {
    FieldAccessNode fieldAccess=(FieldAccessNode)target;
    if (!fieldAccess.isStatic()) {
      setNonnullIfTrackable(updates,fieldAccess.getReceiver());
    }
    updates.set(fieldAccess,value);
  }
  return value;
}
