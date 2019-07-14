private Description describeIfObsolete(@Nullable Tree tree,Iterable<Type> types,VisitorState state){
  for (  Type type : types) {
    Obsolete obsolete=OBSOLETE.get(type.asElement().getQualifiedName().toString());
    if (obsolete == null) {
      continue;
    }
    if (shouldSkip(state,type)) {
      continue;
    }
    Description.Builder description=buildDescription(state.getPath().getLeaf()).setMessage(obsolete.message());
    if (tree != null) {
      obsolete.fix(tree,state).ifPresent(description::addFix);
    }
    return description.build();
  }
  return NO_MATCH;
}
