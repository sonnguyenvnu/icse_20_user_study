private void organizePartition(OrganizedImports organized,List<Import> imports){
  Map<String,ImmutableSortedSet<Import>> groupedByRoot=imports.stream().collect(Collectors.groupingBy(AndroidImportOrganizer::rootPackage,TreeMap::new,toImmutableSortedSet(Comparator.comparing(Import::getType))));
  Set<String> thirdParty=groupedByRoot.keySet().stream().filter(r -> !SPECIAL_ROOTS.contains(r)).collect(toImmutableSortedSet(Ordering.natural()));
  List<String> roots=ImmutableList.<String>builder().add(ANDROID).add(COM_ANDROID).addAll(thirdParty).add(JAVA).add(JAVAX).build();
  organized.addGroups(groupedByRoot,roots);
}
