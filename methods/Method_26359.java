private ImmutableAnalysis createImmutableAnalysis(VisitorState state){
  return new ImmutableAnalysis(this,state,wellKnownMutability,immutableAnnotations);
}
