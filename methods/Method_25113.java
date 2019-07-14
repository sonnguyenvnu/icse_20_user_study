@Override public OrganizedImports organizeImports(List<Import> imports){
  Map<Boolean,ImmutableSortedSet<Import>> partionedByStatic=imports.stream().collect(Collectors.partitioningBy(Import::isStatic,toImmutableSortedSet(Comparator.comparing(Import::getType))));
  return new OrganizedImports().addGroups(partionedByStatic,order.groupOrder());
}
