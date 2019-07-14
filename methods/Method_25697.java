SuggestedFix buildCommentArgumentsFix(InvocationInfo info){
  SuggestedFix.Builder commentArgumentsFixBuilder=SuggestedFix.builder();
  for (  ParameterPair change : changedPairs()) {
    int index=change.formal().index();
    ExpressionTree actual=info.actualParameters().get(index);
    int startPosition=((JCTree)actual).getStartPosition();
    String formal=info.formalParameters().get(index).getSimpleName().toString();
    commentArgumentsFixBuilder.replace(startPosition,startPosition,NamedParameterComment.toCommentText(formal));
  }
  return commentArgumentsFixBuilder.build();
}
