/** 
 * Attach comments to nodes on arguments of constructor calls. Calls such as  {@code new Test(param1 /* c1 *&#47;, /* c2 *&#47; param2)} will attach the comment c1 to {@code param1} and thecomment c2 to  {@code param2}. <p>Warning: this is expensive to compute as it involves re-tokenizing the source for this node. <p>Currently this method will only tokenize the source code of the method call itself. However, the source positions in the returned  {@code Comment} objects are adjusted so that they arerelative to the whole file.
 */
public static ImmutableList<Commented<ExpressionTree>> findCommentsForArguments(NewClassTree newClassTree,VisitorState state){
  int startPosition=((JCTree)newClassTree).getStartPosition();
  return findCommentsForArguments(newClassTree,newClassTree.getArguments(),startPosition,state);
}
