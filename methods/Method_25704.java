/** 
 * Find all the other calls to  {@code calledMethod} within the method (or class) which enclosedthe original call. <p>We are interested in two different cases: 1) where there are other calls to the method we are calling; 2) declarations of the method we are calling (this catches the case when there is a recursive call with the arguments correctly swapped).
 * @param calledMethod is the method call we are analysing for swaps
 * @param currentNode is the tree node the method call occurred at
 * @param state is the current visitor state
 * @return a list containing argument lists for each call found
 */
private static List<List<Parameter>> findArgumentsForOtherInstances(MethodSymbol calledMethod,Tree currentNode,VisitorState state){
  Tree enclosingNode=ASTHelpers.findEnclosingNode(state.getPath(),MethodTree.class);
  if (enclosingNode == null) {
    enclosingNode=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class);
  }
  if (enclosingNode == null) {
    return ImmutableList.of();
  }
  ImmutableList.Builder<List<Parameter>> resultBuilder=ImmutableList.builder();
  new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree methodInvocationTree,    Void aVoid){
      addToResult(ASTHelpers.getSymbol(methodInvocationTree),methodInvocationTree);
      return super.visitMethodInvocation(methodInvocationTree,aVoid);
    }
    @Override public Void visitNewClass(    NewClassTree newClassTree,    Void aVoid){
      addToResult(ASTHelpers.getSymbol(newClassTree),newClassTree);
      return super.visitNewClass(newClassTree,aVoid);
    }
    @Override public Void visitMethod(    MethodTree methodTree,    Void aVoid){
      MethodSymbol methodSymbol=ASTHelpers.getSymbol(methodTree);
      if (methodSymbol != null) {
        addToResult(methodSymbol,methodTree);
        for (        MethodSymbol superSymbol : ASTHelpers.findSuperMethods(methodSymbol,state.getTypes())) {
          addToResult(superSymbol,methodTree);
        }
      }
      return super.visitMethod(methodTree,aVoid);
    }
    private void addToResult(    MethodSymbol foundSymbol,    Tree tree){
      if (foundSymbol != null && Objects.equals(calledMethod,foundSymbol) && !currentNode.equals(tree)) {
        resultBuilder.add(createParameterList(tree));
      }
    }
    private ImmutableList<Parameter> createParameterList(    Tree tree){
      if (tree instanceof MethodInvocationTree) {
        return Parameter.createListFromExpressionTrees(((MethodInvocationTree)tree).getArguments());
      }
      if (tree instanceof NewClassTree) {
        return Parameter.createListFromExpressionTrees(((NewClassTree)tree).getArguments());
      }
      if (tree instanceof MethodTree) {
        return Parameter.createListFromVariableTrees(((MethodTree)tree).getParameters());
      }
      return ImmutableList.of();
    }
  }
.scan(enclosingNode,null);
  return resultBuilder.build();
}
