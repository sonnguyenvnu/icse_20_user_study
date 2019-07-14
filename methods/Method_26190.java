private static Fix adjustHashCodeCall(MethodInvocationTree tree,VisitorState state){
  String argumentClass=state.getTypes().boxedTypeOrType(ASTHelpers.getType(tree.getArguments().get(0))).tsym.getSimpleName().toString();
  return SuggestedFix.builder().prefixWith(tree,argumentClass + ".hashCode(").replace(tree,state.getSourceForNode(tree.getArguments().get(0))).postfixWith(tree,")").build();
}
