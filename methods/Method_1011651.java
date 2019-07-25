@Override public SNode wrap(SNode sourceNode){
  SNode result=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xa5e4de5346a344daL,0xaab368fdf1c34ed0L,0x1cf75b72b0b3962bL,"jetbrains.mps.console.ideCommands.structure.SubtreeStatisticsTarget"));
  SLinkOperations.setTarget(result,MetaAdapterFactory.getContainmentLink(0xa5e4de5346a344daL,0xaab368fdf1c34ed0L,0x1cf75b72b0b3962bL,0x1cf75b72b0b396c6L,"target"),sourceNode);
  return result;
}
