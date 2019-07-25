public void execute(SNode node){
  SNode expression=SLinkOperations.getTarget(((SNode)ReplaceClassAntiquotationWithClassifierType_QuickFix.this.getField("antiquotation")[0]),MetaAdapterFactory.getContainmentLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c104656L,0x1168c104657L,"expression"));
  SNode parent=SNodeOperations.getParent(((SNode)ReplaceClassAntiquotationWithClassifierType_QuickFix.this.getField("antiquotation")[0]));
  parent.removeChild(((SNode)ReplaceClassAntiquotationWithClassifierType_QuickFix.this.getField("antiquotation")[0]));
  SNode classifierType=SNodeOperations.replaceWithNewChild(parent,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,"jetbrains.mps.baseLanguage.structure.ClassifierType"));
  SNode referenceAntiquotation=AttributeOperations.createAndSetAttrbiute(classifierType,new IAttributeDescriptor.LinkAttribute(MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c10465dL,"jetbrains.mps.lang.quotation.structure.ReferenceAntiquotation"),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x101de490babL,"classifier")),MetaAdapterFactory.getConcept(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c10465dL,"jetbrains.mps.lang.quotation.structure.ReferenceAntiquotation"));
  SLinkOperations.setTarget(referenceAntiquotation,MetaAdapterFactory.getContainmentLink(0x3a13115c633c4c5cL,0xbbcc75c4219e9555L,0x1168c104656L,0x1168c104657L,"expression"),expression);
}
