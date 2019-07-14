private static SuggestedFix applySimpleFix(String immutableType,ExpressionTree expression,VisitorState state){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  fix.replace(expression,String.format("%s.copyOf(%s)",qualifyType(state,fix,immutableType),state.getSourceForNode(expression)));
  return fix.build();
}
