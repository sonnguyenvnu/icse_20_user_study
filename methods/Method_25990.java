static String buildReplacement(MethodInvocationTree tree,VisitorState state){
  Operand lhs=classify((JCTree)ASTHelpers.getReceiver(tree.getMethodSelect()),state);
  Operand rhs=classify((JCTree)Iterables.getOnlyElement(tree.getArguments()),state);
  if (lhs.kind() == Kind.GET_CLASS && rhs.kind() == Kind.LITERAL) {
    return String.format("%s instanceof %s",lhs.value(),rhs.value());
  }
  if (lhs.kind() == Kind.GET_CLASS && rhs.kind() == Kind.GET_CLASS) {
    return String.format("%s.getClass().isInstance(%s)",rhs.value(),lhs.value());
  }
  if (lhs.kind() == Kind.LITERAL && rhs.kind() == Kind.LITERAL) {
    return String.format("%s.class == Class.class",rhs.value());
  }
  if (lhs.kind() == Kind.LITERAL && rhs.kind() == Kind.GET_CLASS) {
    return String.format("%s instanceof %s",rhs.value(),lhs.value());
  }
  if (rhs.kind() == Kind.GET_CLASS) {
    return String.format("%s.isInstance(%s)",lhs.source(),rhs.value());
  }
  if (lhs.kind() == Kind.GET_CLASS) {
    return String.format("%s.isInstance(%s)",rhs.source(),lhs.value());
  }
  return String.format("%s.isAssignableFrom(%s)",rhs.source(),lhs.source());
}
