public static ApiDiff fromMembers(Set<String> unsupportedClasses,Multimap<String,ClassMemberKey> unsupportedMembersByClass){
  return new AutoValue_ApiDiff(ImmutableSet.copyOf(unsupportedClasses),ImmutableSetMultimap.copyOf(unsupportedMembersByClass));
}
