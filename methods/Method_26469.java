@Override public Description matchClass(ClassTree classTree,VisitorState state){
  LinkedHashMultimap<OverloadKey,MemberWithIndex> methods=LinkedHashMultimap.create();
  for (int i=0; i < classTree.getMembers().size(); ++i) {
    Tree member=classTree.getMembers().get(i);
    if (member instanceof MethodTree) {
      MethodTree methodTree=(MethodTree)member;
      methods.put(OverloadKey.create(methodTree),MemberWithIndex.create(i,methodTree));
    }
  }
  ImmutableList<Description> descriptions=methods.asMap().entrySet().stream().flatMap(e -> checkOverloads(state,classTree.getMembers(),ImmutableList.copyOf(e.getValue()))).collect(toImmutableList());
  if (batchFindings && !descriptions.isEmpty()) {
    SuggestedFix.Builder fix=SuggestedFix.builder();
    descriptions.forEach(d -> fix.merge((SuggestedFix)getOnlyElement(d.fixes)));
    return describeMatch(descriptions.get(0).position,fix.build());
  }
  descriptions.forEach(state::reportMatch);
  return NO_MATCH;
}
