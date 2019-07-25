public void execute(SNode node){
  SNode instance=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a6b4ccabL,"jetbrains.mps.baseLanguage.structure.AnnotationInstance"));
  SLinkOperations.setPointer(instance,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a6b4ccabL,0x114a6b85d40L,"annotation"),new SNodePointer("3f233e7f-b8a6-46d2-a57f-795d56775243/java:org.jetbrains.annotations(Annotations/)","~Nullable"));
  ListSequence.fromList(SLinkOperations.getChildren(((SNode)Add_NullableAnnotationToParameter_QuickFix.this.getField("parameter")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a6be947aL,0x114a6beb0bdL,"annotation"))).addElement(instance);
}
