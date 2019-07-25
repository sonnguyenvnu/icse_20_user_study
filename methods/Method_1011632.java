private Iterable<SNode> dependencies(SNode module){
  return ListSequence.fromList(SLinkOperations.getChildren(module,MetaAdapterFactory.getContainmentLink(0xcf935df46994e9cL,0xa132fa109541cba3L,0x48e82d508331930cL,0x48e82d5083341cb8L,"dependencies"))).select(new ISelector<SNode,SNode>(){
    public SNode select(    SNode it){
      return (SNodeOperations.isInstanceOf(it,MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x64bd442e1cf7aaeeL,"jetbrains.mps.build.mps.structure.BuildMps_ExtractedModuleDependency")) ? SLinkOperations.getTarget(SNodeOperations.cast(it,MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x64bd442e1cf7aaeeL,"jetbrains.mps.build.mps.structure.BuildMps_ExtractedModuleDependency")),MetaAdapterFactory.getContainmentLink(0xcf935df46994e9cL,0xa132fa109541cba3L,0x64bd442e1cf7aaeeL,0x64bd442e1cf7aaefL,"dependency")) : it);
    }
  }
);
}
