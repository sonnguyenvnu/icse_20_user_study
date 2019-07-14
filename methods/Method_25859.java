@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  ClassTree body=tree.getClassBody();
  if (body == null) {
    return NO_MATCH;
  }
  ImmutableList<? extends Tree> members=body.getMembers().stream().filter(m -> !(m instanceof MethodTree && ASTHelpers.isGeneratedConstructor((MethodTree)m))).collect(toImmutableList());
  if (members.size() != 1) {
    return NO_MATCH;
  }
  Tree member=Iterables.getOnlyElement(members);
  if (!(member instanceof BlockTree)) {
    return NO_MATCH;
  }
  BlockTree block=(BlockTree)member;
  Optional<CollectionTypes> collectionType=Arrays.stream(CollectionTypes.values()).filter(type -> type.constructorMatcher.matches(tree,state)).findFirst();
  if (!collectionType.isPresent()) {
    return NO_MATCH;
  }
  Description.Builder description=buildDescription(tree);
  collectionType.get().maybeFix(tree,state,block).ifPresent(description::addFix);
  return description.build();
}
