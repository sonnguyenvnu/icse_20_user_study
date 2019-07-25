static RtaResult build(Set<Type> unusedTypeSet,Set<Member> unusedMemberSet){
  return new AutoValue_RtaResult.Builder().setUnusedTypes(toSortedList(unusedTypeSet.stream().map(Type::getName))).setUnusedMembers(toSortedList(unusedMemberSet.stream().map(RtaResult::createMemberId))).setCodeRemovalInfo(buildCodeRemovalInfo(unusedTypeSet,unusedMemberSet)).build();
}
