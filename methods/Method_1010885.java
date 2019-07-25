@Override public MPSPsiNode create(SNodeId id,SConcept concept,String containingRole,PsiManager manager){
  SConcept currConcept=concept;
  NodeCreator factory=null;
  while (factory == null && currConcept != null) {
    factory=factories.get(currConcept.getQualifiedName());
    currConcept=currConcept.getSuperConcept();
  }
  if (factory != null) {
    return factory.create(id,concept.getQualifiedName(),containingRole,manager);
  }
  return null;
}
