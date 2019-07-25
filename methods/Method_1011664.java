public void next(SModuleReference generatorIdentity){
  if (!(mySeenGenerators.add(generatorIdentity))) {
    return;
  }
  if (myUseGenerators) {
    SNode gmp=SModelOperations.createNewNode(myTargetModel,null,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x73246de9adecb80dL,"jetbrains.mps.lang.smodel.structure.GeneratorModulePointer"));
    SLinkOperations.setNewChild(gmp,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x73246de9adecb80dL,0x73246de9adecb874L,"module"),null);
    ModuleIdentity__BehaviorDescriptor.setModuleReference_idnJmxU5cSTj.invoke(SLinkOperations.getTarget(gmp,MetaAdapterFactory.getContainmentLink(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x73246de9adecb80dL,0x73246de9adecb874L,"module")),generatorIdentity);
    SNode generatorStep;
    if (myDistinctSteps || mySharedGeneratorsStep == null) {
      generatorStep=SModelOperations.createNewNode(myTargetModel,null,MetaAdapterFactory.getConcept(0x7ab1a6fa0a114b95L,0x9e4875f363d6cb00L,0x73246de9adeca171L,"jetbrains.mps.lang.generator.plan.structure.ApplyGenerators"));
      ListSequence.fromList(SLinkOperations.getChildren(myTarget,MetaAdapterFactory.getContainmentLink(0x7ab1a6fa0a114b95L,0x9e4875f363d6cb00L,0x19443180a20717fbL,0x19443180a2071807L,"steps"))).addElement(generatorStep);
    }
 else {
      generatorStep=mySharedGeneratorsStep;
    }
    if (!(myDistinctSteps) && mySharedGeneratorsStep == null) {
      mySharedGeneratorsStep=generatorStep;
    }
    ListSequence.fromList(SLinkOperations.getChildren(generatorStep,MetaAdapterFactory.getContainmentLink(0x7ab1a6fa0a114b95L,0x9e4875f363d6cb00L,0x73246de9adeca171L,0x73246de9adf5a45cL,"generator"))).addElement(gmp);
  }
 else {
    SLanguage srcLanguage=as_mld0p3_a0a0a0a2a71(generatorIdentity.resolve(myGeneratorLookupRepo),Generator.class).sourceLanguage();
    SNode langId=SModelOperations.createNewNode(myTargetModel,null,MetaAdapterFactory.getConcept(0x7866978ea0f04cc7L,0x81bc4d213d9375e1L,0x312abca18ab8c8c0L,"jetbrains.mps.lang.smodel.structure.LanguageId"));
    LanguageIdentity__BehaviorDescriptor.setLanguage_id34EJa6aIcyw.invoke(langId,srcLanguage);
    SNode transform;
    if (myDistinctSteps || mySharedLanguagesStep == null) {
      transform=SModelOperations.createNewNode(myTargetModel,null,MetaAdapterFactory.getConcept(0x7ab1a6fa0a114b95L,0x9e4875f363d6cb00L,0x19443180a2071802L,"jetbrains.mps.lang.generator.plan.structure.Transform"));
      ListSequence.fromList(SLinkOperations.getChildren(myTarget,MetaAdapterFactory.getContainmentLink(0x7ab1a6fa0a114b95L,0x9e4875f363d6cb00L,0x19443180a20717fbL,0x19443180a2071807L,"steps"))).addElement(transform);
    }
 else {
      transform=mySharedLanguagesStep;
    }
    if (!(myDistinctSteps) && mySharedLanguagesStep == null) {
      mySharedLanguagesStep=transform;
    }
    ListSequence.fromList(SLinkOperations.getChildren(transform,MetaAdapterFactory.getContainmentLink(0x7ab1a6fa0a114b95L,0x9e4875f363d6cb00L,0x19443180a2071802L,0x28dd6d5a7549fa8dL,"languages"))).addElement(langId);
  }
}
