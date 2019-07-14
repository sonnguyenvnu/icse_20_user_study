/** 
 * Matches explicit "return null" statements in methods annotated with  {@code @Provides} but not{@code @Nullable}. Suggests either annotating the method with  {@code @Nullable} or throwing a{@link RuntimeException} instead.
 */
@Override public Description matchReturn(ReturnTree returnTree,VisitorState state){
  ExpressionTree returnExpression=returnTree.getExpression();
  if (returnExpression == null || returnExpression.getKind() != Kind.NULL_LITERAL) {
    return Description.NO_MATCH;
  }
  TreePath path=state.getPath();
  MethodTree enclosingMethod=null;
  while (true) {
    if (path == null || path.getLeaf() instanceof LambdaExpressionTree) {
      return Description.NO_MATCH;
    }
 else     if (path.getLeaf() instanceof MethodTree) {
      enclosingMethod=(MethodTree)path.getLeaf();
      break;
    }
 else {
      path=path.getParentPath();
    }
  }
  MethodSymbol enclosingMethodSym=ASTHelpers.getSymbol(enclosingMethod);
  if (enclosingMethodSym == null) {
    return Description.NO_MATCH;
  }
  if (!ASTHelpers.hasAnnotation(enclosingMethodSym,"dagger.Provides",state) || ASTHelpers.hasDirectAnnotationWithSimpleName(enclosingMethodSym,"Nullable")) {
    return Description.NO_MATCH;
  }
  Fix addNullableFix=SuggestedFix.builder().prefixWith(enclosingMethod,"@Nullable\n").addImport("javax.annotation.Nullable").build();
  CatchTree enclosingCatch=ASTHelpers.findEnclosingNode(state.getPath(),CatchTree.class);
  if (enclosingCatch == null) {
    Fix throwRuntimeExceptionFix=SuggestedFix.replace(returnTree,"throw new RuntimeException();");
    return buildDescription(returnTree).addFix(addNullableFix).addFix(throwRuntimeExceptionFix).build();
  }
 else {
    String replacement=String.format("throw new RuntimeException(%s);",enclosingCatch.getParameter().getName());
    Fix throwRuntimeExceptionFix=SuggestedFix.replace(returnTree,replacement);
    return buildDescription(returnTree).addFix(throwRuntimeExceptionFix).addFix(addNullableFix).build();
  }
}
