/** 
 * We expect that the lhs is a field and the rhs is an identifier, specifically a parameter to the method. We base our suggested fixes on this expectation. <p>Case 1: If lhs is a field and rhs is an identifier, find a method parameter of the same type and similar name and suggest it as the rhs. (Guess that they have misspelled the identifier.) <p>Case 2: If lhs is a field and rhs is not an identifier, find a method parameter of the same type and similar name and suggest it as the rhs. <p>Case 3: If lhs is not a field and rhs is an identifier, find a class field of the same type and similar name and suggest it as the lhs. <p>Case 4: Otherwise replace with literal meaning of functionality
 */
private Description describe(MethodInvocationTree methodInvocationTree,VisitorState state){
  ExpressionTree receiver=ASTHelpers.getReceiver(methodInvocationTree);
  List<? extends ExpressionTree> arguments=methodInvocationTree.getArguments();
  ExpressionTree argument;
  argument=arguments.size() == 2 ? arguments.get(1) : arguments.get(0);
  Description.Builder builder=buildDescription(methodInvocationTree);
  for (  Fix fix : buildFixes(methodInvocationTree,state,receiver,argument)) {
    builder.addFix(fix);
  }
  return builder.build();
}
