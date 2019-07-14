public static JavaTypeDefinition lub(List<JavaTypeDefinition> types){
  for (  JavaTypeDefinition type : types) {
    if (type.isArrayType()) {
      return JavaTypeDefinition.forClass(Object.class);
    }
  }
  Set<Class<?>> erasedCandidateSet=getErasedCandidateSet(types);
  Set<Class<?>> minimalSet=getMinimalErasedCandidateSet(erasedCandidateSet);
  List<JavaTypeDefinition> candidates=new ArrayList<>();
  for (  Class<?> erasedSupertype : minimalSet) {
    JavaTypeDefinition lci=types.get(0).getAsSuper(erasedSupertype);
    for (    JavaTypeDefinition type : types) {
      if (lci == null) {
        throw new ResolutionFailedException();
      }
      lci=intersect(lci,type.getAsSuper(erasedSupertype));
    }
    candidates.add(lci);
  }
  if (candidates.isEmpty()) {
    throw new ResolutionFailedException();
  }
  JavaTypeDefinition result=candidates.get(0);
  for (  JavaTypeDefinition candidate : candidates) {
    if (MethodTypeResolution.isSubtypeable(candidate,result)) {
      result=candidate;
    }
 else     if (!MethodTypeResolution.isSubtypeable(result,candidate)) {
      throw new ResolutionFailedException();
    }
  }
  return result;
}
