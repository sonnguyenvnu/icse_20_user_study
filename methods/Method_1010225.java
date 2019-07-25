@Nullable @Override public SNode resolve(SNode contextNode,@NotNull String refText){
  return (!(SetSequence.fromSet(filteredNames).contains(refText)) ? scope.resolve(contextNode,refText) : null);
}
