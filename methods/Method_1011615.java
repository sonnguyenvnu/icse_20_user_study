public void init(){
  for (  SNode m : SLinkOperations.getChildren(project,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4df58c6f18f84a13L,0x4df58c6f18f84a22L,"macros"))) {
    if (usedNames.contains(SPropertyOperations.getString(m,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")))) {
      context.reportProblem("duplicate macro name",m);
    }
    add(m,null,((boolean)BuildMacro__BehaviorDescriptor.isPublic_id5FtnUVJQZyL.invoke(m) ? SPropertyOperations.getString(project,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")) + "." + SPropertyOperations.getString(m,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")) : null));
  }
  for (  SNode dep : SNodeOperations.ofConcept(SLinkOperations.getChildren(project,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4df58c6f18f84a13L,0x4df58c6f18f84a25L,"dependencies")),MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,"jetbrains.mps.build.structure.BuildProjectDependency"))) {
    SNode depProject=SLinkOperations.getTarget(dep,MetaAdapterFactory.getReferenceLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,0x4df58c6f18f84a24L,"script"));
    MacroHelper depHelper=context.getMacros(depProject);
    if (depHelper == null) {
      continue;
    }
    for (    SNode m : depHelper.getAvailableMacros()) {
      if (macroToName.containsKey(m)) {
        continue;
      }
      String exportName=depHelper.getExportName(m);
      if (exportName == null) {
        continue;
      }
      String depprefix=depPrefixes.get(dep);
      if (depprefix == null) {
        depprefix=makeUnique("import." + SPropertyOperations.getString(SLinkOperations.getTarget(dep,MetaAdapterFactory.getReferenceLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,0x4df58c6f18f84a24L,"script")),MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")),usedPrefixes);
        depPrefixes.put(dep,depprefix);
      }
      add(m,depprefix + "." + exportName,exportName);
    }
  }
}
