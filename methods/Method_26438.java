/** 
 * Matches expressions that look like they should be considered constant, i.e.  {@code ImmutableList.of(1, 2)},  {@code Long.valueOf(10L)}.
 */
static boolean isConstantCreator(ExpressionTree tree,VisitorState state){
  List<? extends ExpressionTree> arguments=tree.accept(new SimpleTreeVisitor<List<? extends ExpressionTree>,Void>(){
    @Override public List<? extends ExpressionTree> visitNewClass(    NewClassTree node,    Void unused){
      return node.getArguments();
    }
    @Override public List<? extends ExpressionTree> visitMethodInvocation(    MethodInvocationTree node,    Void unused){
      MethodSymbol symbol=ASTHelpers.getSymbol(node);
      if (symbol == null || !symbol.isStatic()) {
        return null;
      }
      return node.getArguments();
    }
  }
,null);
  return arguments != null && arguments.stream().allMatch(argument -> ASTHelpers.constValue(argument) != null);
}
