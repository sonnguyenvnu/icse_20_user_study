@Override public Description matchAssignment(AssignmentTree tree,VisitorState state){
  String formalName=extractArgumentName(tree.getVariable());
  if (formalName != null) {
    check(formalName,tree.getExpression(),state);
  }
  return ANY_MATCHES_WERE_ALREADY_REPORTED;
}
