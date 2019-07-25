public void collect(@NotNull Iterable<SNode> modules,@NotNull List<SNode> additionalPlugins){
  List<SNode> initialPlugins=ListSequence.fromListWithValues(new ArrayList<SNode>(),additionalPlugins);
  for (  final SNode module : Sequence.fromIterable(modules)) {
    List<SNode> projectPlugins=SNodeOperations.getNodeDescendants(SNodeOperations.cast(SNodeOperations.getContainingRoot(module),MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4df58c6f18f84a13L,"jetbrains.mps.build.structure.BuildProject")),MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x5b7be37b4de9bb74L,"jetbrains.mps.build.mps.structure.BuildMps_IdeaPlugin"),false,new SAbstractConcept[]{});
    for (    SNode plugin : ListSequence.fromList(projectPlugins)) {
      if (ListSequence.fromList(SLinkOperations.getChildren(plugin,MetaAdapterFactory.getContainmentLink(0xcf935df46994e9cL,0xa132fa109541cba3L,0x5b7be37b4de9bb74L,0x5b7be37b4de9bbeaL,"content"))).any(new IWhereFilter<SNode>(){
        public boolean accept(        SNode it){
          return (boolean)BuildMps_IdeaPluginContent__BehaviorDescriptor.exports_id5FtnUVJQES1.invoke(it,module);
        }
      }
)) {
        ListSequence.fromList(initialPlugins).addElement(plugin);
        break;
      }
    }
  }
  SetSequence.fromSet(myPluginDependencies).addSequence(Sequence.fromIterable(new RequiredPlugins(initialPlugins).returnDepsWithInitial()));
}
