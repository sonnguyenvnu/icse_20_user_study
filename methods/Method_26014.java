private Description handle(@Nullable DocTreePath path,VisitorState state){
  if (path == null) {
    return NO_MATCH;
  }
  RangesFinder rangesFinder=new RangesFinder(state);
  rangesFinder.scan(path,null);
  Comment comment=((DCDocComment)path.getDocComment()).comment;
  Matcher matcher=GENERIC_PATTERN.matcher(comment.getText());
  RangeSet<Integer> generics=TreeRangeSet.create();
  while (matcher.find()) {
    generics.add(Range.closedOpen(comment.getSourcePos(matcher.start()),comment.getSourcePos(matcher.end())));
  }
  RangeSet<Integer> emittedFixes=fixGenerics(generics,rangesFinder.preTags,rangesFinder.dontEmitCodeFix,state);
  new EntityChecker(state,generics,rangesFinder.preTags,emittedFixes).scan(path,null);
  return NO_MATCH;
}
