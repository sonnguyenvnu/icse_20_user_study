/** 
 * Reverse operation to  {@link jetbrains.mps.generator.impl.DebugMappingsBuilder#build(SModel,GeneratorMappings) }, restore mappings information from debug node. Likely shall use same mapping API in both build() and restore() (MappingsMemento or its newer, better version) or even split restore code into separate class (provided it may need different initialization values)
 */
public MappingsMemento restore(SNode debugNode){
  MappingsMemento rv=new MappingsMemento();
  for (  SNode labelEntry : ListSequence.fromList(SLinkOperations.getChildren(debugNode,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc97f1c1L,0x35a02f6bfc9806c5L,"labels")))) {
    final String labelName=SPropertyOperations.getString(labelEntry,MetaAdapterFactory.getProperty(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c4L,0x35a02f6bfc9810e9L,"label"));
    for (    SNode entry : ListSequence.fromList(SLinkOperations.getChildren(labelEntry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c4L,0x35a02f6bfc9810ebL,"entries")))) {
      if (SPropertyOperations.getBoolean(entry,MetaAdapterFactory.getProperty(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x511a0d44c7f45537L,"isNewRoot"))) {
        rv.addNewOutputNode(labelName,SLinkOperations.getTarget(ListSequence.fromList(SLinkOperations.getChildren(entry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x35a02f6bfc9806d5L,"outputNode"))).first(),MetaAdapterFactory.getReferenceLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806d2L,0x35a02f6bfc9806d3L,"node")).getNodeId());
        continue;
      }
      final SNodeId inputNodeId;
      if ((SLinkOperations.getTarget(SLinkOperations.getTarget(entry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x509c00a99889f77eL,"inputNode")),MetaAdapterFactory.getReferenceLink(0xb401a68083254110L,0x8fd384331ff25befL,0x509c00a998897534L,0x509c00a99889f6ffL,"nodePtr")) != null)) {
        SReference ref=SLinkOperations.getTarget(entry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x509c00a99889f77eL,"inputNode")).getReference(MetaAdapterFactory.getReferenceLink(0xb401a68083254110L,0x8fd384331ff25befL,0x509c00a998897534L,0x509c00a99889f6ffL,"nodePtr"));
        inputNodeId=ref.getTargetNodeId();
      }
 else {
        inputNodeId=((SNodeId)BHReflection.invoke0(SLinkOperations.getTarget(SLinkOperations.getTarget(entry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x509c00a99889f77eL,"inputNode")),MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x509c00a998897534L,0x509c00a99889f0aeL,"node")),MetaAdapterFactory.getInterfaceConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x7d58bd9fd9b5e358L,"jetbrains.mps.lang.generator.structure.NodeIdentity"),SMethodTrimmedId.create("getNodeId",null,"39TODbGsIdf")));
      }
      if (ListSequence.fromList(SLinkOperations.getChildren(entry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x35a02f6bfc9806d5L,"outputNode"))).count() == 1) {
        rv.addOutputNodeByInputNodeAndMappingName(inputNodeId,labelName,SLinkOperations.getTarget(ListSequence.fromList(SLinkOperations.getChildren(entry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x35a02f6bfc9806d5L,"outputNode"))).first(),MetaAdapterFactory.getReferenceLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806d2L,0x35a02f6bfc9806d3L,"node")));
      }
 else {
        List<SNode> t=new ArrayList<SNode>();
        for (        SNode on : ListSequence.fromList(SLinkOperations.getChildren(entry,MetaAdapterFactory.getContainmentLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806c7L,0x35a02f6bfc9806d5L,"outputNode")))) {
          ListSequence.fromList(t).addElement(SLinkOperations.getTarget(on,MetaAdapterFactory.getReferenceLink(0xb401a68083254110L,0x8fd384331ff25befL,0x35a02f6bfc9806d2L,0x35a02f6bfc9806d3L,"node")));
        }
        rv.addOutputNodeByInputNodeAndMappingName(inputNodeId,labelName,t);
      }
    }
  }
  return rv;
}
