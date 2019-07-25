public void save(TransitionTrace originTrace){
  for (  SNode n : SNodeUtil.getDescendants(myCheckpointModel)) {
    if (!(originTrace.hasOrigin(n))) {
      continue;
    }
    SNodeId origin=originTrace.getOrigin(n);
    SNode nid=((SNode)BHReflection.invoke0(SNodeOperations.asSConcept(MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x3279d292ec74a708L,"jetbrains.mps.lang.generator.structure.ElementaryNodeId")),MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x3279d292ec74a708L,"jetbrains.mps.lang.generator.structure.ElementaryNodeId"),SMethodTrimmedId.create("create",MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x3279d292ec74a708L,"jetbrains.mps.lang.generator.structure.ElementaryNodeId"),"6UZRahyzeh3"),myCheckpointModel,origin));
    SNode ot=AttributeOperations.createAndSetAttrbiute(n,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x6ebfdca4628bfd48L,"jetbrains.mps.lang.generator.structure.OriginTrace")),MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x6ebfdca4628bfd48L,"jetbrains.mps.lang.generator.structure.OriginTrace"));
    SLinkOperations.setTarget(ot,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x6ebfdca4628bfd48L,0x6ebfdca4628bfd4dL,"origin"),nid);
  }
}
