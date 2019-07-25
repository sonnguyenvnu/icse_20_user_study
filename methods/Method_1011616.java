private void dfs(SNode project,List<SNode> result,Set<SNode> visited){
  SetSequence.fromSet(visited).addElement(project);
  for (  SNode dependency : Sequence.fromIterable(getLocalDependencies(project))) {
    if (SetSequence.fromSet(visited).contains(SLinkOperations.getTarget(dependency,MetaAdapterFactory.getReferenceLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,0x4df58c6f18f84a24L,"script")))) {
      continue;
    }
    dfs(SLinkOperations.getTarget(dependency,MetaAdapterFactory.getReferenceLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,0x4df58c6f18f84a24L,"script")),result,visited);
    ListSequence.fromList(result).addElement(dependency);
  }
}
