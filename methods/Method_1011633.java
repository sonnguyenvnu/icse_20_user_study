public void collect(){
  Queue<SNode> queue=QueueSequence.fromQueue(new LinkedList<SNode>());
  QueueSequence.fromQueue(queue).addLastElement(myProject);
  Set<SNodeId> seen=SetSequence.fromSet(new HashSet<SNodeId>());
  while (QueueSequence.fromQueue(queue).isNotEmpty()) {
    SNode project=QueueSequence.fromQueue(queue).removeFirstElement();
    if (seen.contains(project.getNodeId())) {
      continue;
    }
    seen.add(project.getNodeId());
    for (    SNode dep : SLinkOperations.getChildren(project,MetaAdapterFactory.getContainmentLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x4df58c6f18f84a13L,0x4df58c6f18f84a25L,"dependencies"))) {
      SNode projectDependency=SNodeOperations.as(dep,MetaAdapterFactory.getConcept(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,"jetbrains.mps.build.structure.BuildProjectDependency"));
      if (projectDependency == null) {
        continue;
      }
      SNode depproj=SLinkOperations.getTarget(projectDependency,MetaAdapterFactory.getReferenceLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,0x4df58c6f18f84a24L,"script"));
      if ((depproj == null)) {
        SReference ref=SNodeOperations.getReference(projectDependency,MetaAdapterFactory.getReferenceLink(0x798100da4f0a421aL,0xb99171f8c50ce5d2L,0x454b730dd908c220L,0x4df58c6f18f84a24L,"script"));
        report("Cannot find the build project dependency " + SLinkOperations.getResolveInfo(ref) + " in the model " + check_xuwpka_a0a1a4a3a3a01(ref.getTargetSModelReference()),projectDependency);
      }
      if (depproj != null && !(seen.contains(depproj.getNodeId()))) {
        QueueSequence.fromQueue(queue).addLastElement(depproj);
      }
    }
    for (    SNode newModule : ListSequence.fromList(SNodeOperations.getNodeDescendants(project,MetaAdapterFactory.getConcept(0xcf935df46994e9cL,0xa132fa109541cba3L,0x4780308f5d333ebL,"jetbrains.mps.build.mps.structure.BuildMps_AbstractModule"),false,new SAbstractConcept[]{}))) {
      checkId(newModule,project);
      checkName(newModule,project);
      ListSequence.fromList(myModules).addElement(newModule);
    }
  }
  fillByIdAndByNameMaps();
}
