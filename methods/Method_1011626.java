public void execute(SNode node){
  SNode module=SNodeOperations.as(node,MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x4780308f5d333ebL,"jetbrains.mps.build.mps.structure.BuildMps_AbstractModule"));
  if (module == null) {
    return;
  }
  SNode project=SNodeOperations.as(SNodeOperations.getContainingRoot(node),MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4df58c6f18f84a13L,"jetbrains.mps.build.structure.BuildProject"));
  if ((project == null)) {
    return;
  }
  if (SNodeOperations.isInstanceOf(module,MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x4c6db07d2e56a8b4L,"jetbrains.mps.build.mps.structure.BuildMps_Generator"))) {
    return;
  }
  ModuleLoader ml=new ModuleLoader(project,null,new LogHandler(Logger.getLogger(ModuleLoader.class)));
  ml.createModuleChecker(module).check(ModuleChecker.CheckType.LOAD_IMPORTANT_PART);
}
