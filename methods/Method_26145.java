@Override public Description matchCompoundAssignment(CompoundAssignmentTree tree,VisitorState state){
  String message=identifyBadCast(getType(tree.getVariable()),getType(tree.getExpression()),state.getTypes());
  if (message == null) {
    return Description.NO_MATCH;
  }
  Optional<Fix> fix=rewriteCompoundAssignment(tree,state);
  if (!fix.isPresent()) {
    return Description.NO_MATCH;
  }
  return buildDescription(tree).addFix(fix.get()).setMessage(message).build();
}
