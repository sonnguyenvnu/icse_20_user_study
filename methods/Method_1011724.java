private void migrate(SNode method){
  if (!(SNodeOperations.getContainingLink(method).isValid()) || !(SNodeOperations.getContainingLink(method).isMultiple())) {
    return;
  }
  if (ListSequence.fromList(SNodeOperations.getAllSiblings(method,false)).any(new IWhereFilter<SNode>(){
    public boolean accept(    SNode templateMethod){
      return SNodeOperations.isInstanceOf(templateMethod,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b21dL,"jetbrains.mps.baseLanguage.structure.InstanceMethodDeclaration")) && AttributeOperations.getAttribute(templateMethod,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0xff1b29b76cL,"jetbrains.mps.lang.generator.structure.TemplateFragment"))) != null && ListSequence.fromList(AttributeOperations.getAttributeList(templateMethod,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0xfd47ed6742L,"jetbrains.mps.lang.generator.structure.NodeMacro")))).any(new IWhereFilter<SNode>(){
        public boolean accept(        SNode nodeMacro){
          return SLinkOperations.getTarget(SNodeOperations.as(nodeMacro,MetaAdapterFactory.getConcept(0xb401a68083254110L,0x8fd384331ff25befL,0x14f7f8a311b8f14fL,"jetbrains.mps.lang.generator.structure.TemplateCallMacro")),MetaAdapterFactory.getReferenceLink(0xb401a68083254110L,0x8fd384331ff25befL,0x17e941d108ce3120L,0x17e941d108ce3173L,"template")) == myCellFactoryCompatibilityTemplate;
        }
      }
);
    }
  }
)) {
    return;
  }
  SNodeOperations.insertNextSiblingChild(method,_quotation_createNode_647dgd_a0a3a6());
}
