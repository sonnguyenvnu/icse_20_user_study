public void execute(SNode node){
  SNode part=SNodeOperations.cast(node,MetaAdapterFactory.getInterfaceConcept(0x18bc659203a64e29L,0xa83a7ff23bde13baL,0x652f322a364c9a28L,"jetbrains.mps.lang.editor.structure.IExtensibleTransformationMenuPart"));
  for (  SConcept c : Sequence.fromIterable(IExtensibleTransformationMenuPart__BehaviorDescriptor.getMissingFeatures_id6kJcyCQjeiA.invoke(part))) {
    ListSequence.fromList(SLinkOperations.getChildren(part,MetaAdapterFactory.getContainmentLink(0x18bc659203a64e29L,0xa83a7ff23bde13baL,0x652f322a364c9a28L,0x7c45559defbb3517L,"features"))).addElement(SNodeFactoryOperations.createNewNode(c,null));
  }
}
