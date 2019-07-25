public ConceptDeclarationScanner scan(SModel m){
  List<SNode> roots=SModelOperations.roots(m,null);
  for (  SNode cd : SNodeOperations.ofConcept(roots,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,"jetbrains.mps.lang.structure.structure.ConceptDeclaration"))) {
    SNode ex=SLinkOperations.getTarget(cd,MetaAdapterFactory.getReferenceLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,0xf979be93cfL,"extends"));
    if (ex != null && SNodeOperations.getModel(ex) != m) {
      myExternalConcepts.add(ex);
    }
    for (    SNode icd : SLinkOperations.collect(SLinkOperations.getChildren(cd,MetaAdapterFactory.getContainmentLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,0x110358d693eL,"implements")),MetaAdapterFactory.getReferenceLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x110356fc618L,0x110356fe029L,"intfc"))) {
      if (SNodeOperations.getModel(icd) != m) {
        myExternalIfaces.add(icd);
      }
    }
  }
  for (  SNode icd : SNodeOperations.ofConcept(roots,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103556dcafL,"jetbrains.mps.lang.structure.structure.InterfaceConceptDeclaration"))) {
    for (    SNode iface : SLinkOperations.collect(SLinkOperations.getChildren(icd,MetaAdapterFactory.getContainmentLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103556dcafL,0x110356e9df4L,"extends")),MetaAdapterFactory.getReferenceLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x110356fc618L,0x110356fe029L,"intfc"))) {
      if (SNodeOperations.getModel(iface) != m) {
        myExternalIfaces.add(iface);
      }
    }
  }
  for (  SNode cd : myExternalConcepts) {
    myExtendedModels.add(SNodeOperations.getModel(cd));
  }
  for (  SNode cd : myExternalIfaces) {
    myExtendedModels.add(SNodeOperations.getModel(cd));
  }
  if (myExcludeLangCore) {
    final SModelReference langCoreStructureModelRef=new SNodePointer("r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)","1133920641626").getModelReference();
    myExtendedModels.removeIf(new Predicate<SModel>(){
      public boolean test(      SModel m){
        return langCoreStructureModelRef.equals(SModelOperations.getPointer(m));
      }
    }
);
  }
  for (  SModel em : myExtendedModels) {
    myExtendedModules.add(em.getModule());
  }
  return this;
}
