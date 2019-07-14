/** 
 * Whether this is a chain of method invocations terminating in a new proto or collection builder.
 */
private static boolean newFluentChain(ExpressionTree tree,VisitorState state){
  while (tree instanceof MethodInvocationTree && FLUENT_CHAIN.matches(tree,state)) {
    tree=getReceiver(tree);
  }
  return tree != null && FLUENT_CONSTRUCTOR.matches(tree,state);
}
