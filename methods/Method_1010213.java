public void load(TransitionTrace into){
  for (  SNode n : SNodeUtil.getDescendants(myCheckpointModel)) {
    SNode originTrace=AttributeOperations.getAttribute(n,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x6ebfdca4628bfd48L,"jetbrains.mps.lang.generator.structure.OriginTrace")));
    if ((originTrace == null)) {
      continue;
    }
    SNodeId value=((SNodeId)BHReflection.invoke0(SLinkOperations.getTarget(originTrace,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x6ebfdca4628bfd48L,0x6ebfdca4628bfd4dL,"origin")),MetaAdapterFactory.getInterfaceConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x7d58bd9fd9b5e358L,"jetbrains.mps.lang.generator.structure.NodeIdentity"),SMethodTrimmedId.create("getNodeId",null,"39TODbGsIdf")));
    into.setOrigin(n,value);
  }
}
