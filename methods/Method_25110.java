@Override public OrganizedImports organizeImports(List<Import> imports){
  OrganizedImports organized=new OrganizedImports();
  Map<Boolean,List<Import>> partionedByStatic=imports.stream().collect(Collectors.partitioningBy(Import::isStatic));
  for (  Boolean key : order.groupOrder()) {
    organizePartition(organized,partionedByStatic.get(key));
  }
  return organized;
}
