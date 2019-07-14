private static ImmutableList<SuggestedFix> buildUnusedVarFixes(Symbol varSymbol,List<TreePath> usagePaths,VisitorState state){
  if (ASTHelpers.hasDirectAnnotationWithSimpleName(varSymbol,"Inject")) {
    return ImmutableList.of();
  }
  ElementKind varKind=varSymbol.getKind();
  boolean encounteredSideEffects=false;
  SuggestedFix.Builder fix=SuggestedFix.builder().setShortDescription("remove unused variable");
  SuggestedFix.Builder removeSideEffectsFix=SuggestedFix.builder().setShortDescription("remove unused variable and any side effects");
  for (  TreePath usagePath : usagePaths) {
    StatementTree statement=(StatementTree)usagePath.getLeaf();
    if (statement.getKind() == Kind.VARIABLE) {
      if (getSymbol(statement).getKind() == ElementKind.PARAMETER) {
        continue;
      }
      VariableTree variableTree=(VariableTree)statement;
      ExpressionTree initializer=variableTree.getInitializer();
      if (hasSideEffect(initializer) && TOP_LEVEL_EXPRESSIONS.contains(initializer.getKind())) {
        encounteredSideEffects=true;
        if (varKind == ElementKind.FIELD) {
          String newContent=String.format("%s{ %s; }",varSymbol.isStatic() ? "static " : "",state.getSourceForNode(initializer));
          fix.merge(SuggestedFixes.replaceIncludingComments(usagePath,newContent,state));
          removeSideEffectsFix.replace(statement,"");
        }
 else {
          fix.replace(statement,String.format("%s;",state.getSourceForNode(initializer)));
          removeSideEffectsFix.replace(statement,"");
        }
      }
 else       if (isEnhancedForLoopVar(usagePath)) {
        String modifiers=nullToEmpty(variableTree.getModifiers() == null ? null : state.getSourceForNode(variableTree.getModifiers()));
        String newContent=String.format("%s%s unused",modifiers.isEmpty() ? "" : (modifiers + " "),variableTree.getType());
        fix.replace(variableTree,newContent);
        removeSideEffectsFix.replace(variableTree,newContent);
      }
 else {
        String replacement=needsBlock(usagePath) ? "{}" : "";
        fix.merge(SuggestedFixes.replaceIncludingComments(usagePath,replacement,state));
        removeSideEffectsFix.merge(SuggestedFixes.replaceIncludingComments(usagePath,replacement,state));
      }
      continue;
    }
 else     if (statement.getKind() == Kind.EXPRESSION_STATEMENT) {
      JCTree tree=(JCTree)((ExpressionStatementTree)statement).getExpression();
      if (tree instanceof CompoundAssignmentTree) {
        if (hasSideEffect(((CompoundAssignmentTree)tree).getExpression())) {
          SuggestedFix replacement=SuggestedFix.replace(tree.getStartPosition(),((JCAssignOp)tree).getExpression().getStartPosition(),"");
          fix.merge(replacement);
          removeSideEffectsFix.merge(replacement);
          continue;
        }
      }
 else       if (tree instanceof AssignmentTree) {
        if (hasSideEffect(((AssignmentTree)tree).getExpression())) {
          encounteredSideEffects=true;
          fix.replace(tree.getStartPosition(),((JCAssign)tree).getExpression().getStartPosition(),"");
          removeSideEffectsFix.replace(statement,"");
          continue;
        }
      }
    }
    String replacement=needsBlock(usagePath) ? "{}" : "";
    fix.replace(statement,replacement);
    removeSideEffectsFix.replace(statement,replacement);
  }
  return encounteredSideEffects ? ImmutableList.of(removeSideEffectsFix.build(),fix.build()) : ImmutableList.of(fix.build());
}
