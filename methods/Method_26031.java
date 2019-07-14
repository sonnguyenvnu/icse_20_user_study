/** 
 * Gets the argument types, excluding the message argument if present. 
 */
private List<Type> getArgumentTypesWithoutMessage(MethodInvocationTree methodInvocationTree,VisitorState state){
  List<Type> argumentTypes=new ArrayList<>();
  for (  ExpressionTree argument : methodInvocationTree.getArguments()) {
    JCTree tree=(JCTree)argument;
    argumentTypes.add(tree.type);
  }
  removeMessageArgumentIfPresent(state,argumentTypes);
  return argumentTypes;
}
