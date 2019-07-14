private static ApiDiff deriveApiDiff(boolean allowJava8){
  ClassSupportInfo support=new ClassSupportInfo(allowJava8);
  TreeSet<String> unsupportedClasses=new TreeSet<>(Java7ApiChecker.API_DIFF.unsupportedClasses());
  HashMultimap<String,ClassMemberKey> unsupportedMembers=HashMultimap.create(Java7ApiChecker.API_DIFF.unsupportedMembersByClass());
  for (  String allowedPkg : support.allowedPackages) {
    for (    String cls : classesStartingWithPackage(unsupportedClasses,allowedPkg)) {
      if (classIsInExactPackage(cls,allowedPkg)) {
        unsupportedClasses.remove(cls);
      }
    }
    for (    String cls : classesStartingWithPackage(new TreeSet<>(unsupportedMembers.keys()),allowedPkg)) {
      if (classIsInExactPackage(cls,allowedPkg)) {
        unsupportedMembers.removeAll(cls);
      }
    }
  }
  unsupportedClasses.removeAll(support.allowedClasses);
  unsupportedClasses.addAll(support.bannedClasses);
  support.allowedClasses.forEach(unsupportedMembers::removeAll);
  if (!support.bannedMembers.isEmpty()) {
    unsupportedMembers.entries().removeIf(support::memberIsWhitelisted);
  }
  unsupportedMembers.putAll(support.bannedMembers);
  return ApiDiff.fromMembers(unsupportedClasses,unsupportedMembers);
}
