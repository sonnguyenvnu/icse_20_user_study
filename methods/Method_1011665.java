public void confirm(List<RefactoringParticipant.Option> selectedOptions,Tuples._2<SAbstractConcept,SNodeReference> initialState,Tuples._2<SAbstractConcept,SNodeReference> finalState,SRepository repository,LanguageStructureMigrationParticipant.MigrationBuilder migrationBuilder,boolean updateModuleDependencies){
  SNode from=SNodeOperations.cast(initialState._1().resolve(repository),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,"jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration"));
  SNode to=SNodeOperations.cast(finalState._1().resolve(repository),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,"jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration"));
  SPropertyOperations.plusAssignStringProp(from,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"),"_old");
  AttributeOperations.setAttribute(from,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x11d0a70ae54L,"jetbrains.mps.lang.structure.structure.DeprecatedNodeAnnotation")),createDeprecatedNodeAnnotation_c4c66o_a0d0b("The concept was moved to language \"" + SNodeOperations.getModel(to).getModule().getModuleName() + "\""));
  SPropertyOperations.assign(to,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,0x5d2e6079771f8cc0L,"conceptId"),ConceptIdHelper.generateConceptId(SNodeOperations.getModel(to),to) + "");
  SModule fromModule=SNodeOperations.getModel(from).getModule();
  assert fromModule instanceof Language;
  SModel editorModel=LanguageAspect.EDITOR.getOrCreate((Language)fromModule);
  addEmptySubstituteMenu(editorModel,from);
  if (ListSequence.fromList(selectedOptions).contains(WriteSubconceptMigrationParticipant.OPTION)) {
    if (SNodeOperations.isInstanceOf(from,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,"jetbrains.mps.lang.structure.structure.ConceptDeclaration")) && SNodeOperations.isInstanceOf(to,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,"jetbrains.mps.lang.structure.structure.ConceptDeclaration"))) {
      SLinkOperations.setTarget(SNodeOperations.cast(to,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,"jetbrains.mps.lang.structure.structure.ConceptDeclaration")),MetaAdapterFactory.getReferenceLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,0xf979be93cfL,"extends"),SNodeOperations.cast(from,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,"jetbrains.mps.lang.structure.structure.ConceptDeclaration")));
    }
 else     if (SNodeOperations.isInstanceOf(from,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103556dcafL,"jetbrains.mps.lang.structure.structure.InterfaceConceptDeclaration")) && SNodeOperations.isInstanceOf(to,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103556dcafL,"jetbrains.mps.lang.structure.structure.InterfaceConceptDeclaration"))) {
      ListSequence.fromList(SLinkOperations.getChildren(SNodeOperations.cast(to,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103556dcafL,"jetbrains.mps.lang.structure.structure.InterfaceConceptDeclaration")),MetaAdapterFactory.getContainmentLink(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103556dcafL,0x110356e9df4L,"extends"))).addElement(createInterfaceConceptReference_c4c66o_a0a0a0a0l0b(SNodeOperations.cast(from,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103556dcafL,"jetbrains.mps.lang.structure.structure.InterfaceConceptDeclaration"))));
    }
 else {
      throw new IllegalStateException();
    }
    SModule toModule=SNodeOperations.getModel(to).getModule();
    assert toModule instanceof Language;
    if (updateModuleDependencies) {
      ((Language)toModule).addExtendedLanguage(fromModule.getModuleReference());
    }
  }
  SNode oldId=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x5fea1eb9fefb6fe7L,"jetbrains.mps.lang.smodel.structure.ConceptId"));
  ConceptId__BehaviorDescriptor.setConcept_id5ZE7FBYYR6j.invoke(oldId,MetaAdapterByDeclaration.getConcept(from));
  SNode newId=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x5fea1eb9fefb6fe7L,"jetbrains.mps.lang.smodel.structure.ConceptId"));
  ConceptId__BehaviorDescriptor.setConcept_id5ZE7FBYYR6j.invoke(newId,MetaAdapterByDeclaration.getConcept(to));
  migrationBuilder.addPart(from,to,createMoveConcept_c4c66o_c0a71a1(oldId,newId));
}
