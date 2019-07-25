public static void init(SNode c,SModel futureModel){
  SModule module=futureModel.getModule();
  if (SModuleOperations.isAspect(futureModel,"migration")) {
    Language lang=(Language)module;
    int currentVersion=lang.getLanguageVersion();
    SPropertyOperations.assign(c,MetaAdapterFactory.getProperty(0x9074634404fd4286L,0x97d5b46ae6a81709L,0x73e8a2c68b62c6a3L,0x50c63f9f4a0dac17L,"fromVersion"),currentVersion);
    lang.setLanguageVersion(currentVersion + 1);
  }
  SLinkOperations.setTarget(SLinkOperations.setNewChild(c,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c108ca66L,0x10f6353296dL,"superclass"),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType")),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x101de490babL,"classifier"),SNodeOperations.getNode("90746344-04fd-4286-97d5-b46ae6a81709/r:52a3d974-bd4f-4651-ba6e-a2de5e336d95(jetbrains.mps.lang.migration/jetbrains.mps.lang.migration.methods)","6807933448469658100"));
  ModelImports m=new ModelImports(futureModel);
  AbstractModule mod=(AbstractModule)module;
  m.addUsedLanguage(MetaAdapterFactory.getLanguage(0x28f9e4973b424291L,0xaeba0a1039153ab1L,"jetbrains.mps.lang.plugin"));
  m.addUsedLanguage(MetaAdapterFactory.getLanguage(0xd4615e3bd6714ba9L,0xaf012b78369b0ba7L,"jetbrains.mps.lang.pattern"));
  m.addModelImport(PersistenceFacade.getInstance().createModelReference("8865b7a8-5271-43d3-884c-6fd1d9cfdd34/java:org.jetbrains.mps.openapi.language(MPS.OpenAPI/)"));
  m.addModelImport(PersistenceFacade.getInstance().createModelReference("8865b7a8-5271-43d3-884c-6fd1d9cfdd34/java:org.jetbrains.mps.openapi.module(MPS.OpenAPI/)"));
  mod.addDependency(PersistenceFacade.getInstance().createModuleReference("8865b7a8-5271-43d3-884c-6fd1d9cfdd34(MPS.OpenAPI)"),false);
}
