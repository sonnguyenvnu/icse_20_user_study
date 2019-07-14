private static SuggestedFix appendArgument(NewClassTree constructor,String exception,VisitorState state,List<Type> types){
  if (types.isEmpty()) {
    String source=state.getSourceForNode(constructor);
    int startPosition=((JCTree)constructor).getStartPosition();
    int pos=source.indexOf('(',state.getEndPosition(constructor.getIdentifier()) - startPosition) + startPosition + 1;
    return SuggestedFix.replace(pos,pos,exception);
  }
  return SuggestedFix.postfixWith(getLast(constructor.getArguments()),String.format(", %s",exception));
}
