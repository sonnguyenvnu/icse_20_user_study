public EditorDeclarationScanner scan(SModel m){
  SModule owner=m.getModule();
  for (  SNode ac : SModelOperations.roots(m,MetaAdapterFactory.getConcept(0x18bc659203a64e29L,0xa83a7ff23bde13baL,0x10f7df344a9L,"jetbrains.mps.lang.editor.structure.AbstractComponent"))) {
    SNode cd=SLinkOperations.getTarget(ac,MetaAdapterFactory.getReferenceLink(0x18bc659203a64e29L,0xa83a7ff23bde13baL,0x10f7df344a9L,0x10f7df451aeL,"conceptDeclaration"));
    if (cd != null && SNodeOperations.getModel(cd).getModule() != owner) {
      myExternalConcepts.add(cd);
      myExtendedModels.add(SNodeOperations.getModel(cd));
    }
  }
  for (  SNode menuRef : SModelOperations.nodes(m,MetaAdapterFactory.getInterfaceConcept(0x18bc659203a64e29L,0xa83a7ff23bde13baL,0x169efbc9a90a41b3L,"jetbrains.mps.lang.editor.structure.IMenuReference"))) {
    SNode cd=((SNode)BHReflection.invoke0(menuRef,MetaAdapterFactory.getInterfaceConcept(0x18bc659203a64e29L,0xa83a7ff23bde13baL,0x169efbc9a90a41b3L,"jetbrains.mps.lang.editor.structure.IMenuReference"),SMethodTrimmedId.create("getApplicableConcept",null,"1quYWAD4TFX")));
    if (cd != null && SNodeOperations.getModel(cd).getModule() != owner) {
      myExternalConcepts.add(cd);
      myExtendedModels.add(SNodeOperations.getModel(cd));
    }
  }
  for (  SModel em : myExtendedModels) {
    myExtendedModules.add(em.getModule());
  }
  return this;
}
