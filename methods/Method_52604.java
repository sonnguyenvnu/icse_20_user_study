public static Set<Class<?>> getErasedCandidateSet(List<JavaTypeDefinition> erasedSuperTypeSets){
  Set<Class<?>> result=new HashSet<>();
  if (!erasedSuperTypeSets.isEmpty()) {
    result=erasedSuperTypeSets.get(0).getErasedSuperTypeSet();
  }
  for (int i=1; i < erasedSuperTypeSets.size(); ++i) {
    result.retainAll(erasedSuperTypeSets.get(i).getErasedSuperTypeSet());
  }
  return result;
}
