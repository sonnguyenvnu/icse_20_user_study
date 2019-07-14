/** 
 * Attach comments to nodes on arguments of method calls. Calls such as  {@code test(param1 /* c1*&#47;, /* c2 *&#47; param2)} will attach the comment c1 to {@code param1} and the comment c2to  {@code param2}. <p>Warning: this is expensive to compute as it involves re-tokenizing the source for this node <p>Currently this method will only tokenize the source code of the method call itself. However, the source positions in the returned  {@code Comment} objects are adjusted so that they arerelative to the whole file.
 */
public static ImmutableList<Commented<ExpressionTree>> findCommentsForArguments(MethodInvocationTree methodInvocationTree,VisitorState state){
  int startPosition=state.getEndPosition(methodInvocationTree.getMethodSelect());
  return findCommentsForArguments(methodInvocationTree,methodInvocationTree.getArguments(),startPosition,state);
}
