static List<Type> build(List<TypeInfo> typeInfos){
  List<Type> types=createTypes(typeInfos);
  LinkedHashMultiset<Type> typesInTopologicalOrder=sortTypesInTopologicalOrder(types);
  addInheritedMembers(typesInTopologicalOrder);
  computeOverrideFrontier(ImmutableList.copyOf(typesInTopologicalOrder.elementSet()));
  return types;
}
